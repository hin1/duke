import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.LocalDateTime;

/**
 * Represents a type of Task that includes additional information
 * which is date and time when the particular task is due
 */
public class Deadline extends Task{

    private String by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description description of the task that is due
     * @param by date and/or time that the task is due, which can be in the format d/M/yyyy HHmm
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date and time when the task is due.
     *
     * @return deadline date and time
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the date and time in prose as a string if it was in the format of
     * d/M/yyyy HHmm, if not valid, then just as the string it was before.
     *
     * @param dateTime date and time when the task is due
     * @return formatted date and time when task is due as a string
     */
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
            String time;
            String minute = String.format("%02d",dateTimeFormatted.getMinute());
            int hour = dateTimeFormatted.getHour();
            if (hour == 0) {
                time =  "12." + minute + "am";
            } else if (hour >= 12) {
                time = (hour - 12) + "." + minute + "pm";
            } else { // (hour < 12)
                time = hour + "." + minute + "am";
            }

            return (day + " of " + month + " " + year + ", " + time);

        } catch (DateTimeException e1) {

            return by;

        }
    }

    /**
     * Returns a String of the Deadline object as it would be represented in the task list.
     *
     * @return Deadline details as represented in the task list as a String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime(by) + ")";
    }
}
