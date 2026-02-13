package rickshaw;

import java.util.ArrayList;

import rickshaw.task.Task;

/**
 * Represents the task list.
 * Handles adding, getting, marking, unmarking, deletion and retrieving size of task list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the specified tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask The task to add.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Gets all tasks from the task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Validates that the given 1-based task index is within range.
     *
     * @param index The 1-based index to validate.
     * @throws RickshawException If the index is out of range.
     */
    public void validateIndex(int index) throws RickshawException {
        if (index < 1 || index > tasks.size()) {
            throw new IllegalArgumentException("Task index " + index + " is out of range. "
                    + "You have " + tasks.size() + " task(s).");
        }
    }

    /**
     * Gets a task from the task list.
     *
     * @param index The index of the task.
     * @return The task.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task.
     * @return The task.
     */
    public Task markTask(int index) {
        Task task = this.getTask(index - 1);
        task.markDone();
        return task;
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task.
     * @return The task.
     */
    public Task unmarkTask(int index) {
        Task task = this.getTask(index - 1);
        task.markUndone();
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task.
     * @return The task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index - 1);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets all tasks from the task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks from the task list that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
