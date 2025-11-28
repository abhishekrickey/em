package com.controller;



import com.model.Movie;
import com.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id){
        return movieRepository.findById(id).orElse(null);
    }
}
