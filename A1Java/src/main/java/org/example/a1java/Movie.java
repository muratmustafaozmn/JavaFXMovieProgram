package org.example.a1java;

// setters getters constructors only
public class Movie {
    private int id;
    private String title;
    private float imdbRating;
    private String posterUrl;
    private String description;

    public Movie(int id, String title, float imdbRating, String posterUrl, String description) {
        this.id = id;
        this.title = title;
        this.imdbRating = imdbRating;
        this.posterUrl = posterUrl;
        this.description = description;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}