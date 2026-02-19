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

    /**
     * Finds and displays tasks matching the keyword.
     *
     * @param tasks   The task list.
     * @param ui      The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Object[]> foundTasksWithIndices = tasks.findWithIndices(keyword);
        ui.showFindResultsWithIndices(foundTasksWithIndices);
    }

    /**
     * Returns a response string listing tasks that match the keyword.
     *
     * @param tasks   The task list.
     * @param storage The storage component.
     * @return The response string with matching tasks.
     */
    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        ArrayList<Object[]> foundTasksWithIndices = tasks.findWithIndices(keyword);
        if (foundTasksWithIndices.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasksWithIndices.size(); i++) {
            Object[] taskWithIndex = foundTasksWithIndices.get(i);
            int originalIndex = (Integer) taskWithIndex[0];
            Task task = (Task) taskWithIndex[1];
            sb.append(originalIndex).append(". ").append(task);
            if (i < foundTasksWithIndices.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
