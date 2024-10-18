package org.example.a1java;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Movie> movies;

    public Database() {
        movies = new ArrayList<>();
        // Add 15 sample movies
        movies.add(new Movie(1, "The Shawshank Redemption", 9.3f, "https://hizliresim.com/9ve8gw7", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));
        movies.add(new Movie(2, "The Godfather", 9.2f, "https://example.com/godfather.jpg", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
        movies.add(new Movie(3, "The Dark Knight", 9.0f, "https://example.com/darkknight.jpg", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."));
        // Add more movies here...
    }

    // Get all movies
    public List<Movie> getMovies() {
        return movies;
    }

    // Add a new movie
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    // Update an existing movie
    public void updateMovie(Movie movie) {
        // Find the movie by ID and update its fields
        for (Movie m : movies) {
            if (m.getId() == movie.getId()) {
                m.setTitle(movie.getTitle());
                m.setImdbRating(movie.getImdbRating());
                m.setPosterUrl(movie.getPosterUrl());
                m.setDescription(movie.getDescription());
                break;
            }
        }
    }

    // Delete a movie by ID
    public void deleteMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }
}