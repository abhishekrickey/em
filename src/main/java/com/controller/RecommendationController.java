package com.controller;



import com.model.User;
import com.repository.UserRepository;
import com.services.MovieRecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final MovieRecommendationService recommendationService;
    private final UserRepository userRepository;

    public RecommendationController(MovieRecommendationService recommendationService, UserRepository userRepository){
        this.recommendationService = recommendationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public String recommendMovie(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return "User not found";
        return recommendationService.recommendMovie(user);
    }
}

