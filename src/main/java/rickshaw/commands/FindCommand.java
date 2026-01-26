package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
       ArrayList<Task> foundTasks = tasks.find(keyword);
       ui.showFind(foundTasks);
    }
}
