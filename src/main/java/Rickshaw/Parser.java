package Rickshaw;

public class Parser {
    public Command parse(String input) throws RickshawException{
        String trimmedCommand = input.trim();
        String[] parts = trimmedCommand.split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        switch (commandWord) {
            case "BYE" -> {
                return new Command(CommandType.BYE);
            }
            case "LIST" -> {
                return new Command(CommandType.LIST);
            }
            default -> {
                return new Command(CommandType.ECHO, new String[]{trimmedCommand});
            }
        }
    }
}
