import java.io.IOException;
import java.util.Arrays;

public class AddCommand extends Command {

    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private Task taskToAdd;

    public AddCommand(String keyword, String[] arguments) throws DukeException {
        if (arguments.length == 0) {
            throw new DukeException("The description of the " + keyword + " cannot be empty.");
        }

        switch(keyword) {
            case "todo":
                String todo = String.join(" ", arguments);
                taskToAdd = new ToDo(todo);
                break;


            case "deadline":
                int dueStart = Arrays.asList(arguments).indexOf("/by");

                if (dueStart == -1) { //No deadline
                    throw new DukeException("Deadline date and time cannot be identified. Try again!");
                } else {
                    String[] dueArray = Arrays.copyOfRange(arguments, dueStart + 1, arguments.length);
                    String[] descArray = Arrays.copyOfRange(arguments, 0, dueStart);
                    String due = String.join(" ", dueArray);
                    String desc = String.join(" ", descArray);
                    taskToAdd = new Deadline(desc, due);
                }
                break;

            case "event":
                int atStart = Arrays.asList(arguments).indexOf("/at");

                if (atStart == -1) {
                    throw new DukeException("Event date and time cannot be identified. Try again!");
                } else {
                    String[] atArray = Arrays.copyOfRange(arguments, atStart + 1, arguments.length);
                    String[] eventArray = Arrays.copyOfRange(arguments, 0, atStart);
                    String at = String.join(" ", atArray);
                    String event = String.join(" ", eventArray);
                    taskToAdd = new Event(event, at);
                }
                break;

            default:
                throw new DukeException("Invalid type of task!");

        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        //Add task details to tasklist
        tasks.add(taskToAdd);

        //Print out result
        ui.print("Got it. I've added this task:\n"
                + "  " + taskToAdd.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");

        //Save list
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("Invalid file to save into!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
