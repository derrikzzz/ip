package rickshaw;

import rickshaw.commands.ByeCommand;
import rickshaw.commands.Command;
import rickshaw.commands.DeadlineCommand;
import rickshaw.commands.DeleteCommand;
import rickshaw.commands.EventCommand;
import rickshaw.commands.FindCommand;
import rickshaw.commands.ListCommand;
import rickshaw.commands.MarkCommand;
import rickshaw.commands.TodoCommand;
import rickshaw.commands.UnmarkCommand;

/**
 * Parses inputs from user and returns appropriate Command object.
 */
public class Parser {
    /**
     * Parses the input string from user and returns appropriate Command object.
     *
     * @param input The input string from user.
     * @return Appropriate Command object.
     * @throws RickshawException If the input is invalid.
     */
    public Command parse(String input) throws RickshawException {
        String trimmedCommand = input.trim();
        String[] parts = trimmedCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();
        String argument = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "MARK":
            return parseMark(argument);
        case "UNMARK":
            return parseUnmark(argument);
        case "DELETE":
            return parseDelete(argument);
        case "TODO":
            return parseTodo(trimmedCommand);
        case "DEADLINE":
            return parseDeadline(trimmedCommand);
        case "EVENT":
            return parseEvent(trimmedCommand);
        case "BYE":
            return new ByeCommand();
        case "LIST":
            return new ListCommand();
        case "FIND":
            return parseFind(argument);
        default:
            throw new RickshawException("I do not understand this command...");
        }
    }

    private Command parseMark(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to mark. Usage: mark <task number>");
        }
        return new MarkCommand(Integer.parseInt(argument));
    }

    private Command parseUnmark(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to unmark. Usage: unmark <task number>");
        }
        return new UnmarkCommand(Integer.parseInt(argument));
    }

    private Command parseDelete(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to delete. Usage: delete <task number>");
        }
        return new DeleteCommand(Integer.parseInt(argument));
    }

    private Command parseFind(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a keyword to search for. Usage: find <keyword>");
        }
        return new FindCommand(argument);
    }

    /**
     * Parses todo command from the input string.
     *
     * @param trimmedCommand The input string from user.
     * @return Appropriate Command object for todo task.
     * @throws RickshawException If the input is invalid.
     */
    public Command parseTodo(String trimmedCommand) throws RickshawException {
        String description = trimmedCommand.substring(4).trim();
        if (description.isEmpty()) {
            throw new RickshawException(
                    "Are you sure you want to add a todo task, "
                    + "the description of a todo task cannot be empty");
        }
        return new TodoCommand(description);
    }

    /**
     * Parses deadline command from the input string.
     *
     * @param trimmedCommand The input string from user.
     * @return Appropriate Command object for deadline task.
     * @throws RickshawException If the input is invalid.
     */
    public Command parseDeadline(String trimmedCommand) throws RickshawException {
        // Example of input: "deadline return book /by Sunday"
        String payload = trimmedCommand.substring(8).trim();
        String[] segments = payload.split(" /by ");
        if (segments.length < 2) {
            throw new RickshawException(
                    "I recognise that you want to add a deadline task, but the format is incorrect. "
                    + "Usage: deadline <description> /by <time>");
        }
        String description = segments[0];
        String doneBy = segments[1];
        return new DeadlineCommand(description, doneBy);
    }

    /**
     * Parses event command from the input string.
     *
     * @param trimmedCommand The input string from user.
     * @return Appropriate Command object for event task.
     * @throws RickshawException If the input is invalid.
     */
    public Command parseEvent(String trimmedCommand) throws RickshawException {
        String payload = trimmedCommand.substring(5).trim();
        String[] fromSplit = payload.split(" /from ");
        if (fromSplit.length < 2) {
            throw new RickshawException(
                    "I recognise that you want to add an event task, but the format is incorrect. "
                    + "Usage: event <description> /from <start> /to <end>");
        }

        String description = fromSplit[0];
        String timeInfo = fromSplit[1];

        String[] timeSplit = timeInfo.split(" /to ");
        if (timeSplit.length < 2) {
            throw new RickshawException(
                    "I recognise that you want to add an event task, but the format is incorrect. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        String from = timeSplit[0];
        String to = timeSplit[1];

        return new EventCommand(description, from, to);
    }
}
