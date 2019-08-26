import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Response {

    //Instance variables (Attributes)
    private List<String> content;

    //Constructors
    Response() {
        this.content = new ArrayList<String>();
    }

    Response(String str) {
        String[] list = str.split("\\r?\\n");
        List<String> stringList = new ArrayList<String>(Arrays.asList(list));
        content = Arrays.asList(list);
    }

    List<String> getContent() {
        return content;
    }

    void setContent(String str) {
        String[] list = str.split("\\r?\\n");
        List<String> stringList = new ArrayList<String>(Arrays.asList(list));
        content = stringList;
    }

    void clearContent() {
        content.clear();
    }

    void addArray(List<String> list) {
        content = list;
    }

    void addTasks(List<Task> tasklist) {
        for (int i = 0; i < tasklist.size(); i++) {
            Task t = tasklist.get(i);
            String taskWithStatus = t.getTaskWithStatus();
            content.add((i+1) + "." + taskWithStatus);
        }
    }

    void print() {
        String wrap = "-";
        String wrapper = "    " + wrap.repeat(60);

        System.out.println(wrapper);
        for (String s : content) {
            System.out.println("    " + s);
        }
        System.out.println(wrapper);
    }


}
