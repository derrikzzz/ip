package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;

public class ListCommand extends Command {
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    ui.showList(tasks.getTasks());
  }
}
