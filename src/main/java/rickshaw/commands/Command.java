package rickshaw.commands;

import java.io.IOException;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;

/**
 * Represents an executable command in the Rickshaw application.
 * Base class where all subclasses of commands inherit from.
 */
public abstract class Command {
    /**
     * Executes the command.
     * Concrete implementation delegated to subclasses.
     *
     * @param tasks   The task list to execute the command on.
     * @param ui      The UI component for displaying messages / error messages.
     * @param storage The storage component.
     */
    public abstract void run(TaskList tasks, Ui ui, Storage storage);

    /**
     * Saves the current task list to storage. (in data/rickshaw.txt)
     *
     * @param tasks   The task list to save.
     * @param storage The storage component.
     * @param ui      The UI component for displaying messages / error messages.
     */
    protected void saveTasks(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }
}
