package jeremy.code.starwars.Util;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static final String RESULT = "RESULT";

    @NonNull
    public static String convertCentimetersToMeters(@NonNull String centimeters) {
        if (centimeters != null) {
            int centimetersInteger = Integer.parseInt(centimeters);
            return String.valueOf(centimetersInteger / 100) +
                    "." +
                    centimetersInteger % 100 +
                    "m";
        }
        return null;
    }

    @NonNull
    public static String parseKilograms(@NonNull String mass) {
        if(mass != null) {
            return mass + "kg";
        }
        return null;
    }

    @NonNull
    public static String parseDate(@NonNull String dateString) {
        StringBuilder result = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault());
        try {
            Date date = format.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            result.append(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            result.append(" ");
            result.append(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
            result.append(" ");
            result.append(calendar.get(Calendar.DAY_OF_WEEK));
            result.append(", ");
            result.append(calendar.get(Calendar.HOUR));
            result.append(":");
            result.append(calendar.get(Calendar.MINUTE));
            result.append(" ");
            if(calendar.get(Calendar.AM_PM) == 1) {
                result.append("PM");
            } else {
                result.append("AM");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
