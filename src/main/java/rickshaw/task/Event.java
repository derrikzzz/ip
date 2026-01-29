package rickshaw.task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description.
     *
     * @param description The description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param description The description of the event.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.from = fromTime;
        this.to = toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
