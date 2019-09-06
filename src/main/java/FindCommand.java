public class FindCommand extends Command {

    String wordsToFind;

    public FindCommand(String[] arguments) throws DukeException {
        if (arguments.length == 0) {
            throw new DukeException("Keyword not specified.");
        } else {
            wordsToFind = String.join(" ", arguments);
        }
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
