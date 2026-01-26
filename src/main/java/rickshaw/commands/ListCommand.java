package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;

public class ListCommand extends Command {
  /**
   * Displays the list of tasks
   * @param tasks The task list
   * @param ui The UI component
   * @param storage The storage component
   */
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    ui.showList(tasks.getTasks());
  }
}
