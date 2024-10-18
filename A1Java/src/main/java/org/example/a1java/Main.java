package org.example.a1java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file that defines the UI
            Parent root = FXMLLoader.load(getClass().getResource("Movie.fxml"));

            // Create a scene using the loaded layout, set the size to 600x500
            Scene scene = new Scene(root, 600, 500);

            // Set the window title to "Movie Listing Application"
            primaryStage.setTitle("Movie Listing Application");

            // Attach the scene to the stage (window)
            primaryStage.setScene(scene);

            // Display the stage with all the content
            primaryStage.show();
        } catch (Exception e) {
            // Print the error stack trace if something goes wrong
            e.printStackTrace();
        }
    }

    // The main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
