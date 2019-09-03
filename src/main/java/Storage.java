import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String file;

    public Storage(String filepath)  {
        file = filepath;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasklist = new ArrayList<Task>();
        try {
            File f = new File(file);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] taskArray = task.split(" \\| ");

                Task newTask = null;

                switch (taskArray[0]) {
                    case "T":
                        newTask = new ToDo(taskArray[taskArray.length - 1]);
                        break;
                    case "D":
                        newTask = new Deadline(taskArray[taskArray.length - 2], taskArray[taskArray.length - 1]);
                        break;
                    case "E":
                        newTask = new Event(taskArray[taskArray.length - 2], taskArray[taskArray.length - 1]);
                        break;
                    default:
                        throw new DukeException("Invalid task type");
                }
                //Check if task is done
                try {
                    if (taskArray[1].equals("1")) {
                        newTask.markAsDone();
                    }
                } catch (NullPointerException e) {
                    throw new DukeException("No indicator of whether task is done.");
                }

                tasklist.add(newTask);
            }

            return tasklist;

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found! Try again!");
        }
    }

    public void save(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(file);

        for (Task t: tasklist.getList()) {

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
            if (t.getStatusIcon().equals("\u2713")) {
                lineArray[1] = "1";
            } else if (t.getStatusIcon().equals("\u2718")) {
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
}
