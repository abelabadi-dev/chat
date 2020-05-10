package com.ua.chat.controller;

import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.mapping.ChatResponse;
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
    public ResponseEntity<ChatResponse> getChat(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(chatService.getChat(id), HttpStatus.OK);
    }

    @PostMapping("/chats")
    ResponseEntity<ChatResponse> createChat(@RequestBody ChatCreateRequest request){
        return new ResponseEntity<>(new ChatResponse(chatService.createChat(request)), HttpStatus.OK);
    }
}
