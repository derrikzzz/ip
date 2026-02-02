package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Deadline;
import rickshaw.task.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a new DeadlineCommand with the given description and by.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Gets the description of the deadline.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the deadline date.
     *
     * @return The deadline date.
     */
    public String getBy() {
        return by;
    }

    /**
     * Adds a new deadline task to the task list, display task added message and save tasks to storage.
     *
     * @param tasks The task list.
     * @param ui The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.size());
        saveTasks(tasks, storage, ui);
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        Task newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        saveTasks(tasks, storage);
        return "Got it. I've added this task:\n  " + newDeadline
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
