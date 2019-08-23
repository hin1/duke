import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        Response intro = new Response("Hello! I'm Duke\n" + "What can I do for you?");
        intro.print();

        //INPUT
        Scanner scan = new Scanner(System.in);

        //ADD LIST (LEVEL 2)
        Response reply = new Response();
        String input;
        int size = 1;
        List<String> list = new ArrayList<String>();

        while(!(input = scan.nextLine()).equals("bye")) {

            reply.setContent(input);

            switch(input) {
                case "list":
                    reply.addArray(list);
                    reply.print();
                    break;
                default:
                    list.add(Integer.toString(size) + ". " + input);
                    reply.setContent("Added: " + input);
                    reply.print();
                    size++;
                    break;
            }

        }
        reply.setContent("Bye. Hope to see you again soon!");
        reply.print();


    }
}
