/**
 * A command to terminate Duke once the user is done.
 */
public class ByeCommand extends Command {

    /**
     * Empty constructor as it serves to terminate Duke.
     */
    public ByeCommand() {}

    /**
     * Executes the command to terminate Duke.
     *
     * @param tasks task list to refer to tasks from.
     * @param ui user interface to print goodbye message.
     * @param storage storage to save task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true if it is a ByeCommand object.
     *
     * @return true since it is a ByeCommand
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
