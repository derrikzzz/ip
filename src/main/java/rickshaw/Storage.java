package rickshaw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import rickshaw.task.Deadline;
import rickshaw.task.Event;
import rickshaw.task.Task;
import rickshaw.task.Todo;

/**
 * Represents the storage component
 * Handles the loading and saving of tasks to a file (in data/rickshaw.txt)
 */
public class Storage {
    private static final String DELIMITER = " \\| ";
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads the tasks from the file (in data/rickshaw.txt)
     * @return The list of tasks
     * @throws RickshawException If the file exists but cannot be read or parsed.
     */
    public ArrayList<Task> load() throws IOException, RickshawException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!f.exists()) {
            return tasks;
        }
        
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                process(line, tasks);
            }
        } catch (IOException e) {
            throw new RickshawException("Error loading file.");
        }
        return tasks;
    }

    /**
     * Process line from the file and add to the task list
     * @param line
     * @param tasks
     * @throws RickshawException
     */
    private void process(String line, ArrayList<Task> tasks) throws RickshawException {
        if (line.trim().isEmpty()) {
            return;
        }
        
        String[] parts = line.split(DELIMITER);
        
        // Validate minimum parts
        if (parts.length < 3) {
            throw new RickshawException("Corrupted line (not enough fields): " + line);
        }

        String type = parts[0];
        String status = parts[1]; // "1" for done, "0" for not done
        String description = parts[2];
        Task t = null;

        switch (type) {
        case "T":
            t = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new RickshawException("Corrupted deadline (missing date): " + line);
            }
            t = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                throw new RickshawException("Corrupted event (missing times): " + line);
            }
            t = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new RickshawException("Unknown task type: " + type);
        }
        if (status.equals("1")) {
            t.markDone();
        } else {
            t.markUndone();
        }
        tasks.add(t);
    }

    /**
     * Saves the tasks to the file (in data/rickshaw.txt)
     * @param tasks The list of tasks to save
     * @throws IOException If the file cannot be written
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try {
            Path path = Paths.get(filePath);
            Path directoryPath = path.getParent();
            if (directoryPath != null) {
                Files.createDirectories(directoryPath);
            }

            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


}
