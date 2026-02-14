package rickshaw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import rickshaw.task.Deadline;
import rickshaw.task.Event;
import rickshaw.task.Task;
import rickshaw.task.Todo;

/**
 * Represents the storage component.
 * Handles the loading and saving of tasks to a file (in data/rickshaw.txt).
 */
public class Storage {
    protected static final int MIN_PARTS_COUNT = 3;
    private static final String DELIMITER = " \\| ";
    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file (in data/rickshaw.txt).
     * Corrupted lines are skipped with warnings instead of failing the entire load.
     *
     * @return The list of tasks.
     * @throws IOException If the file cannot be read.
     * @throws RickshawException If the file exists but cannot be read, or all lines are corrupted.
     */
    public ArrayList<Task> load() throws IOException, RickshawException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!f.exists()) {
            return tasks;
        }
        if (!f.canRead()) {
            throw new RickshawException("Cannot read file: " + filePath
                    + ". Please check file permissions.");
        }

        int lineNumber = 0;
        ArrayList<String> warnings = new ArrayList<>();
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                lineNumber++;
                String line = s.nextLine();
                try {
                    process(line, tasks, lineNumber);
                } catch (RickshawException e) {
                    warnings.add("Line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RickshawException("Error loading file: " + e.getMessage());
        }

        if (!warnings.isEmpty() && tasks.isEmpty()) {
            throw new RickshawException("All lines in the data file are corrupted. "
                    + "Warnings:\n" + String.join("\n", warnings));
        }

        return tasks;
    }

    /**
     * Process line from the file and add to the task list.
     *
     * @param line The line to process.
     * @param tasks The task list to add to.
     * @throws RickshawException If the line is corrupted.
     */
    private void process(String line, ArrayList<Task> tasks, int lineNumber) throws RickshawException {
        if (line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.split(DELIMITER);

        // Validate minimum parts
        if (parts.length < MIN_PARTS_COUNT) {
            throw new RickshawException("Corrupted line %d (not enough fields): %s",
                    lineNumber, line);
        }

        String type = parts[0];
        String status = parts[1]; // "1" for done, "0" for not done
        String description = parts[2];

        if (!status.equals("0") && !status.equals("1")) {
            throw new RickshawException("Corrupted line %d (invalid status '%s'): %s",
                    lineNumber, status, line);
        }

        Task t = null;
        String tagsField = "";

        switch (type) {
        case "T":
            t = new Todo(description);
            tagsField = parts.length > 3 ? parts[3] : "";
            break;
        case "D":
            if (parts.length < 4) {
                throw new RickshawException("Corrupted line %d (deadline missing date): %s",
                        lineNumber, line);
            }
            try {
                t = new Deadline(description, parts[3]);
            } catch (IllegalArgumentException e) {
                throw new RickshawException("Corrupted line %d (invalid deadline date): %s",
                        lineNumber, line);
            }
            tagsField = parts.length > 4 ? parts[4] : "";
            break;
        case "E":
            if (parts.length < 5) {
                throw new RickshawException("Corrupted line %d (event missing times): %s",
                        lineNumber, line);
            }
            try {
                t = new Event(description, parts[3], parts[4]);
            } catch (IllegalArgumentException e) {
                throw new RickshawException("Corrupted line %d (invalid event dates): %s",
                        lineNumber, line);
            }
            tagsField = parts.length > 5 ? parts[5] : "";
            break;
        default:
            throw new RickshawException("Corrupted line %d (unknown task type '%s'): %s",
                    lineNumber, type, line);
        }
        if (status.equals("1")) {
            t.markDone();
        }
        if (!tagsField.trim().isEmpty()) {
            String[] tagArray = tagsField.split(",");
            Set<String> loadedTags = new HashSet<>();
            for (String tag : tagArray) {
                String trimmedTag = tag.trim();
                if (trimmedTag.contains("|")) {
                    throw new RickshawException(
                            "Corrupted line %d (tag contains reserved '|' character): %s",
                            lineNumber, line);
                }
                if (!trimmedTag.isEmpty()) {
                    loadedTags.add(trimmedTag);
                }
            }
            t.setTags(loadedTags);
        }
        tasks.add(t);
    }

    /**
     * Saves the tasks to the file (in data/rickshaw.txt).
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If the file cannot be written.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        Path path = Paths.get(filePath);
        Path directoryPath = path.getParent();
        if (directoryPath != null) {
            Files.createDirectories(directoryPath);
        }

        File f = new File(filePath);
        if (f.exists() && !f.canWrite()) {
            throw new IOException("Cannot write to file: " + filePath
                    + ". Please check file permissions.");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new IOException("Error saving file: " + e.getMessage(), e);
        }
    }
}
