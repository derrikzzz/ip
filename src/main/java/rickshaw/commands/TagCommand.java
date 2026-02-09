package rickshaw.commands;

import rickshaw.RickshawException;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;

/**
 * Represents a command to add a tag to a task.
 */
public class TagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs a TagCommand with the specified task index and tag.
     *
     * @param taskIndex The 1-based index of the task to tag.
     * @param tag The tag to add.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    private void validateIndex(TaskList tasks) throws RickshawException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new RickshawException("Task index " + taskIndex + " is out of range. "
                    + "You have " + tasks.size() + " task(s).");
        }
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws RickshawException {
        validateIndex(tasks);
        tasks.getTask(taskIndex - 1).addTag(tag);
        ui.showTaggedTask(tasks.getTask(taskIndex - 1));
        saveTasks(tasks, storage, ui);
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) throws RickshawException {
        validateIndex(tasks);
        tasks.getTask(taskIndex - 1).addTag(tag);
        saveTasks(tasks, storage);
        return "Added tag " + tag + " to task " + taskIndex;
    }
}
