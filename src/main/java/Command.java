/**
 * An abstract class that defines the command, and is extended into multiple types of commands
 * that are available to the user.
 */
public abstract class Command {
    /**
     * Abstract method where a command is executed in their specific implementation.
     *
     * @param tasks list of tasks as reference or to change
     * @param ui user interface to give response to the user
     * @param storage storage to save or load list of tasks externally
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is a ByeCommand object.
     *
     * @return true if command is ByeCommand
     */
    public abstract boolean isExit();
}
