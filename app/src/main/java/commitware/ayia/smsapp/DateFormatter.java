package commitware.ayia.smsapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatter {

    public String calenderToDate(Calendar date)
    {
        SimpleDateFormat  sdf = new SimpleDateFormat("dd MMM", Locale.getDefault());

        if(date.get(Calendar.YEAR) != Calendar.getInstance().get(Calendar.YEAR))
        {
            sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        }

        return sdf.format(date.getTime());
    }

    public Calendar longToCalender(long date)
    {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public String longToDate(long date)
    {
        return calenderToDate(longToCalender(date));
    }


}
