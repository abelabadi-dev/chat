package com.ua.chat.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "user_name")
    @NonNull
    private String userName;
}
