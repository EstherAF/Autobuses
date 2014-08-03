package es.estheraf.horariosbus.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.adapter.StopsSpinnerAdapter;
import es.estheraf.horariosbus.data.DataProvider;
import es.estheraf.horariosbus.data.model.Stop;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Fragment in charge of displaying routes search screen
 *
 * @author Félix Queiruga Balado
 */
public class SearchFragment extends Fragment {

    private CardView mSearchCardView;

    private Spinner mOrigins;
    private Spinner mDestinations;
    private StopsSpinnerAdapter mOriginsAdapter;
    private StopsSpinnerAdapter mDestinationsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initCardView(view);
        initSpinners(view);
        return view;
    }

    private void initCardView(View view) {
        mSearchCardView = (CardView) view.findViewById(R.id.card_search);
        Card card = new Card(getActivity(), R.layout.card_search);
        mSearchCardView.setCard(card);
    }

    private void initSpinners(View view) {
        mOrigins = (Spinner) view.findViewById(R.id.origin);
        mDestinations = (Spinner) view.findViewById(R.id.destination);

        mOriginsAdapter = new StopsSpinnerAdapter(getActivity(), getStops());
        mOrigins.setAdapter(mOriginsAdapter);
        mOrigins.setOnItemSelectedListener(new OriginsSpinnerListener());

        mDestinationsAdapter = new StopsSpinnerAdapter(getActivity(), new ArrayList<Stop>());
        mDestinations.setAdapter(mDestinationsAdapter);
    }

    private List<Stop> getStops() {
        return DataProvider.getStops();
    }

    private void updateDestinationsSpinner(int position) {
        Stop selectedOrigin = mOriginsAdapter.getItem(position);
        List<Stop> newDestinations = DataProvider.getDestinations(selectedOrigin);
        mDestinationsAdapter.clear();
        mDestinationsAdapter.addAll(newDestinations);
        mDestinationsAdapter.notifyDataSetChanged();
    }

    private class OriginsSpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            updateDestinationsSpinner(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

}
