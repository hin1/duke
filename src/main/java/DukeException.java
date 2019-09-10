/**
 * The main Exception class that will be used to detect errors and circumvent them within Duke.
 */
public class DukeException extends Exception {

    private String message = "\u2639 " + "OOPS!!! ";

    /**
     * Constructor for a DukeException object that contains a relevant
     * error message for a particular situation.
     *
     * @param errorMessage message that contains the error.
     */
    public DukeException(String errorMessage) {
        message += errorMessage;
        //exceptionReply.setContent("\u2639 " + "OOPS!!! " + errorMessage);
    }

    public String getMessage() {
        return message;
    }

}
