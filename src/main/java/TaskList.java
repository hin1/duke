import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasklist) {
        list = tasklist;
    }

    public List<Task> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

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

    public Task getTask(int index) {
        return list.get(index);
    }

    public void add(Task t) {
        list.add(t);
    }

    public void delete(Task t) {
        list.remove(t);
    }


}
