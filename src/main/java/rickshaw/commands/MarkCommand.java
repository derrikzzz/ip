package rickshaw.commands;

import rickshaw.RickshawException;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a new MarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks task as done and display marked task message and save tasks to storage.
     *
     * @param tasks The task list.
     * @param ui The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws RickshawException {
        tasks.validateIndex(taskIndex);
        Task markedTask = tasks.markTask(taskIndex);
        ui.showMarkedTask(markedTask);
        saveTasks(tasks, storage, ui);
    }

    /**
     * Returns a response string after marking a task as done.
     *
     * @param tasks   The task list.
     * @param storage The storage component.
     * @return The response string confirming the task was marked.
     */
    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) throws RickshawException {
        tasks.validateIndex(taskIndex);
        Task markedTask = tasks.markTask(taskIndex);
        saveTasks(tasks, storage);
        return "Nice! I've marked this task as done:\n  " + markedTask;
    }
}
