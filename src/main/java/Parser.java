import java.util.Arrays;

/**
 * Represents the intermediary that parses input from the user into system understood commands.
 */
public class Parser {

    /**
     * Returns the proper command that has been given by the user along with the relevant
     * arguments.
     *
     * @param commandString input from the user
     * @return command that is interpreted
     * @throws DukeException
     */
     public static Command parse(String commandString) throws DukeException {
            String[] inst = commandString.split(" ");
            String command = inst[0];
            String[] arguments = Arrays.copyOfRange(inst,1,inst.length);

            if (command.startsWith("todo") || command.startsWith("event") || command.startsWith("deadline")) {
                return new AddCommand(command, arguments);
            } else if (command.startsWith("list")) {
                return new ListCommand();
            } else if (command.startsWith("delete")) {
                return new DeleteCommand(arguments);
            } else if (command.startsWith("done")) {
                return new DoneCommand(arguments);
            } else if (command.startsWith("find")) {
                return new FindCommand(arguments);
            } else if (command.startsWith("bye")) {
                return new ByeCommand();
            } else {
                throw new DukeException("Invalid command");
            }
        }

}
