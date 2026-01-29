package rickshaw.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
