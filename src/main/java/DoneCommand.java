import java.io.IOException;

/**
 * A command that marks tasks (todos, events and deadlines) as done in a task list within Duke.
 */
public class DoneCommand extends Command {

    private Task taskDone;
    private String[] arguments;

    /**
     * Constructor for an DoneCommand object that contains a task to be marked as done.
     *
     * @param arguments number of the task in the list to be marked as done
     */
    public DoneCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command to mark the identified task from the task list as done.
     *
     * @param tasks list of tasks as reference and to mark the task in question as done
     * @param ui user interface to give response to the user
     * @param storage storage to save or load list of tasks externally
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.length == 0) {
            throw new DukeException("Task number not specified.");
        } else {
            taskDone = tasks.getTask(Integer.parseInt(arguments[0]) - 1);
            taskDone.markAsDone();
            ui.print("Nice! I've marked this task as done:\n" + "  " + taskDone.toString());
        }

        //Save
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("Invalid file to save into!");
        }
    }

    /**
     * Returns true if the command is a ByeCommand object.
     *
     * @return false since command is not ByeCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
