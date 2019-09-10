/**
 *  Represents a type of Task that includes additional information
 *  which is date and time when the particular task is to be done
 */
public class Event extends Task {

    private String at;

    /**
     * Constructor for an Event object which contains the event description
     * and date and time that it is on.
     *
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date and time that the event is on.
     *
     * @return date and time of the event
     */
    public String getAt() {
        return at;
    }

    /**
     * Represents the details of the event as a string (status, description, date and time)
     *
     * @return event details as a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
