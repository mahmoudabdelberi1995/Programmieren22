package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    static Stage primaryStage;
	@Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

            Scene scene = new Scene(root, 1121, 708.0);

            primaryStage.setTitle("PardiesSpiel");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
