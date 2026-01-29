package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a new DeleteCommand with the given task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(deletedTask, tasks.size());
        saveTasks(tasks, storage, ui);
    }
}
