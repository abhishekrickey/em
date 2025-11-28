package com.services;


import com.model.*;
import com.repository.*;
import com.services.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingRegisterService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public RatingRegisterService(UserRepository userRepository, 
                                 MovieRepository movieRepository, 
                                 RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public void addRating(User user, Movie movie, MovieRating rating) {
        // Save user/movie if new
        if (user.getId() == null) userRepository.save(user);
        if (movie.getId() == null) movieRepository.save(movie);

        // Check if rating exists
        Optional<Rating> existing = ratingRepository.findByUserAndMovie(user, movie);
        if (existing.isPresent()) {
            existing.get().setRating(rating);
            ratingRepository.save(existing.get());
        } else {
            Rating r = new Rating(user, movie, rating);
            ratingRepository.save(r);
        }
    }

    public double getAverageRating(Movie movie) {
        List<Rating> ratings = ratingRepository.findByMovie(movie);
        if (ratings.isEmpty()) return MovieRating.NOT_RATED.ordinal();
        int sum = ratings.stream().mapToInt(r -> r.getRating().ordinal()).sum();
        return (double) sum / ratings.size();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getUserMovies(User user) {
        return ratingRepository.findByUser(user)
                .stream()
                .map(Rating::getMovie)
                .collect(Collectors.toList());
    }

    public Map<Long, MovieRating> getMovieRatings(Movie movie) {
        Map<Long, MovieRating> map = new HashMap<>();
        ratingRepository.findByMovie(movie)
                .forEach(r -> map.put(r.getUser().getId(), r.getRating()));
        return map;
    }
    @GetMapping("/movie/{movieId}")
    public List<Rating> getMovieRatings(@PathVariable Long movieId){
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) return List.of();

        return ratingService.getMovieRatings(movie);
    }

}

