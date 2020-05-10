package com.ua.chat.service;

import com.ua.chat.mapping.ChatDetailsResponse;
import com.ua.chat.model.Chat;
import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.model.User;
import com.ua.chat.repository.ChatRepository;
import com.ua.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class ChatService {

    @Value("${chat.timeout.default:60}")
    private int timeout;

    private ChatRepository chatRepository;
    private UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Long createChat(ChatCreateRequest request) {
        User user = userRepository.findByUserName(request.getUserName());
        int seconds =  request.getTimeout() == 0 ? timeout : request.getTimeout();
        Date expirationDate = Date.from((new Date()).toInstant().plusSeconds( seconds ));
        Chat chat = new Chat(request.getText(),expirationDate,user);
        return (chatRepository.save(chat)).getId();
    }

    public ChatDetailsResponse getChat(Long id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat record not found"));
        return new ChatDetailsResponse(chat.getUser().getUserName(),chat.getText(),chat.getExpirationDate());
    }
}
