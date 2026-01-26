package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;

public class MarkCommand extends Command {
    private final int taskIndex;
  /**
   * Constructs a new MarkCommand with the given task index
   * @param taskIndex
   */
  public MarkCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  /**
   * Marks task as done and display marked task message and save tasks to storage
   * @param tasks The task list
   * @param ui The UI component
   * @param storage The storage component
   */
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task markedtask = tasks.markTask(taskIndex);
    ui.showMarkedTask(markedtask);
    saveTasks(tasks, storage, ui);
  }
}
