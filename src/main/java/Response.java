class Response {

    //Instance variables (Attributes)
    private String content;

    //Constructor
    Response(String content) {
        this.content = content;
    }

    //Method 1
    void edit(String str) {
        content = str;
    }

    //Method 2
    void print() {
        String wrap = "-";
        String wrapper = "    ".concat(wrap.repeat(60));

        System.out.println(wrapper);
        System.out.println("    ".concat(content));
        System.out.println(wrapper);
    }
}
