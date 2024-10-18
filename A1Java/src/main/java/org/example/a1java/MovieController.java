package org.example.a1java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListCell;

import java.sql.*;
import java.util.List;

public class MovieController {

    @FXML
    private ListView<Movie> movieListView;  // List to display movies
    @FXML
    private Label titleLabel;  // Label to show the title
    @FXML
    private Label ratingLabel;  // Label to show the IMDb rating
    @FXML
    private ImageView posterImageView;  // Image view for the movie poster
    @FXML
    private TextArea descriptionLabel;  // Text area for the movie description
    @FXML
    private TextField titleField;  // Input field for the title
    @FXML
    private TextField ratingField;  // Input field for the IMDb rating
    @FXML
    private TextField posterUrlField;  // Input field for the poster URL
    @FXML
    private TextArea descriptionField;  // Input field for the description

    private ObservableList<Movie> movieList = FXCollections.observableArrayList();  // List to store movies
    private Database database;  // Database object to handle database operations

    @FXML
    public void initialize() {
        database = new Database();  // Initialize database
        loadMovies();  // Load movies from the database

        // Set listener to update movie details when a movie is selected from the list
        movieListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayMovieDetails(newValue);  // Show details of the selected movie
            }
        });

        // Customize the movie list to display movie titles
        movieListView.setCellFactory(param -> new ListCell<Movie>() {
            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);  // Clear text if there's no item
                } else {
                    setText(item.getTitle());  // Show movie title
                }
            }
        });
    }

    // Load movies from the database and add them to the list
    private void loadMovies() {
        movieList.clear();  // Clear the current list
        List<Movie> movies = database.getMovies();  // Get movies from the database
        movieList.addAll(movies);  // Add movies to the list
        movieListView.setItems(movieList);  // Update the list view
    }

    // Show details of the selected movie
    private void displayMovieDetails(Movie movie) {
        titleLabel.setText(movie.getTitle());  // Set the title label
        ratingLabel.setText("IMDb Rating: " + movie.getImdbRating());  // Show the IMDb rating
        descriptionLabel.setText(movie.getDescription());  // Show the movie description

        Image image = new Image(movie.getPosterUrl(), true);  // Load the poster image
        posterImageView.setImage(image);  // Set the poster image in the image view

        // Fill the fields for editing
        titleField.setText(movie.getTitle());
        ratingField.setText(String.valueOf(movie.getImdbRating()));
        posterUrlField.setText(movie.getPosterUrl());
        descriptionField.setText(movie.getDescription());
    }

    // Add a new movie to the database
    @FXML
    private void addMovie() {
        String title = titleField.getText();  // Get the title input
        String rating = ratingField.getText();  // Get the rating input
        String posterUrl = posterUrlField.getText();  // Get the poster URL input
        String description = descriptionField.getText();  // Get the description input

        if (title.isEmpty() || rating.isEmpty() || posterUrl.isEmpty() || description.isEmpty()) {
            showAlert("Please fill in all fields.");  // Show an alert if any field is empty
            return;
        }

        try {
            float imdbRating = Float.parseFloat(rating);  // Convert the rating to a float
            Movie newMovie = new Movie(0, title, imdbRating, posterUrl, description);  // Create a new movie object
            database.addMovie(newMovie);  // Add the movie to the database
            loadMovies();  // Reload the movie list
            clearFields();  // Clear the input fields
        } catch (NumberFormatException e) {
            showAlert("Invalid IMDb rating. Please enter a valid number.");  // Show alert if rating is not valid
        }
    }

    // Update the selected movie
    @FXML
    private void updateMovie() {
        Movie selectedMovie = movieListView.getSelectionModel().getSelectedItem();  // Get the selected movie
        if (selectedMovie == null) {
            showAlert("Please select a movie to update.");  // Show alert if no movie is selected
            return;
        }

        String title = titleField.getText();  // Get the new title input
        String rating = ratingField.getText();  // Get the new rating input
        String posterUrl = posterUrlField.getText();  // Get the new poster URL input
        String description = descriptionField.getText();  // Get the new description input

        if (title.isEmpty() || rating.isEmpty() || posterUrl.isEmpty() || description.isEmpty()) {
            showAlert("Please fill in all fields.");  // Show alert if any field is empty
            return;
        }

        try {
            float imdbRating = Float.parseFloat(rating);  // Convert the rating to a float
            selectedMovie.setTitle(title);  // Update the title of the movie
            selectedMovie.setImdbRating(imdbRating);  // Update the IMDb rating
            selectedMovie.setPosterUrl(posterUrl);  // Update the poster URL
            selectedMovie.setDescription(description);  // Update the description
            database.updateMovie(selectedMovie);  // Update the movie in the database
            loadMovies();  // Reload the movie list
            clearFields();  // Clear the input fields
        } catch (NumberFormatException e) {
            showAlert("Invalid IMDb rating. Please enter a valid number.");  // Show alert if rating is not valid
        }
    }

    // Delete the selected movie
    @FXML
    private void deleteMovie() {
        Movie selectedMovie = movieListView.getSelectionModel().getSelectedItem();  // Get the selected movie
        if (selectedMovie == null) {
            showAlert("Please select a movie to delete.");  // Show alert if no movie is selected
            return;
        }

        database.deleteMovie(selectedMovie.getId());  // Delete the movie from the database
        loadMovies();  // Reload the movie list
        clearFields();  // Clear the input fields
    }

    // Clear the input fields after adding/updating/deleting a movie
    private void clearFields() {
        titleField.clear();
        ratingField.clear();
        posterUrlField.clear();
        descriptionField.clear();
    }

    // Show an alert message to the user
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  // Create an information alert
        alert.setTitle("Movie App");  // Set the title of the alert
        alert.setHeaderText(null);  // No header text
        alert.setContentText(message);  // Set the message content
        alert.showAndWait();  // Show the alert and wait for user action
    }
}
