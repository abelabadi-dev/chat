package com.ua.chat;

import com.ua.chat.controller.ChatController;
import com.ua.chat.mapping.ChatResponse;
import com.ua.chat.service.ChatService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ChatService service;

    @Test
    public void getChatsForAGivenUser() throws Exception{

        List<ChatResponse> responses = new ArrayList<>();
        responses.add(new ChatResponse("aabadi","hello",new Date()));
        responses.add(new ChatResponse("aabadi","hi",new Date()));
        responses.add(new ChatResponse("aabadi","okay",new Date()));

        given(service.getChats("aabadi")).willReturn(responses);

        mvc.perform(get("/api/chats?username=aabadi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].text", Matchers.is(responses.get(0).getText())));
    }
}
