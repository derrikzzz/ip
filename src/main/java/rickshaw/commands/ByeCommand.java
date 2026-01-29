package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}
