package com.ua.chat;

import com.ua.chat.mapping.ChatResponse;
import com.ua.chat.model.Chat;
import com.ua.chat.model.User;
import com.ua.chat.repository.ChatRepository;
import com.ua.chat.repository.UserRepository;
import com.ua.chat.service.ChatService;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ChatApplication.class})
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private ChatRepository chatRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void getChatUsingId(){
        User user = new User("Abel","Abadi","aabadi");
        Chat message = new Chat("hello",new Date(),user);
        message.setId(1l);
        Mockito.when(chatRepository.findById(message.getId())).thenReturn(java.util.Optional.of(message));
        ChatResponse response = chatService.getChat(1l);
        assertEquals(response.getId(),message.getId());
        assertEquals(response.getText(),message.getText());
    }

    @Test
    public void getChatsForAUserShouldReturnUnreadAndUnexpriedMessages(){
        User user = new User("Abel","Abadi","aabadi");
        List<Chat> messages = new ArrayList<>();
        Date futureDate = Date.from((new Date()).toInstant().plusSeconds( 10000 ));

        Chat msg1 = new Chat("hello", futureDate ,user);
        msg1.setRead(false);

        Chat msg2 = new Chat("hi",new Date(),user);
        msg2.setRead(true);

        Chat msg3 = new Chat("okay",new Date(),user);
        msg3.setRead(false);

        Chat msg4 = new Chat("cool.", futureDate,user);
        msg4.setRead(false);

        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);
        messages.add(msg4);


        Mockito.when(chatRepository.findAllByUser_userName(user.getUserName())).thenReturn(messages);

        List<ChatResponse> responses = chatService.getChats(user.getUserName());
        assertEquals(responses.size(),2);
    }
}
