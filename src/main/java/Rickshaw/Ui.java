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

    public String showWelcomeMessage() {
        String welcomeMessage;
        System.out.println(HORIZONTAL_LINE);
        welcomeMessage = String.format(
                "Hello! I'm %s%n What can i do for you?%n",
                chatbotName
        );
        return welcomeMessage;
    }
}