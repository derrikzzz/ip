package rickshaw;

import java.io.IOException;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.geometry.Pos;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView picture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        picture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the right and text on the left.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getUserDialog(String input, Image userImage) {
        var db = new DialogBox(input, userImage);
        db.setAlignment(Pos.TOP_LEFT);
        db.dialog.setStyle("-fx-background-color: #d1f2eb; -fx-background-radius: 10; -fx-padding: 10; -fx-font-size: 13px;");
        return db;
    }

    public static DialogBox getRickshawDialog(String response, Image rickshawImage) {
        var db = new DialogBox(response, rickshawImage);
        db.flip();
        db.dialog.setStyle("-fx-background-color: #e1f5fe; -fx-background-radius: 10; -fx-padding: 10; -fx-font-size: 13px;");
        return db;
    }
}
