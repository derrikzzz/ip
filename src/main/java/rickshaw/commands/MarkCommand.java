package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        Task markedTask = tasks.markTask(taskIndex);
        ui.showMarkedTask(markedTask);
        saveTasks(tasks, storage, ui);
    }
}
