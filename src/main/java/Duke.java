import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showWelcome();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        Parser parser = new Parser();
        String inputString = ui.readCommand();
        String outputMessage;

        if (inputString.equals("bye")) {

            outputMessage = "Bye. Hope to see you again soon!";
            ui.print(outputMessage);

        } else {
            //Parse and execute the command
            try {
                outputMessage = parser.parse(inputString, tasks);
            } catch (DukeException e){
                outputMessage = e.getMessage();
            }

            //Output result
            ui.print(outputMessage);

            //Save file
            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.print("Invalid file to save into!");
            }

            //Recurse run again
            run();
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/seanchan/duke/data/duke.txt").run();
    }

/*
    //private static void handleCommand(Scanner scan, List<Task> tasklist, Response reply, Storage storage) {
    private static void handleCommand(Scanner scan, TaskList tasklist, Response reply, Storage storage) {
        String input;

        while(!(input = scan.nextLine()).equals("bye")) {

            String[] inst = input.split(" ");
            String command = inst[0];
            String[] arg = Arrays.copyOfRange(inst,1,inst.length);

            switch(command) {
                case "list":
                    reply.clearContent();
                    reply.setContent("Here are the tasks in your list:\n");
                    reply.addTasks(tasklist.getList());
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
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                        reply.print();
                    }
                    break;

                case "deadline":
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        reply.clearContent();
                        int dueStart = Arrays.asList(arg).indexOf("/by");

                        if (dueStart == -1) {
                            try {
                                throw new DukeException("Deadline date and time cannot be identified. Try again!");
                            } catch (DukeException e) {
                                e.print();
                                handleCommand(scan, tasklist, reply, storage);
                            }
                        }

                        String[] dueArray = Arrays.copyOfRange(arg, dueStart + 1, arg.length);
                        String[] descArray = Arrays.copyOfRange(arg, 0, dueStart);
                        String due = String.join(" ", dueArray);
                        String desc = String.join(" ", descArray);

                        Deadline newDeadline = new Deadline(desc, due);
                        tasklist.add(newDeadline);
                        reply.setContent("Got it. I've added this task:\n"
                                + "  " + newDeadline.toString()
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                        reply.print();
                    }
                    break;

                case "event":
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("The description of an event cannot be empty.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        reply.clearContent();
                        int atStart = Arrays.asList(arg).indexOf("/at");

                        if (atStart == -1) {
                            try {
                                throw new DukeException("Event date and time cannot be identified. Try again!");
                            } catch (DukeException e) {
                                e.print();
                                handleCommand(scan, tasklist, reply, storage);
                            }
                        }

                        String[] atArray = Arrays.copyOfRange(arg, atStart + 1, arg.length);
                        String[] eventArray = Arrays.copyOfRange(arg, 0, atStart);
                        String at = String.join(" ", atArray);
                        String event = String.join(" ", eventArray);

                        Event newEvent = new Event(event, at);
                        tasklist.add(newEvent);
                        reply.setContent("Got it. I've added this task:\n"
                                + "  " + newEvent.toString()
                                + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                        reply.print();
                    }
                    break;

                case "done":
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("Task number not specified.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        Task doneTask = tasklist.getTask(Integer.parseInt(arg[0]) - 1);
                        doneTask.markAsDone();

                        String doneMsg = "Nice! I've marked this task as done:\n";
                        reply.setContent(doneMsg + "  " + doneTask.toString());
                        reply.print();
                    }
                    break;

                case "delete":
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("Task number not specified.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        try {
                            Task taskToDelete = tasklist.getTask(Integer.parseInt(arg[0]) - 1);
                            tasklist.delete(taskToDelete);

                            reply.setContent("Noted. I've removed this task:\n"
                                    + "  " + taskToDelete.toString()
                                    + "\nNow you have " + tasklist.getSize() + " tasks in the list.");
                            reply.print();
                        } catch (IndexOutOfBoundsException e1) {
                            Response message = new Response("Task number is out of bounds.");
                            message.print();
                        }
                    }
                    break;

                case "find":
                    if (arg.length == 0) {
                        try {
                            throw new DukeException("Keyword not specified.");
                        } catch (DukeException e) {
                            e.print();
                        }
                    } else {
                        String keyword = arg[0];
                        List<Task> tasksFound = new ArrayList<Task>();
                        for (Task t : tasklist.getList()) {
                            String desc = t.getDescription();
                            if (desc.contains(keyword)) {
                                tasksFound.add(t);
                            }
                        }

                        reply.setContent("Here are the matching tasks in your list:\n");
                        reply.addTasks(tasksFound);
                        reply.print();
                    }

                    break;

                default:
                    try {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        e.print();
                    }
            }

            try {
                //writeTasks("/Users/seanchan/duke/data/duke.txt",tasklist);
                storage.save(tasklist);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
        //List<Task> tasklist = null; //ADD FILEPATH
        Storage storage = new Storage("/Users/seanchan/duke/data/duke.txt");
        try {
            //tasklist = getTasks("/Users/seanchan/duke/data/duke.txt");
            //tasklist = storage.load();
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Response reply = new Response();
        handleCommand(scan, tasks, reply, storage);

        reply.setContent("Bye. Hope to see you again soon!");
        reply.print();
    }

 */

}
