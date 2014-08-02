package es.estheraf.horariosbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;

public class StopsSpinnerAdapter extends ArrayAdapter<Stop> implements SpinnerAdapter {

    private List<Stop> mStops;

    public StopsSpinnerAdapter(Context context, List<Stop> stops) {
        super(context, View.NO_ID, stops);
        mStops = stops;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return retrieveView(position, view, parent, android.R.layout.simple_spinner_item);
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        return retrieveView(position, view, parent, android.R.layout.simple_spinner_dropdown_item);
    }

    private View retrieveView(int position, View view, ViewGroup parent, int layout) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(mStops.get(position).name);
        return view;
    }

    private static class ViewHolder {
        public TextView name;
    }

}
