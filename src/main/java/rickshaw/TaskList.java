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
     * @throws RickshawException If a duplicate task already exists in the list.
     */
    public void addTask(Task newTask) throws RickshawException {
        for (Task task : tasks) {
            if (task.equals(newTask)) {
                throw new RickshawException("This task already exists in the list: " + newTask.getDescription());
            }
        }
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
        if (tasks.isEmpty()) {
            throw new RickshawException(
                    "Your task list is empty. Add some tasks first before using this command.");
        }
        if (index < 1 || index > tasks.size()) {
            throw new RickshawException("Task index " + index + " is out of range. "
                    + "You have " + tasks.size() + " task(s). Please use an index between 1 and "
                    + tasks.size() + ".");
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
     * @throws RickshawException If the task is already marked as done.
     */
    public Task markTask(int index) throws RickshawException {
        Task task = this.getTask(index - 1);
        if (task.isDone()) {
            throw new RickshawException("This task is already marked as done: " + task);
        }
        task.markDone();
        return task;
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task.
     * @return The task.
     * @throws RickshawException If the task is already marked as not done.
     */
    public Task unmarkTask(int index) throws RickshawException {
        Task task = this.getTask(index - 1);
        if (!task.isDone()) {
            throw new RickshawException("This task is already marked as not done: " + task);
        }
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

    /**
     * Finds tasks from the task list that contain the keyword, with their original indices.
     *
     * @param keyword The keyword to search for.
     * @return A list of arrays where each array contains [originalIndex, task].
     */
    public ArrayList<Object[]> findWithIndices(String keyword) {
        ArrayList<Object[]> foundTasksWithIndices = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                foundTasksWithIndices.add(new Object[]{i + 1, task}); // Store 1-based index
            }
        }
        return foundTasksWithIndices;
    }
}
