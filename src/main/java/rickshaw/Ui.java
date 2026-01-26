package rickshaw;

import rickshaw.task.Task;

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
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "bye";
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

    public void showTaskAdded(Task task, int totalTasks) {
        format("Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        format("Noted. I've removed this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + totalTasks + " tasks in the list.");
    }

    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            format("Your list is currently empty.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showFind(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            format("No tasks found.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showEchoMessage(String input) {
        format(input);
    }

    public void showMarkedTask(Task task) {
        format("Nice! I've marked this task as done:\n       " + task);
    }

    public void showUnmarkedTask(Task task) {
        format("OK, I've marked this task as not done yet:\n       " + task);
    }
}
