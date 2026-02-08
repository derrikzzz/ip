package rickshaw.commands;

import rickshaw.RickshawException;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to remove a tag from a task.
 */
public class UntagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs an UntagCommand with the specified task index and tag.
     *
     * @param taskIndex The 1-based index of the task to untag.
     * @param tag The tag to remove.
     */
    public UntagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    private void validateIndex(TaskList tasks) throws RickshawException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new RickshawException("Task index " + taskIndex + " is out of range. "
                    + "You have " + tasks.size() + " task(s).");
        }
    }

    private void removeTagOrThrow(Task task) throws RickshawException {
        if (!task.getTags().contains(tag)) {
            throw new RickshawException("Task " + taskIndex + " does not have the tag '" + tag + "'.");
        }
        task.removeTag(tag);
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws RickshawException {
        validateIndex(tasks);
        Task task = tasks.getTask(taskIndex - 1);
        removeTagOrThrow(task);
        ui.showUntaggedTask(task);
        saveTasks(tasks, storage, ui);
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) throws RickshawException {
        validateIndex(tasks);
        Task task = tasks.getTask(taskIndex - 1);
        removeTagOrThrow(task);
        saveTasks(tasks, storage);
        return "OK, I've untagged this task:\n" + task;
    }
}
