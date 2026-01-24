package rickshaw;

public class Rickshaw {
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;
    private final Storage storage;
    
    public Rickshaw(String name) {
        this.ui = new Ui(name);
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage("data/rickshaw.txt");
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