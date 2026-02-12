package rickshaw;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Rickshaw application.
 */
public class Main extends Application {
    private Rickshaw rickshaw = new Rickshaw("Rickshaw");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rickshaw");
            stage.setMinWidth(400);
            stage.setMinHeight(600);
            fxmlLoader.<MainWindow>getController().setRickshaw(rickshaw);
            scene.getStylesheets().add(Main.class.getResource("/styles.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
