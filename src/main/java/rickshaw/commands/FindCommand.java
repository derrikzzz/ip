package rickshaw.commands;

import java.util.ArrayList;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import rickshaw.task.Task;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        ui.showFindResults(foundTasks);
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(foundTasks.get(i));
            if (i < foundTasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
