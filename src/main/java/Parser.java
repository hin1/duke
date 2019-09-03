import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private String command;
    private String[] arguments;

    public Parser() {
        command = null;
        arguments = null;
    }

    public Parser(String command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String parse(String inputString, TaskList tasklist) throws DukeException {

        String[] inst = inputString.split(" ");
        command = inst[0];
        arguments = Arrays.copyOfRange(inst,1,inst.length);

        String output;

        switch(command) {
            case "list":

                String tasks = tasklist.getListAsString();
                output = "Here are the tasks in your list:\n" + tasks;
                break;

            case "todo":
                if (arguments.length == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    String todo = String.join(" ", arguments);
                    ToDo newTodo = new ToDo(todo);
                    tasklist.add(newTodo);

                    output =  ("Got it. I've added this task:\n"
                               + "  " + newTodo.toString()
                               + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                }
                break;

            case "deadline":
                if (arguments.length == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    int dueStart = Arrays.asList(arguments).indexOf("/by");

                    if (dueStart == -1) {
                        throw new DukeException("Deadline date and time cannot be identified. Try again!");
                    } else {
                        String[] dueArray = Arrays.copyOfRange(arguments, dueStart + 1, arguments.length);
                        String[] descArray = Arrays.copyOfRange(arguments, 0, dueStart);
                        String due = String.join(" ", dueArray);
                        String desc = String.join(" ", descArray);

                        Deadline newDeadline = new Deadline(desc, due);
                        tasklist.add(newDeadline);
                        output = ("Got it. I've added this task:\n"
                                + "  " + newDeadline.toString()
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                    }
                }
                break;

            case "event":
                if (arguments.length == 0) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else {
                    int atStart = Arrays.asList(arguments).indexOf("/at");

                    if (atStart == -1) {
                        throw new DukeException("Event date and time cannot be identified. Try again!");
                    } else {
                        String[] atArray = Arrays.copyOfRange(arguments, atStart + 1, arguments.length);
                        String[] eventArray = Arrays.copyOfRange(arguments, 0, atStart);
                        String at = String.join(" ", atArray);
                        String event = String.join(" ", eventArray);

                        Event newEvent = new Event(event, at);
                        tasklist.add(newEvent);
                        output = ("Got it. I've added this task:\n"
                                + "  " + newEvent.toString()
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                    }
                }
                break;


            case "done":
                if (arguments.length == 0) {
                    throw new DukeException("Task number not specified.");
                } else {
                    Task doneTask = tasklist.getTask(Integer.parseInt(arguments[0]) - 1);
                    doneTask.markAsDone();
                    output = ("Nice! I've marked this task as done:\n" + "  " + doneTask.toString());
                }
                break;

            case "delete":
                if (arguments.length == 0) {
                    throw new DukeException("Task number not specified.");
                } else {
                    try {
                        Task taskToDelete = tasklist.getTask(Integer.parseInt(arguments[0]) - 1);
                        tasklist.delete(taskToDelete);

                        output = ("Noted. I've removed this task:\n"
                                + "  " + taskToDelete.toString()
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e1) {
                        throw new DukeException("Task number is out of bounds.");
                    }
                }
                break;

            case "find":
                if (arguments.length == 0) {
                    throw new DukeException("Keyword not specified.");
                } else {
                    String keyword = arguments[0];
                    TaskList tasksFound = new TaskList();
                    for (Task t : tasklist.getList()) {
                        String desc = t.getDescription();
                        if (desc.contains(keyword)) {
                            tasksFound.add(t);
                        }
                    }

                    output = ("Here are the matching tasks in your list:\n" + tasksFound.getListAsString());
                }
                break;


            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");

        }

        return output;
    }
}
