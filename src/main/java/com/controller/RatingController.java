package com.controller;

import com.model.Movie;
import com.model.MovieRating;
import com.model.Rating;
import com.model.User;
import com.repository.MovieRepository;
import com.repository.UserRepository;
import com.services.RatingRegisterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingRegisterService ratingService;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public RatingController(RatingRegisterService ratingService, UserRepository userRepository, MovieRepository movieRepository) {
        this.ratingService = ratingService;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public String addRating(@RequestParam Long userId, @RequestParam Long movieId, @RequestParam MovieRating rating){
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(user == null || movie == null) return "User or Movie not found";

        ratingService.addRating(user, movie, rating);
        return "Rating added successfully";
    }

    @GetMapping("/user/{userId}")
    public List<Movie> getUserMovies(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return List.of();
        return ratingService.getUserMovies(user);
    }

    @GetMapping("/movie/{movieId}")
    public List<Rating> getMovieRatings(@PathVariable Long movieId){
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null) return List.of();
        return movie.getRatings();
    }
}

