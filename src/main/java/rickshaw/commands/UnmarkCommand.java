package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task unmarkedTask = tasks.unmarkTask(taskIndex);
        ui.showUnmarkedTask(unmarkedTask);
        saveTasks(tasks, storage, ui);
    }
}
