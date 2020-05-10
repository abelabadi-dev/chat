package com.ua.chat.controller;

import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.mapping.ChatCreateResponse;
import com.ua.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chats")
    ResponseEntity<ChatCreateResponse> createChat(@RequestBody ChatCreateRequest request){
        return new ResponseEntity<>(new ChatCreateResponse(chatService.createChat(request)), HttpStatus.OK);
    }
}
