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
        content = Arrays.asList(list);
    }

    //Method 1
    List<String> getContent() {
        return content;
    }

    //Method 2
    void setContent(String str) {
        String[] list = str.split("\\r?\\n");
        content = Arrays.asList(list);
    }

    //Method 3
    void addArray(List<String> list) {
        content = list;
    }

    //Method 4
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
