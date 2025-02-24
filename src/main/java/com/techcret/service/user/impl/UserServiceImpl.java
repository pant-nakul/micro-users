package com.techcret.service.user.impl;

import com.techcret.service.user.entities.Hotel;
import com.techcret.service.user.entities.Rating;
import com.techcret.service.user.entities.User;
import com.techcret.service.user.exceptions.ResourceNotFoundException;
import com.techcret.service.user.externalServices.HotelService;
import com.techcret.service.user.externalServices.RatingService;
import com.techcret.service.user.repositories.UserRepository;
import com.techcret.service.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HotelService hotelService;
    @Autowired
    RatingService ratingService;


    /**
     * @param user
     * @return
     */
    @Override
    public User saveUSer(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map( user -> populateUserData(user)).toList();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with id :- " + userId));
        user = populateUserData(user);
        return user;
    }

    public User populateUserData(User user) {
        Rating[] ratingsOfUser = ratingService.getRatingsForUser(user.getUserId());

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        if (ratings != null) {
            ratings.stream().peek(rating -> {
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
            }).collect(Collectors.toList());
        }

        user.setRatings(ratings);
        return user;
    }
}
