package com.techcret.service.user.controller;

import com.techcret.service.user.entities.User;
import com.techcret.service.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //create

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUSer(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get single user
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackSingleUser")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallbackSingleUser")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        log.info("Retry Count : {}", retryCount);
        retryCount ++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //all user get
    @GetMapping
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackAllUsers")
    public ResponseEntity<List<User>> getSingleUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    int retryCount = 1;
    public ResponseEntity<User> ratingHotelFallbackSingleUser(String userId, Exception e){

        log.info("Circuit breaker executing from ratingHotelFallbackSingleUser, Exception  : {}", e.getMessage());
        User user = User.builder()
                .userId(userId)
                .name("CB Name")
                .email("CB Email")
                .about("CB ABout")
                .build();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<List<User>> ratingHotelFallbackAllUsers(Exception e){
        log.info("Circuit breaker executing from ratingHotelFallbackAllUsers, Exception  : {}", e.getMessage());
        return ResponseEntity.ok(new ArrayList<>());
    }
}
