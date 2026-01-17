package Rickshaw;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected String chatbotName;
    protected Scanner scanner;

    public Ui(String chatbotName) {
        this.scanner = new Scanner(System.in);
        this.chatbotName = chatbotName;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    private void format(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     " + message.replace("\n", "\n     "));
        System.out.println(HORIZONTAL_LINE);
    }

    public void showWelcomeMessage() {
        String welcomeMessage;
        welcomeMessage = String.format(
                "Hello! I'm %s%nWhat can i do for you?",
                chatbotName
        );
        format(welcomeMessage);
    }

    public void showExitMessage() {
        String exitMessage;
        exitMessage = "Bye. Hope to see you again soon!";
        format(exitMessage);
    }

    public void showErrorMessage(String errorMessage) {
        format(errorMessage);
    }

    public void showList() {
        format("list");
    }

    public void showEchoMessage(String input) {
        format(input);
    }
}