package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a new UnmarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as undone and display unmarked task message and save tasks to storage.
     *
     * @param tasks The task list.
     * @param ui The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task unmarkedtask = tasks.unmarkTask(taskIndex);
        ui.showUnmarkedTask(unmarkedtask);
        saveTasks(tasks, storage, ui);
    }
}
