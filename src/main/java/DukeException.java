import java.util.Scanner;

public class DukeException extends Exception {

    public enum errorType {
        EMPTYTASK;
    }

    protected Response exceptionReply = new Response();

    public DukeException(String errorMessage) {
        exceptionReply.setContent("\u2639 " + "OOPS!!! " + errorMessage);
    }

    public DukeException(errorType e) {
        switch (e) {
            case EMPTYTASK:
                exceptionReply.setContent("\u2639 " + "OOPS!!! " + "This task description cannot be empty!");
        }
    }

    public void print() {
        exceptionReply.print();
    }

    public void scanNewObject(Object o) {
        Scanner newDateTimeScan = new Scanner(System.in);
        o = newDateTimeScan.nextLine();
    }

}
