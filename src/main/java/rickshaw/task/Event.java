package rickshaw.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param description The description of the event.
     * @param fromTime The start time of the event in format "d/MM/yyyy HHmm".
     * @param toTime The end time of the event in format "d/MM/yyyy HHmm".
     * @throws IllegalArgumentException if the date format is invalid or start is not before end.
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        try {
            this.from = LocalDateTime.parse(fromTime, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Invalid start date format: '" + fromTime
                    + "'. Please use the format: d/MM/yyyy HHmm", e);
        }
        try {
            this.to = LocalDateTime.parse(toTime, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Invalid end date format: '" + toTime
                    + "'. Please use the format: d/MM/yyyy HHmm", e);
        }
        if (!this.from.isBefore(this.to)) {
            throw new IllegalArgumentException(
                "The start time (" + this.from.format(OUTPUT_FORMATTER)
                    + ") must be before the end time (" + this.to.format(OUTPUT_FORMATTER) + ").");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER)
                + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        String base = "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.format(INPUT_FORMATTER) + " | " + to.format(INPUT_FORMATTER);
        return tags.isEmpty() ? base : base + " | " + String.join(",", tags);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) object;
        return this.description.equals(otherEvent.getDescription())
                && this.from.equals(otherEvent.from)
                && this.to.equals(otherEvent.to)
                && this.isDone == otherEvent.isDone
                && this.tags.equals(otherEvent.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, from, to, isDone, tags);
    }
}
