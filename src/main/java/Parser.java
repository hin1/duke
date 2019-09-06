import java.util.Arrays;

public class Parser {

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
