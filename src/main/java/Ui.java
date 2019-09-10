import java.util.Scanner;

/**
 * Represents the user interface that the user inputs and receives responses from Duke.
 */
public class Ui {

    private Scanner input;

    /**
     * Constructor for a Ui object that creates a Scanner object for the user to type input.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Creates a line which is the top/bottom wrapper for the response message from Duke.
     */
    public void showLine() {
        String wrap = "-";
        String wrapper = "\t" + wrap.repeat(100);

        System.out.println(wrapper);
    }

    /**
     * Prints a response message from Duke.
     *
     * @param message the response message to be printed.
     */
    public void print(String message) {
        String[] content = message.split("\\r?\\n");
        for (String s : content) {
            System.out.println("\t" + s);
        }
    }

    /**
     * Gets the user inputted command to be parsed.
     *
     * @return command as a user input
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Shows the introduction of Duke.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        showLine();
        print("Hello! I'm Duke\n" + "What can I do for you?");
        showLine();
    }

    /**
     * Shows error message when the list of tasks cannot be loaded
     * from the external file.
     */
    public void showLoadingError() {
        showLine();
        print("Lists of tasks cannot be properly loaded.");
        showLine();
    }

    /**
     * Shows an error message when there is error in Duke.
     * @param errorMsg error message to be printed
     */
    public void showError(String errorMsg) {
        print(errorMsg);
    }
}

