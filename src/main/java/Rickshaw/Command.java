package Rickshaw;

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

    public void run(Ui ui) {
        switch (type) {
            case BYE:
                ui.showExitMessage();
                break;
            case LIST:
                ui.showList();
                break;
            case ECHO:
                ui.showEchoMessage(args[0]);
                break;
        }
    }

    public CommandType getType() {
        return this.type;
    }

}

