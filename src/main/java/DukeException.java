import java.util.Scanner;

public class DukeException extends Exception {

    public enum errorType {
        EMPTYTASK
    }

    private String message = "\u2639 " + "OOPS!!! ";

    //protected Response exceptionReply = new Response();

    public DukeException(String errorMessage) {
        message += errorMessage;
        //exceptionReply.setContent("\u2639 " + "OOPS!!! " + errorMessage);
    }

    public String getMessage() {
        return message;
    }

}
