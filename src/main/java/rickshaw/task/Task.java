package rickshaw.task;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
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

    /**
     * Gets the tags of the task.
     *
     * @return The tags.
     */
    public Set<String> getTags() {
        return this.tags;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to add.
     * @return The tags.
     */
    public Set<String> addTag(String tag) {
        this.tags.add(tag);
        return this.tags;
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag The tag to remove.
     * @return The tags.
     */
    public Set<String> removeTag(String tag) {
        this.tags.remove(tag);
        return this.tags;
    }

    /**
     * Sets the tags of the task.
     *
     * @param tags The tags to set.
     * @return The tags.
     */
    public Set<String> setTags(Set<String> tags) {
        this.tags = tags;
        return this.tags;
    }

    @Override
    public String toString() {
        String tagString = tags.isEmpty() ? "" : " " + tags.stream()
                .map(tag -> "#" + tag)
                .reduce((t1, t2) -> t1 + " " + t2)
                .orElse("");
        return String.format("[%s] %s%s", this.getIcon(), this.description, tagString);
    }

    /**
     * Converts the task to a file format string.
     *
     * @return The file format string.
     */
    public abstract String toFileFormat();
}
