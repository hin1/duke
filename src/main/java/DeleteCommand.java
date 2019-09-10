import java.io.IOException;

/**
 * A command that deletes tasks (todos, events and deadlines) from a task list within Duke.
 */
public class DeleteCommand extends Command {

    private String[] arguments;
    private Task taskToDelete;

    /**
     * Constructor for an DeleteCommand object that contains a task to be deleted
     * from the task list.
     *
     * @param arguments number of the task in the list to be deleted.
     */
    public DeleteCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command to delete the identified task from
     * the arguments from the list.
     *
     * @param tasks list of tasks to delete the task from
     * @param ui user interface to give response to the user
     * @param storage storage to save or load list of tasks externally
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.length == 0) {
            throw new DukeException("Task number not specified.");
        } else {
            try {
                taskToDelete = tasks.getTask(Integer.parseInt(arguments[0]) - 1);
                tasks.delete(taskToDelete);
                ui.print("Noted. I've removed this task:\n"
                        + "  " + taskToDelete.toString()
                        + "\nNow you have " + tasks.getSize() + " tasks in the list.");

                storage.save(tasks);
            } catch (IndexOutOfBoundsException e1) {
                throw new DukeException("Task number is out of bounds.");
            } catch (IOException e2) {
                throw new DukeException("Invalid file to save into!");
            }
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
