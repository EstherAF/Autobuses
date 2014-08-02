package es.estheraf.horariosbus.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.adapter.StopsSpinnerAdapter;
import es.estheraf.horariosbus.data.model.Stop;

public class SearchFragment extends Fragment {

    private Spinner mOrigins;
    private Spinner mDestinations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mOrigins = (Spinner) view.findViewById(R.id.origin);
        mDestinations = (Spinner) view.findViewById(R.id.destination);

        mOrigins.setAdapter(new StopsSpinnerAdapter(getActivity(), getOrigins()));
        mDestinations.setAdapter(new StopsSpinnerAdapter(getActivity(), getDestinations()));

        return view;
    }

    private List<Stop> getOrigins() {
        return Arrays.asList(
                new Stop(3, "Origin 1"),
                new Stop(4, "Origin 2")
        );
    }

    private List<Stop> getDestinations() {
        return Arrays.asList(
                new Stop(3, "Destination 1"),
                new Stop(4, "Destination 2")
        );
    }

}
