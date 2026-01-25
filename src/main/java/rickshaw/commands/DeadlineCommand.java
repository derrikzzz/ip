package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.task.Task;
import rickshaw.task.Deadline;

public class DeadlineCommand extends Command {
  private final String description;
  private final String by;

  public DeadlineCommand(String description, String by) {
    this.description = description;
    this.by = by;
  }

  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    Task newDeadline = new Deadline(description, by);
    tasks.addTask(newDeadline);
    ui.showTaskAdded(newDeadline, tasks.size());
    saveTasks(tasks, storage, ui);
  }
}
