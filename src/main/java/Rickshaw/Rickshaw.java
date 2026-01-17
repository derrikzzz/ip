package Rickshaw;

public class Rickshaw {
    private final Ui ui;
    private final Parser parser;

    public Rickshaw(String name) {
        this.ui = new Ui(name);
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();

            try {
                Command parsedCommand = parser.parse(command);
                parsedCommand.run(ui);

                if (parsedCommand.getType() == CommandType.BYE) {
                    isExit = true;
                }
            } catch (RickshawException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Rickshaw("Rickshaw Bot").run();
    }


}