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

    /**
     * Deletes a task from the task list, displays deletion message and saves tasks to storage.
     *
     * @param tasks   The task list.
     * @param ui      The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(deletedTask, tasks.size());
        saveTasks(tasks, storage, ui);
    }

    /**
     * Returns a response string after deleting a task.
     *
     * @param tasks   The task list.
     * @param storage The storage component.
     * @return The response string confirming deletion.
     */
    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        saveTasks(tasks, storage);
        return "Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
