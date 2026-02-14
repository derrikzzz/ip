package rickshaw.task;

import java.util.Objects;

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
        String base = "T | " + (isDone ? "1" : "0") + " | " + description;
        return tags.isEmpty() ? base : base + " | " + String.join(",", tags);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) object;
        return this.description.equals(otherTodo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, tags);
    }
}
