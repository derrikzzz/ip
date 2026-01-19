package Rickshaw;

public class Parser {
    public Command parse(String input) throws RickshawException{
        String trimmedCommand = input.trim();
        String[] parts = trimmedCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "MARK" -> {
                return new Command(CommandType.MARK, new String[]{parts[1]});
            }
            case "UNMARK" -> {
                return new Command(CommandType.UNMARK, new String[]{parts[1]});
            }
            case "DELETE" -> {
                return new Command(CommandType.DELETE, new String[]{parts[1]});
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
                return new Command(CommandType.BYE);
            }
            case "LIST" -> {
                return new Command(CommandType.LIST);
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
        return new Command(CommandType.TODO, new String[]{description});
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
        return new Command(CommandType.DEADLINE, new String[]{description, doneBy});
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

        return new Command(CommandType.EVENT, new String[]{description, from, to});
    }


}
