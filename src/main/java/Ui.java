import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner input;
    //private List<String> content;

    public Ui() {
        input = new Scanner(System.in);
        //content = new ArrayList<String>();
    }

    public void print(String message) {
        String[] content = message.split("\\r?\\n");
        String wrap = "-";
        String wrapper = "    " + wrap.repeat(100);

        System.out.println(wrapper);

        for (String s : content) {
            System.out.println("    " + s);
        }

        System.out.println(wrapper);
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void showLoadingError() {
        print("Lists of tasks cannot be properly loaded.");
    }
}

