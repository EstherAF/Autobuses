package es.estheraf.horariosbus.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.data.model.SearchRoute;
import es.estheraf.horariosbus.ui.fragment.adapter.ResultListAdapter;
import es.estheraf.horariosbus.ui.model.UIResult;

import static es.estheraf.horariosbus.ui.BundleKey.RESULTS;

/**
 * Created by Esther on 26/10/2014.
 */
public class ResultFragment extends Fragment {

    private ListView mResultList;
    private BarViewHolder mBar;

    public static ResultFragment newInstance(Bundle bundle) {
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate layout
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Initialize references to existing view elements
        mResultList = (ListView) getView().findViewById(R.id.result_list);
        mBar = new BarViewHolder(getView());
        //adapter for result list
        mResultList.setAdapter(new ResultListAdapter(getActivity(), getUIResults().results));
        //Modify view elements
        mBar.populateView(getView(), getUIResults().search);

    }

    private UIResult getUIResults() {
        return (UIResult) getArguments().getSerializable(RESULTS.val());
    }

    private class BarViewHolder {
        public ViewGroup bar;
        public TextView origin;
        public TextView destination;
        public TextView date;

        /**
         * Initialize references to views
         */
        public BarViewHolder(View view) {
            this.bar = (ViewGroup) view.findViewById(R.id.result_bar);
            this.origin = (TextView) view.findViewById(R.id.result_bar_origin);
            this.destination = (TextView) view.findViewById(R.id.result_bar_destination);
            this.date = (TextView) view.findViewById(R.id.result_bar_date);
        }

        /**
         * Populate texts of holder's views
         *
         * @param view   View
         * @param search Object that contains search info, to generate bar's texts
         */
        private void populateView(View view, SearchRoute search) {
            this.origin.setText(search.origin.name);
            this.destination.setText(search.destination.name);
            String pattern = view.getResources().getString(R.string.date_pattern);
            this.date.setText(search.date.toString(pattern));
        }
    }

}
