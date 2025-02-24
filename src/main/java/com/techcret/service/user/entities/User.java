package com.techcret.service.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="micro_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name="id")
    private String userId;
    @Column(name="name", length = 128)
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="about")
    private String about;

    @Transient
    @Builder.Default
    private List<Rating> ratings = new ArrayList<>();
}
