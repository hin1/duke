public class DukeException extends Exception {

    protected Response exceptionReply = new Response();

    public DukeException(String errorMessage) {
        exceptionReply.setContent("\u2639 " + "OOPS!!! " + errorMessage);
        //System.out.println("\u2639" + errorMessage);
    }

    public void print() {
        exceptionReply.print();
    }

}
