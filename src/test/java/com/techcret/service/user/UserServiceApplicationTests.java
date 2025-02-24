package com.techcret.service.user;

import com.techcret.service.user.entities.Rating;
import com.techcret.service.user.externalServices.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder()
				.rating(10)
				.userId("8fd131b3-4b42-4f69-a601-48b28fa73a2e")
				.hotelId("71e51410-cd7a-485f-a8ea-c859e3548e82")
				.feedback("This was created by Test")
				.build();
		ratingService.createRating(rating);
		System.out.println("Rating created from Test!!!!!");
	}

}
