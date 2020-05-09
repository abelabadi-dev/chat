package com.ua.chat.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @Column(name="expiration_date")
    private Date expirationDate;

    @ManyToOne
    private User user;

}
