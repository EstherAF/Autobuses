package es.estheraf.horariosbus.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import es.estheraf.horariosbus.R;
import es.estheraf.horariosbus.ui.BundleKey;

import static es.estheraf.horariosbus.ui.BundleKey.PICKED_DATE;

/**
 * Fragment of DatePickerDialog, to select the date of search.
 *
 * Created by Esther on 22/10/2014.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    /**
     * Constructor with no args
     */
    public DatePickerFragment() {
    }


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        //Recover saved pickedDate
        LocalDate pickedDate = (LocalDate) getArguments().getSerializable(PICKED_DATE.val());
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this,
                pickedDate.getYear(), pickedDate.getMonthOfYear() - 1, pickedDate.getDayOfMonth());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        //Recover picked date
        LocalDate pickedDate = new LocalDate(year, month + 1, date);
        //Store it in bundle
        getArguments().putSerializable(PICKED_DATE.val(), pickedDate);
        //Show date in button's label
        String dateAsText = getDateAsText(getResources(), pickedDate);
        ((Button) getActivity().findViewById(R.id.search_date_btn)).setText(dateAsText);
    }

    /**
     * Build human readable text for a given date
     *
     * @param date
     * @return String
     */
    public static String getDateAsText(Resources resources, LocalDate date) {
        LocalDate now = LocalDate.now();
        int daysUntilDate = Days.daysBetween(now, date).getDays();
        if (Math.abs(daysUntilDate) <= 1) {
            //Return predefined String for today, tomorrow or yesterday
            int resourceId = (daysUntilDate == 0) ? R.string.date_today
                    : (date.getDayOfMonth() > now.getDayOfMonth()) ? R.string.date_tomorrow
                    : R.string.date_yesterday;
            return resources.getString(resourceId);
        }
        String datePattern = resources.getString(R.string.date_pattern);
        return date.toString(datePattern);
    }
}
