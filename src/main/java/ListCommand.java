/**
 * A command that list all tasks (todos, events and deadlines) from a task list within Duke.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list
     *
     * @param tasks list of tasks as reference or to change
     * @param ui user interface to give response to the user
     * @param storage storage to save or load list of tasks externally
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String tasksAsString = tasks.getListAsString();
        ui.print("Here are the tasks in your list:\n" + tasksAsString);
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
