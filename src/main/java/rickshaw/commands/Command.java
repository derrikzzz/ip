package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;
import java.io.IOException;

public abstract class Command {
    public abstract void run (TaskList tasks, Ui ui, Storage storage);
    protected void saveTasks(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

}
