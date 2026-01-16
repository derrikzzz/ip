package Rickshaw;

public class Parser {
    public Command parse(String input) {
        String trimmedCommand = input.trim();
        String[] parts = trimmedCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "bye" -> {
                return new Command(CommandType.BYE);
            }
            case "list" -> {
                return new Command(CommandType.LIST);
            }
            default -> {
                return new Command(CommandType.ECHO, new String[]{trimmedCommand});
            }
        }
    }
}
