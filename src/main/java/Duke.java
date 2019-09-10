/**
 * Main interface which controls the flow of Duke's operations.
 */
public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke object which prepares the setup such as
     * the user interface, loading up or creating a new task list
     *
     * @param filePath file as a string to read an existing task list from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke interface of receiving inputs from the user, parsing them,
     * and executing the relevant commands with respect to the task list, and
     * responding to the user about the outcome.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main program to call the relevant functions for Duke to function
     *
     * @param args command line arguments (not used in this case)
     */
    public static void main(String[] args) {
        new Duke("/Users/seanchan/duke/data/duke.txt").run();
    }

}
