/**
 * A command that finds tasks (todos, events and deadlines) from a task list within Duke.
 */
public class FindCommand extends Command {

    String wordsToFind;

    /**
     * Constructor for an FindCommand object that contains a key string to
     * to search for in the task list.
     *
     * @param arguments keywords to search for within the task list.
     */
    public FindCommand(String[] arguments) throws DukeException {
        if (arguments.length == 0) {
            throw new DukeException("Keyword not specified.");
        } else {
            wordsToFind = String.join(" ", arguments);
        }
    }

    /**
     * Executes the command to search for the task in relevance
     * to the key string given.
     *
     * @param tasks list of tasks to search from
     * @param ui user interface to give response to the user
     * @param storage storage to save or load list of tasks externally
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList tasksFound = new TaskList();
        for (Task t : tasks.getList()) {
            String desc = t.getDescription();
            if (desc.contains(wordsToFind)) {
                tasksFound.add(t);
            }
        }
        ui.print("Here are the matching tasks in your list:\n" + tasksFound.getListAsString());
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
