package Rickshaw;

import Rickshaw.task.Deadline;
import Rickshaw.task.Task;
import Rickshaw.task.Todo;
import Rickshaw.task.Event;

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

    public void run(TaskList tasks, Ui ui) {
        switch (type) {
            case BYE:
                break;
            case LIST:
                ui.showList(tasks.getTasks());
                break;
            case MARK:
                Task markedTask = tasks.markTask(Integer.parseInt(args[0]));
                ui.showMarkedTask(markedTask);
                break;
            case UNMARK:
                Task unmarkedTask = tasks.unmarkTask(Integer.parseInt(args[0]));
                ui.showUnmarkedTask(unmarkedTask);
                break;
            case DELETE:
                Task deletedTask = tasks.deleteTask(Integer.parseInt(args[0]));
                ui.showTaskDeleted(deletedTask, tasks.size());
                break;
            case TODO:
                Todo newTodo = new Todo(args[0]);
                tasks.addTask(newTodo);
                ui.showTaskAdded(newTodo, tasks.size());
                break;
            case DEADLINE:
                Deadline newDeadline = new Deadline(args[0], args[1]);
                tasks.addTask(newDeadline);
                ui.showTaskAdded(newDeadline, tasks.size());
                break;
            case EVENT:
                Event newEvent = new Event(args[0], args[1], args[2]);
                tasks.addTask(newEvent);
                ui.showTaskAdded(newEvent, tasks.size());
        }
    }

    public CommandType getType() {
        return this.type;
    }

}

