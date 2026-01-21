package rickshaw.task;

public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description) {
        super(description);
    }

    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.from = fromTime;
        this.to = toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
