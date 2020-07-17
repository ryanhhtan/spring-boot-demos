package com.example.websocketdemo.service;

import java.security.Principal;
import java.util.UUID;

import com.example.websocketdemo.pojos.AllRoomsEvent;
import com.example.websocketdemo.pojos.ChatUser;
import com.example.websocketdemo.pojos.CreateRoomRequest;
import com.example.websocketdemo.pojos.EventType;
import com.example.websocketdemo.pojos.Room;
import com.example.websocketdemo.pojos.RoomEvent;
import com.example.websocketdemo.repository.ChatUserRepository;
import com.example.websocketdemo.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatUserRepository userRepository;

    @Autowired private RoomRepository roomRepository;

	public AllRoomsEvent findAllRooms() {
        final AllRoomsEvent event = new AllRoomsEvent();
        event.setType(EventType.GET_ALL_ROOMS);
		event.setRooms(roomRepository.findAll());
        return event;
	}

    public ChatUser findUserByPrincipal(Principal principal) throws Exception{
        return userRepository
            .findById(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    public RoomEvent createRoom(Principal principal, CreateRoomRequest request) throws Exception{
        findUserByPrincipal(principal);
        Room room = roomRepository
            .findByName(request.getName())
            .orElse(null);
        if (room != null) throw new Exception("Name already taken.");
        
        room = new Room(generateId(), request.getName());
        roomRepository.add(room);

        final RoomEvent event = new RoomEvent();
        event.setType(EventType.ROOM_CREATED);
        event.setRoom(room);
        return event;
    }
    
    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
