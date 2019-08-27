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

                case "todo":
                    reply.clearContent();
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        String todo = String.join(" ", arg);
                        ToDo newTodo = new ToDo(todo);
                        tasklist.add(newTodo);

                        reply.setContent("Got it. I've added this task:\n"
                                + "  " + newTodo.toString()
                                + "\nNow you have " + tasklist.size() + " tasks in the list.");
                        reply.print();
                    }
                    break;

                case "deadline":
                    reply.clearContent();
                    int dueStart = Arrays.asList(arg).indexOf("/by");
                    String[] dueArray = Arrays.copyOfRange(arg, dueStart + 1, arg.length);
                    String[] descArray = Arrays.copyOfRange(arg, 0, dueStart);
                    String due = String.join(" ", dueArray);
                    String desc = String.join(" ", descArray);

                    Deadline newDeadline = new Deadline(desc,due);
                    tasklist.add(newDeadline);
                    reply.setContent("Got it. I've added this task:\n"
                                   + "  " + newDeadline.toString()
                                   + "\nNow you have " + tasklist.size() + " tasks in the list.");
                    reply.print();
                    break;

                case "event":
                    reply.clearContent();
                    int atStart = Arrays.asList(arg).indexOf("/at");
                    String[] atArray = Arrays.copyOfRange(arg, atStart + 1, arg.length);
                    String[] eventArray = Arrays.copyOfRange(arg, 0, atStart);
                    String at = String.join(" ", atArray);
                    String event = String.join(" ", eventArray);

                    Event newEvent = new Event(event,at);
                    tasklist.add(newEvent);
                    reply.setContent("Got it. I've added this task:\n"
                                    + "  " + newEvent.toString()
                                    + "\nNow you have " + tasklist.size() + " tasks in the list.");
                    reply.print();
                    break;

                case "done":
                    Task doneTask = tasklist.get(Integer.parseInt(arg[0]) - 1);
                    doneTask.markAsDone();

                    String doneMsg = "Nice! I've marked this task as done:\n";
                    reply.setContent(doneMsg + "  " + doneTask.toString());
                    reply.print();
                    break;

                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        e.print();
                    }
            }

        }
        reply.setContent("Bye. Hope to see you again soon!");
        reply.print();
    }
}
