package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateHandler {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static Calendar formatForProgram(String dateTime) throws ParseException {
        Date dateDate = dateFormat.parse(dateTime);
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateDate);
        return calendarDate;
    }

    public static String formatForDisplay(Calendar date){
        return dateFormat.format(date.getTime());
    }

    public static long timeBetweenDates(Calendar date1, Calendar date2){
        long date1InMillis = date1.getTimeInMillis();
        long date2InMillis = date2.getTimeInMillis();
        return date1InMillis - date2InMillis;
    }

    public static Calendar endDateFromDuration(Calendar startDate, long duration){
        Calendar endDate = Calendar.getInstance();
        long startDateInMillis = startDate.getTimeInMillis();
        long endDateInMillis = startDateInMillis + duration;
        endDate.setTimeInMillis(endDateInMillis);
        return endDate;
    }
}
