package com.ua.chat.mapping;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatCreateRequest {
    private Long id;
    private String text;
    private int timeout;
    private String userName;
}
