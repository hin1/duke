import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    private static List<Task> getTasks(String filePath) throws FileNotFoundException {
        List<Task> tasklist = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while(s.hasNext()) {
            String task = s.nextLine();
            String[] taskArray = task.split(" \\| ");

            Task newTask = null;

            if (taskArray[0].equals("T")) {
                newTask = new ToDo(taskArray[taskArray.length - 1]);
            } else if (taskArray[0].equals("D")) {
                newTask = new Deadline(taskArray[taskArray.length - 2], taskArray[taskArray.length - 1]);
            } else if (taskArray[0].equals("E")) {
                newTask = new Event(taskArray[taskArray.length - 2], taskArray[taskArray.length - 1]);
            } else {
                try {
                    throw new DukeException("Invalid task type");
                } catch (DukeException e) {
                    e.print();
                }
            }
            //Check if task is done
            if (taskArray[1].equals("1")) {
                newTask.markAsDone();
            }
            tasklist.add(newTask);
        }

        return tasklist;
    }

    private static void writeTasks(String filePath, List<Task> tasklist) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task t: tasklist) {

            String[] lineArray = new String[4];

            //Check type of task
            if (t instanceof ToDo) {
                lineArray[0] = "T";
            } else if (t instanceof Deadline) {
                lineArray[0] = "D";
                lineArray[3] = ((Deadline) t).getBy();
            } else if (t instanceof Event) {
                lineArray[0] = "E";
                lineArray[3] = ((Event) t).getAt();
            } else {
                try {
                    throw new DukeException("Invalid Task: " + t.getDescription());
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }

            //Check if task is done
            if (t.getStatusIcon() == "\u2713") {
                lineArray[1] = "1";
            } else if (t.getStatusIcon() == "\u2718") {
                lineArray[1] = "0";
            } else {
                try {
                    throw new DukeException("Invalid status of task: " + t.getDescription());
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }

            //Check description
            lineArray[2] = t.getDescription();

            //Combine
            if (lineArray[3] == null) {
                lineArray = Arrays.copyOfRange(lineArray, 0, 3);
            }
            String line = String.join(" | ",lineArray);
            fw.write(line);
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        Response intro = new Response("Hello! I'm Duke\n" + "What can I do for you?");
        intro.print();

        //INPUT
        Scanner scan = new Scanner(System.in);

        //Save (Level 7)

        //Read file
        List<Task> tasklist = null; //ADD FILEPATH
        try {
            tasklist = getTasks("/Users/seanchan/duke/data/duke.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //List<Task> tasklist = new ArrayList<Task>();

        Response reply = new Response();
        String input;

        while(!(input = scan.nextLine()).equals("bye")) {

            String[] inst = input.split(" ");
            String command = inst[0];
            String[] arg = Arrays.copyOfRange(inst,1,inst.length);

            switch(command) {
                case "list":
                    reply.clearContent();
                    reply.setContent("Here are the tasks in your list:\n");
                    reply.addTasks(tasklist);
                    reply.print();
                    break;

                case "todo":
                    reply.clearContent();
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("The description of a todo cannot be empty.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        String todo = String.join(" ", arg);
                        ToDo newTodo = new ToDo(todo);
                        tasklist.add(newTodo);

                        reply.setContent("Got it. I've added this task:\n"
                                + "  " + newTodo.toString()
                                + "\nNow you have " + tasklist.size() + " tasks in the list.");
                        reply.print();
                    }
                    break;

                case "deadline":
                    reply.clearContent();
                    int dueStart = Arrays.asList(arg).indexOf("/by");
                    String[] dueArray = Arrays.copyOfRange(arg, dueStart + 1, arg.length);
                    String[] descArray = Arrays.copyOfRange(arg, 0, dueStart);
                    String due = String.join(" ", dueArray);
                    String desc = String.join(" ", descArray);

                    Deadline newDeadline = new Deadline(desc,due);
                    tasklist.add(newDeadline);
                    reply.setContent("Got it. I've added this task:\n"
                                   + "  " + newDeadline.toString()
                                   + "\nNow you have " + tasklist.size() + " tasks in the list.");
                    reply.print();
                    break;

                case "event":
                    reply.clearContent();
                    int atStart = Arrays.asList(arg).indexOf("/at");
                    String[] atArray = Arrays.copyOfRange(arg, atStart + 1, arg.length);
                    String[] eventArray = Arrays.copyOfRange(arg, 0, atStart);
                    String at = String.join(" ", atArray);
                    String event = String.join(" ", eventArray);

                    Event newEvent = new Event(event,at);
                    tasklist.add(newEvent);
                    reply.setContent("Got it. I've added this task:\n"
                                    + "  " + newEvent.toString()
                                    + "\nNow you have " + tasklist.size() + " tasks in the list.");
                    reply.print();
                    break;

                case "done":
                    Task doneTask = tasklist.get(Integer.parseInt(arg[0]) - 1);
                    doneTask.markAsDone();

                    String doneMsg = "Nice! I've marked this task as done:\n";
                    reply.setContent(doneMsg + "  " + doneTask.toString());
                    reply.print();
                    break;

                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        e.print();
                    }
            }

            try {
                writeTasks("/Users/seanchan/duke/data/duke.txt",tasklist);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        reply.setContent("Bye. Hope to see you again soon!");
        reply.print();
    }
}
