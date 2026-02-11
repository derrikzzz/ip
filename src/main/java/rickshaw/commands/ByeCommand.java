package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Displays the exit message to the user.
     *
     * @param tasks   The task list.
     * @param ui      The UI component.
     * @param storage The storage component.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns the exit message string for the GUI.
     *
     * @param tasks   The task list.
     * @param storage The storage component.
     * @return The exit message string.
     */
    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
