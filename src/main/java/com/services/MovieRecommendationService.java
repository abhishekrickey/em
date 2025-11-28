package com.services;



import com.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieRecommendationService {

    private final RatingRegisterService ratingRegister;

    public MovieRecommendationService(RatingRegisterService ratingRegister) {
        this.ratingRegister = ratingRegister;
    }

    public String recommendMovie(User user) {
        List<Movie> watched = ratingRegister.getUserMovies(user);
        if (watched.isEmpty()) return recommendMovieNewUser();
        return recommendMovieExistingUser(user);
    }

    private String recommendMovieNewUser() {
        Movie bestMovie = null;
        double bestRating = 0;
        for (Movie movie : ratingRegister.getMovies()) {
            double avg = ratingRegister.getAverageRating(movie);
            if (avg > bestRating) {
                bestMovie = movie;
                bestRating = avg;
            }
        }
        return bestMovie != null ? bestMovie.getTitle() : null;
    }

    private String recommendMovieExistingUser(User user) {
        Movie bestMovie = null;
        int similarityScore = Integer.MAX_VALUE;

        for (User reviewer : ratingRegister.getUsers()) {
            if (reviewer.getId().equals(user.getId())) continue;

            int score = getSimilarityScore(user, reviewer);
            if (score < similarityScore) {
                similarityScore = score;
                Movie recommendedMovie = recommendUnwatchedMovie(user, reviewer);
                if (recommendedMovie != null) bestMovie = recommendedMovie;
            }
        }
        return bestMovie != null ? bestMovie.getTitle() : null;
    }

    private int getSimilarityScore(User u1, User u2) {
        int score = Integer.MAX_VALUE;

        for (Movie movie : ratingRegister.getUserMovies(u2)) {
            Map<Long, MovieRating> ratings = ratingRegister.getMovieRatings(movie);
            if (ratings.containsKey(u1.getId())) {
                score = (score == Integer.MAX_VALUE) ? 0 : score;
                score += Math.abs(ratings.get(u1.getId()).ordinal() - ratings.get(u2.getId()).ordinal());
            }
        }
        return score;
    }

    private Movie recommendUnwatchedMovie(User user, User reviewer) {
        Movie bestMovie = null;
        int bestRating = 0;

        for (Movie movie : ratingRegister.getUserMovies(reviewer)) {
            Map<Long, MovieRating> ratings = ratingRegister.getMovieRatings(movie);
            if (!ratings.containsKey(user.getId()) && ratings.get(reviewer.getId()).ordinal() > bestRating) {
                bestMovie = movie;
                bestRating = ratings.get(reviewer.getId()).ordinal();
            }
        }
        return bestMovie;
    }
}

