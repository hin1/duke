import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getDateTime(String dateTime) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTimeFormatted = LocalDateTime.parse(dateTime, formatter);

            //Date
            String year = Integer.toString(dateTimeFormatted.getYear());
            String month = dateTimeFormatted.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String day = Integer.toString(dateTimeFormatted.getDayOfMonth());
            if (day.endsWith("1")) {
                day = day + "st";
            } else if (day.endsWith("2")) {
                day = day + "nd";
            } else if (day.endsWith("3")) {
                day = day + "rd";
            } else {
                day = day + "th";
            }

            //Time
            String time = "13.00pm";
            String minute = String.format("%02d",dateTimeFormatted.getMinute());
            int hour = dateTimeFormatted.getHour();
            if (hour == 0) {
                time =  "12." + minute + "am";
            } else if ((hour >= 12) && (hour < 24)) {
                time = (hour - 12) + "." + minute + "pm";
            } else if (hour < 12) {
                time = hour + "." + minute + "am";
            }

            return (day + " of " + month + " " + year + ", " + time);

        } catch (DateTimeException e1) {
            //e1.printStackTrace();
            //Response errorMessage = new Response("Invalid date and time format");
            //errorMessage.print();
            return by;

        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime(by) + ")";
    }
}
