public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String tasksAsString = tasks.getListAsString();
        ui.print("Here are the tasks in your list:\n" + tasksAsString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
