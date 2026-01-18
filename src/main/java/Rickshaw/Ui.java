package Rickshaw;

import Rickshaw.task.Task;

import java.util.ArrayList;
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

    public void showTaskAdded(String task) {
        format("added: " + task);
    }

    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            format("Your list is currently empty.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        format(sb.toString());
    }

    public void showEchoMessage(String input) {
        format(input);
    }

}