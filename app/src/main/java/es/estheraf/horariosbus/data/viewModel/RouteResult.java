package es.estheraf.horariosbus.data.viewModel;

import org.joda.time.LocalTime;

import java.util.Date;

/**
 * @author Esther Álvarez
 */
public class RouteResult implements Comparable{

    public Integer routeId;

    public String routeName;

    public LocalTime originTime;

    public LocalTime destinationTime;

    @Override
    public int compareTo(Object o) {
        RouteResult obj = (RouteResult) o;

        //Compare by departure time
        int comparison = this.originTime.compareTo(obj.originTime);
        if(comparison != 0) return comparison;

        //Otherwise, Compare by destination time
        comparison = this.destinationTime.compareTo(obj.destinationTime);
        if(comparison != 0) return comparison;

        //Otherwise, Compare by route id
        comparison = this.routeId - obj.routeId;

        return comparison;
    }
}
