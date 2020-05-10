package com.ua.chat.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.mapping.ChatResponse;
import com.ua.chat.mapping.ResponseView;
import com.ua.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chats/{id}")
    @JsonView(ResponseView.ChatDetailsResponse.class)
    public ResponseEntity<ChatResponse> getChat(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(chatService.getChat(id), HttpStatus.OK);
    }

    @GetMapping("/chats")
    @JsonView(ResponseView.ChatCollectionResponse.class)
    public ResponseEntity<List<ChatResponse>> getChats(@RequestParam(name = "username") String userName){
        return new ResponseEntity<>(chatService.getChats(userName), HttpStatus.OK);
    }

    @PostMapping("/chats")
    @JsonView(ResponseView.ChatCreatedResponse.class)
    ResponseEntity<ChatResponse> createChat(@RequestBody ChatCreateRequest request){
        return new ResponseEntity<>(new ChatResponse(chatService.createChat(request)), HttpStatus.OK);
    }
}
