import java.io.IOException;

public class DeleteCommand extends Command {

    private String[] arguments;
    private Task taskToDelete;

    public DeleteCommand(String[] arguments) {
        this.arguments = arguments;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
