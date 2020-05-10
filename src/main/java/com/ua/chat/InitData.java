package com.ua.chat;

import com.ua.chat.model.Chat;
import com.ua.chat.model.User;
import com.ua.chat.repository.ChatRepository;
import com.ua.chat.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class InitData implements CommandLineRunner {
    private final UserRepository userRepository;

    public InitData(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {
        Stream.of(
                (new User("abel","abadi","aabadi")),
                (new User("john","doe","jdoe")),
                (new User("jane","doe","jadoe"))
                ).forEach(user -> userRepository.save(user));
    }
}
