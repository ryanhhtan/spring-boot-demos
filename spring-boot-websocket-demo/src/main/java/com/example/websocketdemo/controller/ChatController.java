package com.example.websocketdemo.controller;

import java.security.Principal;

import com.example.websocketdemo.pojos.AllRoomsEvent;
import com.example.websocketdemo.pojos.ChatMessage;
import com.example.websocketdemo.pojos.ChatUser;
import com.example.websocketdemo.pojos.CreateRoomRequest;
import com.example.websocketdemo.pojos.InputMessage;
import com.example.websocketdemo.pojos.RoomEvent;
import com.example.websocketdemo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;


    @MessageMapping("room.showall")
    @SendToUser("/queue/events")
    public AllRoomsEvent getRooms() {
        log.info("###show all rooms");
        return chatService.findAllRooms();
    }

    @MessageMapping("room.create")
    @SendTo("/topic/events")
    public RoomEvent createRoom(final Principal principal, @Payload final CreateRoomRequest request) throws Exception{
        log.info("###create room request: {}", request);
        return chatService.createRoom(principal, request);
    }

    @MessageMapping("/message")
    @SendTo("/topic/chat")
    public ChatMessage say(final Principal principal, @Payload final InputMessage input) throws Exception {
        final ChatUser user = chatService.findUserByPrincipal(principal);
        return new ChatMessage(user, input.getMessage()); 
    }
}
