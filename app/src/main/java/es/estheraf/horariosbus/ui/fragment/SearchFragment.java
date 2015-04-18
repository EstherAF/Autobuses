package es.estheraf.horariosbus.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.activity.MainActivity;
import es.estheraf.horariosbus.data.model.SearchRoute;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.sqlite.StopProviderImpl;
import es.estheraf.horariosbus.ui.BundleKey;
import es.estheraf.horariosbus.ui.fragment.adapter.StopsSpinnerAdapter;

import static es.estheraf.horariosbus.ui.BundleKey.PICKED_DATE;

/**
 * Fragment in charge of displaying routes search screen
 *
 * @author Esther Álvarez Feijoo
 */
public class SearchFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    private Spinner mOrigins;
    private Spinner mDestinations;
    private Button mDate;
    private Button mSearch;

    private DatePickerFragment mDatePickerFragment;
    private StopsSpinnerAdapter mOriginsAdapter;
    private StopsSpinnerAdapter mDestinationsAdapter;


    public static SearchFragment newInstance(Bundle bundle) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //initialize Bundle
        if (!getArguments().containsKey(PICKED_DATE.val()))
            getArguments().putSerializable(PICKED_DATE.val(), LocalDate.now());

        //Inflate fragment on view container
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Initialize references to existing view elements
        initializeViewReferences(getView());
        //Spinner adapters for origin and destination
        createAdapters();

        //Bind events to callbacks
        mOrigins.setOnItemSelectedListener(new OriginsSpinnerListener());
        mDate.setOnClickListener(new DatePickerListener());
        mSearch.setOnClickListener(new SearchButtonListener());

        //Modify view elements
        mDate.setText(DatePickerFragment.getDateAsText(getResources(),
                (LocalDate) getArguments().get(PICKED_DATE.val())));
    }

    /**
     * Creating the fragment
     */
    private void initializeViewReferences(View view) {
        mOrigins = (Spinner) view.findViewById(R.id.search_origin);
        mDestinations = (Spinner) view.findViewById(R.id.search_destination);
        mDate = (Button) view.findViewById(R.id.search_date_btn);
        mSearch = (Button) view.findViewById(R.id.search_btn);
    }

    private void createAdapters() {
        mOriginsAdapter = new StopsSpinnerAdapter(getActivity(), StopProviderImpl.getInstance().getStops());
        mOrigins.setAdapter(mOriginsAdapter);
        mDestinationsAdapter = new StopsSpinnerAdapter(getActivity(), new ArrayList<Stop>());
        mDestinations.setAdapter(mDestinationsAdapter);
    }


    /**
     * Listeners
     */
    private class OriginsSpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            Stop selectedOrigin = mOriginsAdapter.getItem(position);
            updateDestinationsSpinner(selectedOrigin);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            mDestinationsAdapter.clear();
            mDestinationsAdapter.notifyDataSetChanged();
        }

        /**
         * Update the values of destinationSpinner, based on selected value of originSpinner
         *
         * @param origin
         */
        private void updateDestinationsSpinner(Stop origin) {
            List<Stop> newDestinations = StopProviderImpl.getInstance().getDestinations(origin.id);
            mDestinationsAdapter.clear();
            mDestinationsAdapter.addAll(newDestinations);
            mDestinationsAdapter.notifyDataSetChanged();
        }
    }

    private class DatePickerListener implements AdapterView.OnClickListener {
        @Override
        public void onClick(View view) {
            mDatePickerFragment = new DatePickerFragment();
            mDatePickerFragment.setArguments(getArguments());
            mDatePickerFragment.show(getFragmentManager(), "datePicker");
        }
    }

    private class SearchButtonListener implements AdapterView.OnClickListener {

        @Override
        public void onClick(View view) {
            SearchRoute search = new SearchRoute();
            search.origin=(Stop) mOrigins.getSelectedItem();
            search.destination=(Stop) mDestinations.getSelectedItem();
            search.date = (LocalDate) getArguments().getSerializable(PICKED_DATE.val());
            getArguments().putSerializable(BundleKey.SEARCH_FILTERS.val(), search);

            ((MainActivity) getActivity()).doSearch();
        }
    }

}
