import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        //INPUT
        Scanner scan = new Scanner(System.in);

        //ECHO FUNCTION
        Response reply = new Response("");
        String input;
        while(!(input = scan.nextLine()).equals("bye")) {
            reply.edit(input);
            reply.print();
        }
        reply.edit("Bye. Hope to see you again soon!");
        reply.print();

    }
}
