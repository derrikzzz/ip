package Rickshaw;

public class Rickshaw {
    public static void main(String[] args) {
        Ui ui = new Ui("Rickshaw");
        System.out.println(ui.showWelcomeMessage());
        System.out.println(ui.showExitMessage());
    }
}