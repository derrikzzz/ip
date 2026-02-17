package rickshaw;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import rickshaw.commands.ByeCommand;
import rickshaw.commands.Command;
import rickshaw.commands.DeadlineCommand;
import rickshaw.commands.DeleteCommand;
import rickshaw.commands.EventCommand;
import rickshaw.commands.FindCommand;
import rickshaw.commands.HelpCommand;
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
    private static final DateTimeFormatter STRICT_DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/uuuu HHmm");

    /**
     * Parses the input string from user and returns appropriate Command object.
     *
     * @param input The input string from user.
     * @return Appropriate Command object.
     * @throws RickshawException If the input is invalid.
     */
    public Command parse(String input) throws RickshawException {
        if (input == null || input.trim().isEmpty()) {
            throw new RickshawException("Please enter a command.");
        }
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
        case "HELP":
            return new HelpCommand();
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
     * Validates that a description does not contain the pipe character.
     *
     * @param description The description to validate.
     * @throws RickshawException If the description contains a pipe character.
     */
    private void validateDescription(String description) throws RickshawException {
        if (description.contains("|")) {
            throw new RickshawException(
                    "Description cannot contain the '|' character as it is reserved.");
        }
    }

    /**
     * Validates that a tag does not contain reserved characters.
     *
     * @param tag The tag to validate.
     * @throws RickshawException If the tag contains reserved characters.
     */
    private void validateTag(String tag) throws RickshawException {
        if (tag.contains("|") || tag.contains(",")) {
            throw new RickshawException(
                    "Tag cannot contain '|' or ',' characters as they are reserved.");
        }
    }

    /**
     * Parses a date/time string using strict validation (rejects non-existent dates like Feb 30).
     *
     * @param dateStr The date/time string to parse.
     * @param fieldName The name of the field for error messages.
     * @return The parsed LocalDateTime.
     * @throws RickshawException If the date format is invalid or the date doesn't exist.
     */
    private LocalDateTime parseDateTime(String dateStr, String fieldName) throws RickshawException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, STRICT_DATE_FORMATTER);
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new RickshawException(
                        "The %s cannot be in the past: '%s'.",
                        fieldName, dateStr);
            }
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new RickshawException(
                    "Invalid %s: '%s'. Please use the format: d/MM/yyyy HHmm",
                    fieldName, dateStr);
        }
    }

    /**
     * Parses a task index string and validates it is a positive integer.
     *
     * @param argument The string to parse.
     * @param commandName The command name for error messages.
     * @param usage The usage string for error messages.
     * @return The parsed positive integer.
     * @throws RickshawException If the argument is not a valid positive integer.
     */
    private int parsePositiveIndex(String argument, String commandName, String usage)
            throws RickshawException {
        try {
            int index = Integer.parseInt(argument);
            if (index <= 0) {
                throw new RickshawException(
                        "Task number must be a positive integer. Usage: " + usage);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new RickshawException(
                    "'" + argument + "' is not a valid task number. Usage: " + usage);
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
        if (argument.contains(" ")) {
            throw new RickshawException(
                    "Too many arguments. Usage: mark <task number>");
        }
        int index = parsePositiveIndex(argument, "mark", "mark <task number>");
        return new MarkCommand(index);
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
        if (argument.contains(" ")) {
            throw new RickshawException(
                    "Too many arguments. Usage: unmark <task number>");
        }
        int index = parsePositiveIndex(argument, "unmark", "unmark <task number>");
        return new UnmarkCommand(index);
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
        if (argument.contains(" ")) {
            throw new RickshawException(
                    "Too many arguments. Usage: delete <task number>");
        }
        int index = parsePositiveIndex(argument, "delete", "delete <task number>");
        return new DeleteCommand(index);
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
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: tag <task number> <tag>");
        }
        String[] parts = argument.split(" ", 2);
        if (parts.length < MIN_SEGMENT_COUNT || parts[1].trim().isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: tag <task number> <tag>");
        }
        int taskIndex = parsePositiveIndex(parts[0], "tag", "tag <task number> <tag>");
        String tag = parts[1].trim();
        validateTag(tag);
        return new TagCommand(taskIndex, tag);
    }

    /**
     * Parses the untag command argument.
     *
     * @param argument The task number and tag string.
     * @return An UntagCommand for the specified task and tag.
     * @throws RickshawException If the argument format is invalid.
     */
    private Command parseUntag(String argument) throws RickshawException {
        if (argument.isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: untag <task number> <tag>");
        }
        String[] parts = argument.split(" ", 2);
        if (parts.length < MIN_SEGMENT_COUNT || parts[1].trim().isEmpty()) {
            throw new RickshawException(
                    "Please provide a task number and tag. Usage: untag <task number> <tag>");
        }
        int taskIndex = parsePositiveIndex(parts[0], "untag", "untag <task number> <tag>");
        String tag = parts[1].trim();
        validateTag(tag);
        return new UntagCommand(taskIndex, tag);
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
        validateDescription(description);
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
        // Example of input: "deadline return book /by 1/12/2024 1800"
        String payload = trimmedCommand.substring(DEADLINE_COMMAND_LENGTH).trim();
        String[] segments = payload.split(" /by ");
        if (segments.length < MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "I recognise that you want to add a deadline task, but the format is incorrect. "
                    + "Usage: deadline <description> /by <time>");
        }
        if (segments.length > MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "Multiple '/by' delimiters found. "
                    + "Usage: deadline <description> /by <time>");
        }
        String description = segments[0].trim();
        String doneBy = segments[1].trim();
        if (description.isEmpty()) {
            throw new RickshawException(
                    "The description of a deadline task cannot be empty. "
                    + "Usage: deadline <description> /by <time>");
        }
        if (doneBy.isEmpty()) {
            throw new RickshawException(
                    "The deadline date/time cannot be empty. "
                    + "Usage: deadline <description> /by <time>");
        }
        validateDescription(description);
        // Validate date format early (catches non-existent dates like Feb 30)
        parseDateTime(doneBy, "deadline date/time");
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
        if (fromSplit.length > MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "Multiple '/from' delimiters found. "
                    + "Usage: event <description> /from <start> /to <end>");
        }

        String description = fromSplit[0].trim();
        String timeInfo = fromSplit[1];

        String[] timeSplit = timeInfo.split(" /to ");
        if (timeSplit.length < MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "I recognise that you want to add an event task, but the format is incorrect. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        if (timeSplit.length > MIN_SEGMENT_COUNT) {
            throw new RickshawException(
                    "Multiple '/to' delimiters found. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        String from = timeSplit[0].trim();
        String to = timeSplit[1].trim();

        if (description.isEmpty()) {
            throw new RickshawException(
                    "The description of an event task cannot be empty. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        if (from.isEmpty()) {
            throw new RickshawException(
                    "The start time of an event cannot be empty. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        if (to.isEmpty()) {
            throw new RickshawException(
                    "The end time of an event cannot be empty. "
                    + "Usage: event <description> /from <start> /to <end>");
        }
        validateDescription(description);

        // Validate dates and chronological order
        LocalDateTime fromDate = parseDateTime(from, "start date/time");
        LocalDateTime toDate = parseDateTime(to, "end date/time");
        if (!fromDate.isBefore(toDate)) {
            throw new RickshawException(
                    "The start time must be before the end time. "
                    + "Start: " + from + ", End: " + to);
        }

        return new EventCommand(description, from, to);
    }
}
