package com.ua.chat.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {

    @JsonView(ResponseView.ChatCreatedResponse.class)
    private Long id;

    @JsonView(ResponseView.ChatDetailsResponse.class)
    private String userName;
    @JsonView(ResponseView.ChatDetailsResponse.class)
    private String text;
    @JsonView(ResponseView.ChatDetailsResponse.class)
    private Date expirationDate;


    public ChatResponse(Long id){
        this.id = id;
    }

    public ChatResponse(String userName,String text,Date expirationDate){
        this.userName = userName;
        this.text = text;
        this.expirationDate = expirationDate;
    }
}
