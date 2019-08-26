import java.util.ArrayList;
import java.util.Arrays;
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

        //Mark as Done (LEVEL 3)
        Response reply = new Response();
        String input;
        List<Task> tasklist = new ArrayList<Task>();

        while(!(input = scan.nextLine()).equals("bye")) {

            String[] inst = input.split(" ");
            String command = inst[0];
            String[] arg = Arrays.copyOfRange(inst,1,inst.length);

            switch(command) {
                case "list":
                    reply.clearContent();
                    reply.setContent("Here are the tasks in your list:\n");
                    reply.addTasks(tasklist);
                    reply.print();
                    break;
                case "done":
                    Task doneTask = tasklist.get(Integer.parseInt(arg[0]) - 1);
                    doneTask.markAsDone();

                    String doneMsg = "Nice! I've marked this task as done:\n";
                    reply.setContent(doneMsg + "  " + doneTask.getTaskWithStatus());
                    reply.print();
                    break;
                default:
                    Task newTask = new Task(input);
                    tasklist.add(newTask);
                    reply.setContent("added: " + input);
                    reply.print();
                    break;
            }

        }
        reply.setContent("Bye. Hope to see you again soon!");
        reply.print();
    }
}
