public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String checkbox = "[" + this.getStatusIcon() + "]";
        return (checkbox + " " + description);
    }

    public void markAsDone() {
        isDone = true;
    }
}
