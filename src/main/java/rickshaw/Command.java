package rickshaw;

import rickshaw.task.Deadline;
import rickshaw.task.Task;
import rickshaw.task.Todo;
import rickshaw.task.Event;
import java.io.IOException;

public class Command {
    private final CommandType type;
    private final String[] args;

    public Command(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    public Command(CommandType type) {
        this.type = type;
        this.args = new String[0];
    }

    public void run(TaskList tasks, Ui ui, Storage storage) {
        switch (type) {
            case BYE:
                ui.showExitMessage();
                break;
            case LIST:
                ui.showList(tasks.getTasks());
                break;
            case MARK:
                Task markedTask = tasks.markTask(Integer.parseInt(args[0]));
                ui.showMarkedTask(markedTask);
                saveTasks(tasks, storage, ui);
                break;
            case UNMARK:
                Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(args[0]));
                ui.showUnmarkedTask(unmarkedTask);
                saveTasks(tasks, storage, ui);
                break;
            case DELETE:
                Task deletedTask = tasks.deleteTask(Integer.parseInt(args[0]));
                ui.showTaskDeleted(deletedTask, tasks.size());
                saveTasks(tasks, storage, ui);
                break;
            case TODO:
                Todo newTodo = new Todo(args[0]);
                tasks.addTask(newTodo);
                ui.showTaskAdded(newTodo, tasks.size());
                saveTasks(tasks, storage, ui);
                break;
            case DEADLINE:
                Deadline newDeadline = new Deadline(args[0], args[1]);
                tasks.addTask(newDeadline);
                ui.showTaskAdded(newDeadline, tasks.size());
                saveTasks(tasks, storage, ui);
                break;
            case EVENT:
                Event newEvent = new Event(args[0], args[1], args[2]);
                tasks.addTask(newEvent);
                ui.showTaskAdded(newEvent, tasks.size());
                saveTasks(tasks, storage, ui);
                break;
        }
    }
    //Command will decide whether to save tasks after each task change
    private void saveTasks(TaskList tasks, Storage storage, Ui ui) {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    public CommandType getType() {
        return this.type;
    }

}
