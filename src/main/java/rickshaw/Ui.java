package rickshaw;

import java.util.ArrayList;
import java.util.Scanner;

import rickshaw.task.Task;

/**
 * Handles user interface interactions.
 * Displays messages and reads user input.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = "     ";
    private static final String TASK_INDENT = "       ";
    protected String chatbotName;
    protected Scanner scanner;

    /**
     * Constructs a Ui object with the specified chatbot name.
     *
     * @param chatbotName The name of the chatbot.
     */
    public Ui(String chatbotName) {
        this.scanner = new Scanner(System.in);
        this.chatbotName = chatbotName;
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "bye";
    }

    /**
     * Formats the message to be displayed.
     *
     * @param message The message to format.
     */
    private void format(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + message.replace("\n", "\n" + INDENT));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        String welcomeMessage;
        welcomeMessage = String.format(
                "Hello! I'm %s%nWhat can i do for you?",
                chatbotName
        );
        format(welcomeMessage);
    }

    /**
     * Shows exit message.
     */
    public void showExitMessage() {
        String exitMessage;
        exitMessage = "Bye. Hope to see you again soon!";
        format(exitMessage);
    }

    /**
     * Shows error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showErrorMessage(String errorMessage) {
        format(errorMessage);
    }

    /**
     * Shows task added message.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        format("Got it. I've added this task:\n"
                + TASK_INDENT + task + "\n"
                + INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Shows task deleted message.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        format("Noted. I've removed this task:\n"
                + TASK_INDENT + task + "\n"
                + INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Shows a numbered list of tasks with a header message.
     *
     * @param header The header message to display above the list.
     * @param tasks The list of tasks to display.
     */
    private void showNumberedTaskList(String header, ArrayList<Task> tasks) {
        System.out.println(header);
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            format("Your list is currently empty.");
            return;
        }
        showNumberedTaskList("Here are the tasks in your list:", tasks);
    }

    /**
     * Shows list of matching tasks.
     *
     * @param tasks The list of matching tasks to display.
     */
    public void showFindResults(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            format("No tasks found.");
            return;
        }
        showNumberedTaskList("Here are the matching tasks in your list:", tasks);
    }

    /**
     * Shows echo message.
     *
     * @param input The input to echo.
     */
    public void showEchoMessage(String input) {
        format(input);
    }

    /**
     * Shows marked task message.
     *
     * @param task The task that was marked.
     */
    public void showMarkedTask(Task task) {
        format("Nice! I've marked this task as done:\n" + TASK_INDENT + task);
    }

    /**
     * Shows unmarked task message.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkedTask(Task task) {
        format("OK, I've marked this task as not done yet:\n" + TASK_INDENT + task);
    }

    public void showTaggedTask(Task task) {
        format("Nice! I've tagged this task:\n" + TASK_INDENT + task);
    }

    public void showUntaggedTask(Task task) {
        format("OK, I've untagged this task:\n" + TASK_INDENT + task);
    }
}
