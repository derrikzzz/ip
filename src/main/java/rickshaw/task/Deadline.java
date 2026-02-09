package rickshaw.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected LocalDateTime by;
    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date string in format "d/MM/yyyy HHmm".
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Invalid date format: '" + by + "'. Please use the format: d/MM/yyyy HHmm (e.g., 1/12/2024 1800)",
                e
            );
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(OUTPUT_FORMATTER));
    }

    @Override
    public String toFileFormat() {
        String base = String.format("D | %s | %s | %s", (isDone ? "1" : "0"),
                description, by.format(INPUT_FORMATTER));
        return tags.isEmpty() ? base : base + " | " + String.join(",", tags);
    }
}
