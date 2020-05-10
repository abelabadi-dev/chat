package com.ua.chat.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDetailsResponse {
    private String userName;
    private String text;
    private Date expiratationDate;
}
