package rickshaw.commands;

import rickshaw.Storage;
import rickshaw.TaskList;
import rickshaw.Ui;

/**
 * Represents a command to display help information.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE =
            "\u2550\u2550\u2550 Available Commands \u2550\u2550\u2550\n"
            + "\n"
            + "\u25b6 Adding Tasks:\n"
            + "  todo <desc>\n"
            + "  deadline <desc> /by <d/MM/yyyy HHmm>\n"
            + "  event <desc> /from <d/MM/yyyy HHmm>\n"
            + "                /to <d/MM/yyyy HHmm>\n"
            + "\n"
            + "\u25b6 Managing Tasks:\n"
            + "  list              View all tasks\n"
            + "  mark <number>     Mark as done\n"
            + "  unmark <number>   Mark as not done\n"
            + "  delete <number>   Delete a task\n"
            + "  find <keyword>    Search tasks\n"
            + "\n"
            + "\u25b6 Tagging:\n"
            + "  tag <number> <tag>    Add a tag\n"
            + "  untag <number> <tag>  Remove a tag\n"
            + "\n"
            + "\u25b6 Other:\n"
            + "  help    Show this message\n"
            + "  bye     Exit";

    /**
     * Returns the help message string.
     *
     * @return The help message.
     */
    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.showEchoMessage(HELP_MESSAGE);
    }

    @Override
    public String returnStringResponse(TaskList tasks, Storage storage) {
        return HELP_MESSAGE;
    }
}
