package es.estheraf.horariosbus.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.estheraf.horariosbus.R;

public class SearchFragment extends Fragment {

    private Spinner mOrigin;
    private Spinner mDestination;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        List<String> stops = Arrays.asList("Stop 1", "Stop 2");

        mOrigin = (Spinner) view.findViewById(R.id.origin);
        mDestination = (Spinner) view.findViewById(R.id.destination);
        mOrigin.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stops));
        mDestination.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stops));

        return view;
    }

}
