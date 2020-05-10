package com.ua.chat.controller;

import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.mapping.ChatCreateResponse;
import com.ua.chat.mapping.ChatDetailsResponse;
import com.ua.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<ChatDetailsResponse> getChat(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(chatService.getChat(id), HttpStatus.OK);
    }

    @PostMapping("/chats")
    ResponseEntity<ChatCreateResponse> createChat(@RequestBody ChatCreateRequest request){
        return new ResponseEntity<>(new ChatCreateResponse(chatService.createChat(request)), HttpStatus.OK);
    }
}
