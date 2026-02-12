package rickshaw;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Rickshaw rickshaw;

    private Image userImage;
    private Image rickshawImage;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
            rickshawImage = new Image(this.getClass().getResourceAsStream("/images/rickshaw.png"));
        } catch (Exception e) {
            System.err.println("Warning: Could not load images from /images/ directory");
        }
    }

    public void setRickshaw(Rickshaw r) {
        rickshaw = r;
    }

    /**
     * Handles user input by creating dialog boxes for user and bot responses.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = rickshaw.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRickshawDialog(response, rickshawImage)
            );
        } catch (RickshawException e) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getErrorDialog(e.getMessage(), rickshawImage)
            );
        }
        userInput.clear();
    }
}
