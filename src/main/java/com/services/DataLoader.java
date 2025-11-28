package com.services;



import com.model.*;
import com.repository.MovieRepository;
import com.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingRegisterService ratingService;

    public DataLoader(UserRepository userRepository, MovieRepository movieRepository,
                      RatingRegisterService ratingService) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ratingService = ratingService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Users
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        User user3 = new User("User 3");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // Create Movies
        Movie movie1 = new Movie("Batman Begins");
        Movie movie2 = new Movie("Liar Liar");
        Movie movie3 = new Movie("The Godfather");
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        // Add Ratings
        ratingService.addRating(user1, movie1, MovieRating.FIVE);
        ratingService.addRating(user1, movie2, MovieRating.TWO);
        ratingService.addRating(user2, movie2, MovieRating.TWO);
        ratingService.addRating(user2, movie3, MovieRating.FOUR);

        System.out.println("Sample data loaded successfully!");
    }
}
