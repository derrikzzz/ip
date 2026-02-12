package rickshaw;

import rickshaw.commands.ByeCommand;
import rickshaw.commands.Command;
import rickshaw.commands.DeadlineCommand;
import rickshaw.commands.DeleteCommand;
import rickshaw.commands.EventCommand;
import rickshaw.commands.FindCommand;
import rickshaw.commands.ListCommand;
import rickshaw.commands.MarkCommand;
import rickshaw.commands.TagCommand;
import rickshaw.commands.TodoCommand;
import rickshaw.commands.UnmarkCommand;
import rickshaw.commands.UntagCommand;

/**
 * Parses inputs from user and returns appropriate Command object.
 */
public class Parser {
    private static final int TODO_COMMAND_LENGTH = "todo".length();
    private static final int EVENT_COMMAND_LENGTH = "event".length();
    private static final int DEADLINE_COMMAND_LENGTH = "deadline".length();
    private static final int MIN_SEGMENT_COUNT = 2;
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
        case "TAG":
            return parseTag(argument);
        case "UNTAG":
            return parseUntag(argument);
        default:
            throw new RickshawException("I do not understand this command...");
        }
    }

    /**
     * Parses the mark command argument.
     *
     * @param argument The task number string.
     * @return A MarkCommand for the specified task.
     * @throws RickshawException If the argument is empty.
     */
    private Command parseMark(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to mark. Usage: mark <task number>");
        }
        try {
            return new MarkCommand(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + argument + "' is not a valid task number. Usage: mark <task number>");
        }
    }

    /**
     * Parses the unmark command argument.
     *
     * @param argument The task number string.
     * @return An UnmarkCommand for the specified task.
     * @throws RickshawException If the argument is empty.
     */
    private Command parseUnmark(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to unmark. Usage: unmark <task number>");
        }
        try {
            return new UnmarkCommand(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + argument + "' is not a valid task number. Usage: unmark <task number>");
        }
    }

    /**
     * Parses the delete command argument.
     *
     * @param argument The task number string.
     * @return A DeleteCommand for the specified task.
     * @throws RickshawException If the argument is empty.
     */
    private Command parseDelete(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number to delete. Usage: delete <task number>");
        }
        try {
            return new DeleteCommand(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + argument + "' is not a valid task number. Usage: delete <task number>");
        }
    }

    /**
     * Parses the find command argument.
     *
     * @param argument The keyword to search for.
     * @return A FindCommand with the specified keyword.
     * @throws RickshawException If the argument is empty.
     */
    private Command parseFind(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a keyword to search for. Usage: find <keyword>");
        }
        return new FindCommand(argument);
    }

    /**
     * Parses the tag command argument.
     *
     * @param argument The task number and tag string.
     * @return A TagCommand for the specified task and tag.
     * @throws RickshawException If the argument format is invalid.
     */
    private Command parseTag(String argument) throws RickshawException {
        String[] parts = argument.split(" ", 2);
        if (parts.length < MIN_SEGMENT_COUNT || parts[1].trim().isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: tag <task number> <tag>");
        }
        try {
            int taskIndex = Integer.parseInt(parts[0]);
            String tag = parts[1].trim();
            return new TagCommand(taskIndex, tag);
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + parts[0] + "' is not a valid task number. Usage: tag <task number> <tag>");
        }
    }

    /**
     * Parses the untag command argument.
     *
     * @param argument The task number and tag string.
     * @return An UntagCommand for the specified task and tag.
     * @throws RickshawException If the argument format is invalid.
     */
    private Command parseUntag(String argument) throws RickshawException {
        String[] parts = argument.split(" ", 2);
        if (parts.length < MIN_SEGMENT_COUNT || parts[1].trim().isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: untag <task number> <tag>");
        }
        try {
            int taskIndex = Integer.parseInt(parts[0]);
            String tag = parts[1].trim();
            return new UntagCommand(taskIndex, tag);
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + parts[0] + "' is not a valid task number. Usage: untag <task number> <tag>");
        }
    }

    /**
     * Parses todo command from the input string.
     *
     * @param trimmedCommand The input string from user.
     * @return Appropriate Command object for todo task.
     * @throws RickshawException If the input is invalid.
     */
    public Command parseTodo(String trimmedCommand) throws RickshawException {
        String description = trimmedCommand.substring(TODO_COMMAND_LENGTH).trim();
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
        String payload = trimmedCommand.substring(DEADLINE_COMMAND_LENGTH).trim();
        String[] segments = payload.split(" /by ");
        if (segments.length < MIN_SEGMENT_COUNT) {
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
        String payload = trimmedCommand.substring(EVENT_COMMAND_LENGTH).trim();
        String[] fromSplit = payload.split(" /from ");
        if (fromSplit.length < MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "I recognise that you want to add an event task, but the format is incorrect. "
                    + "Usage: event <description> /from <start> /to <end>");
        }

        String description = fromSplit[0];
        String timeInfo = fromSplit[1];

        String[] timeSplit = timeInfo.split(" /to ");
        if (timeSplit.length < MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "I recognise that you want to add an event task, but the format is incorrect. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        String from = timeSplit[0];
        String to = timeSplit[1];

        return new EventCommand(description, from, to);
    }
}
