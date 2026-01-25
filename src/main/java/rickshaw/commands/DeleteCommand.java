package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;

public class DeleteCommand extends Command {
  private final int taskIndex;

  public DeleteCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task deletedtask = tasks.deleteTask(taskIndex);
    ui.showTaskDeleted(deletedtask, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}
