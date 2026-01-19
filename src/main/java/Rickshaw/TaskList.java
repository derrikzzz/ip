package Rickshaw;

import Rickshaw.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public Task markTask(int index) {
        Task task = this.getTask(index - 1);
        task.markDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = this.getTask(index - 1);
        task.markUndone();
        return task;
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index - 1);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }


}
