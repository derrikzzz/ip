package rickshaw;

import java.io.IOException;

public class Rickshaw {
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;
    private final Storage storage;
    
    public Rickshaw(String name) {
        this.ui = new Ui(name);
        this.parser = new Parser();
        this.storage = new Storage("data/rickshaw.txt");

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showErrorMessage("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList();
        } catch (RickshawException e) {
            ui.showErrorMessage("Corrupted data file: " + e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();

            try {
                Command parsedCommand = parser.parse(command);
                parsedCommand.run(tasks, ui, storage);

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