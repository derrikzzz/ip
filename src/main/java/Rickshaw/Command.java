package Rickshaw;

import Rickshaw.task.Task;

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
                ui.showExitMessage();
                break;
            case LIST:
                ui.showList(tasks.getTasks());
                break;
            case MARK:
                tasks.getTask(Integer.parseInt(args[0])).markDone();
                break;
            case UNMARK:
                tasks.getTask(Integer.parseInt(args[0])).markUndone();
                break;
            case ECHO:
                String desc = args[0];
                Task newTask = new Task(desc);
                tasks.addTask(newTask);

                ui.showTaskAdded(desc);
                break;
        }
    }

    public CommandType getType() {
        return this.type;
    }

}

