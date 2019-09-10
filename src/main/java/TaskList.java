import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor for a TaskList object that is represented by a new empty ArrayList of Tasks.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Constructor for a TaskList object that is represented by an existing ArrayList of Tasks.
     *
     * @param tasklist list of tasks to be represented.
     */
    public TaskList(List<Task> tasklist) {
        list = tasklist;
    }

    /**
     * Get the list of tasks as a List<Task>.
     *
     * @return List<Task> representing the list of tasks.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Checks number of tasks in the list.
     *
     * @return number of tasks in the list in total.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Represent the list as a few lines of strings,
     * each representing a task.
     *
     * @return String that represents the details of all tasks.
     */
    public String getListAsString() {
        String listString = "";
        for (int i = 0; i < getSize(); i++) {
            Task t = list.get(i);
            String taskWithStatus = t.toString();
            if (i == (getSize() - 1)) {
                listString += ((i+1) + "." + taskWithStatus);
            } else {
                listString += ((i+1) + "." + taskWithStatus + "\n");
            }
        }
        return listString;
    }

    /**
     * Get an individual task indicated at a particular index
     *
     * @param index index that the task to be retrieved is at
     * @return Task object that has been identified
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Appends a Task object into the task list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Deletes a Task object from the task list.
     *
     * @param t Task to be deleted.
     */
    public void delete(Task t) {
        list.remove(t);
    }


}
