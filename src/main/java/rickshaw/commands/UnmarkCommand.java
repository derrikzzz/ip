package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;

public class UnmarkCommand extends Command {
    private final int taskIndex;

  /**
   * Constructs a new UnmarkCommand with the given task index
   * @param taskIndex
   */
  public UnmarkCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  /**
   * Marks task as undone and display unmarked task message and save tasks to storage
   * @param tasks The task list
   * @param ui The UI component
   * @param storage The storage component
   */
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task unmarkedtask = tasks.unmarkTask(taskIndex);
    ui.showUnmarkedTask(unmarkedtask);
    saveTasks(tasks, storage, ui);
  }
}
