package rickshaw.commands;

import java.util.ArrayList;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks.
     *
     * @param tasks The task list.
     * @param ui The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        ArrayList<Task> taskList = tasks.getTasks();
        if (taskList.isEmpty()) {
            return "Your list is currently empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.get(i));
            if (i < taskList.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
