package rickshaw.task;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if done, empty string otherwise.
     */
    public String getIcon() {
        return this.isDone ? "X" : "";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getIcon(), this.description);
    }

    /**
     * Converts the task to a file format string.
     *
     * @return The file format string.
     */
    public abstract String toFileFormat();
}
