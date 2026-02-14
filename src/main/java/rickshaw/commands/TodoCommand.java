package rickshaw.commands;

import rickshaw.RickshawException;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;
import rickshaw.task.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a new TodoCommand with given description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new todo task to the task list, display task added message and save tasks to storage.
     *
     * @param tasks The task list.
     * @param ui The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws RickshawException {
        Task newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showTaskAdded(newTodo, tasks.size());
        saveTasks(tasks, storage, ui);
    }

    /**
     * Returns a response string after adding a todo task.
     *
     * @param tasks   The task list.
     * @param storage The storage component.
     * @return The response string confirming the todo was added.
     */
    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) throws RickshawException {
        Task newTodo = new Todo(description);
        tasks.addTask(newTodo);
        saveTasks(tasks, storage);
        return "Got it. I've added this task:\n  " + newTodo
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
