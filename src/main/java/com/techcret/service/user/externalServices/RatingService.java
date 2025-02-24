package com.techcret.service.user.externalServices;

import com.techcret.service.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    Rating[] getRatingsForUser(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating createRating(Rating rating);

}
