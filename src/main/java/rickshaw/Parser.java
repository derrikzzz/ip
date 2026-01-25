package rickshaw;

import rickshaw.commands.Command;
import rickshaw.commands.MarkCommand;
import rickshaw.commands.UnmarkCommand;
import rickshaw.commands.DeleteCommand;
import rickshaw.commands.TodoCommand;
import rickshaw.commands.DeadlineCommand;
import rickshaw.commands.EventCommand;
import rickshaw.commands.ByeCommand;
import rickshaw.commands.ListCommand;

public class Parser {
    public Command parse(String input) throws RickshawException{
        String trimmedCommand = input.trim();
        String[] parts = trimmedCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "MARK" -> {
                return new MarkCommand(Integer.parseInt(parts[1]));
            }
            case "UNMARK" -> {
                return new UnmarkCommand(Integer.parseInt(parts[1]));
            }
            case "DELETE" -> {
                return new DeleteCommand(Integer.parseInt(parts[1]));
            }
            case "TODO" -> {
                return parseTodo(trimmedCommand);
            }
            case "DEADLINE" -> {
                return parseDeadline(trimmedCommand);
            }
            case "EVENT" -> {
                return parseEvent(trimmedCommand);
            }
            case "BYE" -> {
                return new ByeCommand();
            }
            case "LIST" -> {
                return new ListCommand();
            }
            default -> {
                throw new RickshawException("I do not understand this command...");
            }
        }
    }

    private Command parseTodo(String trimmedCommand) throws RickshawException {
        String description = trimmedCommand.substring(4).trim();
        if (description.isEmpty()) {
            throw new RickshawException("Are you sure you want to add a todo task, the description of a todo task cannot be empty");
        }
        return new TodoCommand(description);
    }

    private Command parseDeadline(String trimmedCommand) throws RickshawException {
        // Example of input: "deadline return book /by Sunday"
        String payload = trimmedCommand.substring(8).trim();
        String[] segments = payload.split(" /by ");
        if (segments.length < 2) {
            throw new RickshawException("I recognise that you want to add a deadline task, but the format is incorrect. Usage: deadline <description> /by <time>");
        }
        String description = segments[0];
        String doneBy = segments[1];
        return new DeadlineCommand(description, doneBy);
    }

    private Command parseEvent(String trimmedCommand) throws RickshawException {
        String payload = trimmedCommand.substring(5).trim();
        String[] fromSplit = payload.split(" /from ");
        if (fromSplit.length < 2) {
            throw new RickshawException("I recognise that you want to add an event task, but the format is incorrect. Usage: event <description> /from <start]> /to <end>");
        }

        String description = fromSplit[0];
        String timeInfo = fromSplit[1];

        String[] timeSplit = timeInfo.split(" /to ");
        if (timeSplit.length < 2) {
            throw new RickshawException("I recognise that you want to add an event task, but the format is incorrect. Usage: event <description> /from <start> /to <end>");
        }
        String from = timeSplit[0];
        String to = timeSplit[1];

        return new EventCommand(description, from, to);
    }


}
