package es.estheraf.horariosbus.ui.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.LocalTime;

import java.util.List;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.data.model.SimpleResultRoute;

/**
 * Created by esther on 28/10/14.
 */
public class ResultListAdapter extends ArrayAdapter<SimpleResultRoute> implements ListAdapter {

    private int positionAutoScroll = 0;

    private List<SimpleResultRoute> results;

    public ResultListAdapter(Context context, List<SimpleResultRoute> results) {
        super(context, View.NO_ID, results);
        this.results = results;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //Create viewHolder
        ViewHolder holder = null;
        SimpleResultRoute result = results.get(position);
        boolean routeIsActive = !LocalTime.now().isAfter(result.departureTime);

        if (routeIsActive && positionAutoScroll==0) autoScroll(position, (ListView) parent);

        if (view != null) {
            holder = (ViewHolder) view.getTag();
            //If view's layout isn't updated, create it again
            if (routeIsActive != holder.active) view = null;
        }

        if (view == null) {
            //Layout depending on departure time is after now
            view = createViewElement(routeIsActive, parent);
            holder = new ViewHolder(view, routeIsActive);
            //tag it
            view.setTag(holder);
        }
        //Populate texts of holder's views
        holder.populateView(holder, result);

        return view;
    }

    private void autoScroll(int position, ListView list){
        positionAutoScroll = position;
        list.setSelection(position);
    }

    private View createViewElement(boolean routeIsActive, ViewGroup parent){
        return LayoutInflater.from(getContext()).inflate((routeIsActive)
                ? R.layout.list_item_result_enabled
                : R.layout.list_item_result_disabled, parent, false);
    }

    /**
     * ViewHolder Pattern
     */
    private class ViewHolder {
        boolean active;
        TextView hour;
        TextView route;

        public ViewHolder() {
        }

        /**
         * Initialize references to views
         */
        public ViewHolder(View view, boolean isActive) {
            this.active = isActive;
            this.hour = (TextView) view.findViewById(R.id.result_list_elem_hour);
            this.route = (TextView) view.findViewById(R.id.result_list_elem_route_name);
        }

        /**
         * Populate texts of holder's views
         *
         * @param holder ViewHolder
         * @param result Object that contains info to generate view's texts
         */
        private void populateView(ViewHolder holder, SimpleResultRoute result) {
            String pattern = getContext().getResources().getString(R.string.time_pattern);
            holder.route.setText(result.routeName);
            StringBuilder hoursBuilder = new StringBuilder(result.departureTime.toString(pattern))
                    .append(getContext().getResources().getString(R.string.result_times_separator))
                    .append(result.departureTime.toString(pattern));

            holder.hour.setText(hoursBuilder.toString());

        }
    }
}
