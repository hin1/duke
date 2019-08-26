public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTaskWithStatus() {
        String checkbox = "[" + this.getStatusIcon() + "]";
        return (checkbox + " " + description);
    }

    public void markAsDone() {
        isDone = true;
    }
}
