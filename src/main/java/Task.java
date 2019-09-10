/**
 * A task that can be marked as done and has a short description.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task object that has a short description.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the particular task.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task, whether its done or not
     *
     * @return tick if its done, cross if its not
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Represents the details of the task as a string (status, description)
     *
     * @return task details as a string
     */
    @Override
    public String toString() {
        String checkbox = "[" + this.getStatusIcon() + "]";
        return (checkbox + " " + description);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }
}
