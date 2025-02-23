package com.techcret.service.user.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private String userIdId;
    private String hotelId;
    private int rating;
    private String feedback;


}
