/**
 * Represents a type of Task which only has description.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo object which contains the task description
     *
     * @param description description of the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Represents the details of the todo as a string (status, description)
     *
     * @return todo details as a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
