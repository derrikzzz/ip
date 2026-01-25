package rickshaw.commands;

import rickshaw.Ui;
import rickshaw.Storage;
import rickshaw.TaskList;

public class ByeCommand extends Command {
  @Override
  public void run(TaskList tasks, Ui ui, Storage storage) {
    ui.showExitMessage();
  }
}
