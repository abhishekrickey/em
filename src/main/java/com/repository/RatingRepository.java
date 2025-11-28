package com.repository;



import com.model.Rating;
import com.model.Movie;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUser(User user);
    List<Rating> findByMovie(Movie movie);
    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}

