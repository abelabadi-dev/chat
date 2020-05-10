package com.ua.chat.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String text;

    @Column(name="expiration_date")
    @NonNull
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_name")
    @NonNull
    private User user;

}
