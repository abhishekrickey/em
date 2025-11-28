package com.model;


import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @Enumerated(EnumType.STRING)
    private MovieRating rating;

    public Rating() {}

    public Rating(User user, Movie movie, MovieRating rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Movie getMovie() { return movie; }
    public MovieRating getRating() { return rating; }

    public void setUser(User user) { this.user = user; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public void setRating(MovieRating rating) { this.rating = rating; }
}

