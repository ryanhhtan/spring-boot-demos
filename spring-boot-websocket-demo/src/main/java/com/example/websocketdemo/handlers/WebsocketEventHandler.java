package com.example.websocketdemo.handlers;

import com.example.websocketdemo.pojos.ChatUser;
import com.example.websocketdemo.repository.ChatUserRepository;
import com.example.websocketdemo.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebsocketEventHandler {
    @Autowired
    ChatUserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @EventListener
    public void handleWebSocketConnectedListener(SessionConnectedEvent event) {
        log.info("### connected: {}", event);
    }

    @EventListener
    public void handleWebSocketDisconnectedListener(SessionDisconnectEvent event) {
        final ChatUser user = userRepository
            .findById(event.getUser().getName())
            .orElse(null);
        log.info("### disconnected user: {}", user);
        if (user != null) {
            user.getRoom().deleteUser(user);
            userRepository.delete(user);
        }
    }
}
