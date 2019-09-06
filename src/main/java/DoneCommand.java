import java.io.IOException;

public class DoneCommand extends Command {

    private Task taskDone;
    private String[] arguments;

    public DoneCommand(String[] arguments) {
        this.arguments = arguments;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
