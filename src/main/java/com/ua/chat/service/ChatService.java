package com.ua.chat.service;

import com.ua.chat.mapping.ChatResponse;
import com.ua.chat.model.Chat;
import com.ua.chat.mapping.ChatCreateRequest;
import com.ua.chat.model.User;
import com.ua.chat.repository.ChatRepository;
import com.ua.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

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

    public ChatResponse getChat(Long id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat record not found"));
        return new ChatResponse(chat.getId(),chat.getUser().getUserName(),chat.getText(),chat.getExpirationDate());
    }

    public List<ChatResponse> getChats(String userName) {
        List<Chat> chat = chatRepository.findAllByUser_userName(userName);
        List<ChatResponse> responses = new ArrayList<>();
        responses = chat.stream()
                .filter(c->{
                    return now().isBefore(c.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
                })
                .map(c -> {
            return new ChatResponse(c.getId(),c.getUser().getUserName(),c.getText(),c.getExpirationDate()); }
        ).collect(Collectors.toList());
        return responses;
    }
}
