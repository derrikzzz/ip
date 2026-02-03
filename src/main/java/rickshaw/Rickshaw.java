package rickshaw;

import java.io.IOException;

import rickshaw.commands.ByeCommand;
import rickshaw.commands.Command;

/**
 * Main class for the Rickshaw chatbot application.
 * Handles initialization and main program loop.
 */
public class Rickshaw {
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a Rickshaw chatbot with the specified name.
     *
     * @param name The name of the chatbot.
     */
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

    /**
     * Runs the main program loop.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();

            try {
                Command parsedCommand = parser.parse(command);
                parsedCommand.run(tasks, ui, storage);

                if (parsedCommand instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (RickshawException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's input.
     *
     * @param input The user's input string.
     * @return The response string from Rickshaw.
     */
    public String getResponse(String input) {
        try {
            Command parsedCommand = parser.parse(input);
            return parsedCommand.returnStringResponse(tasks, storage);
        } catch (RickshawException e) {
            return e.getMessage();
        }
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Rickshaw("Rickshaw Bot").run();
    }
}
