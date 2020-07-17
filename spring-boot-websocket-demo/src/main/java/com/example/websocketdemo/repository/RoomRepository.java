package com.example.websocketdemo.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.websocketdemo.pojos.Room;

import org.springframework.stereotype.Component;

@Component
public class RoomRepository {
    private static Set<Room> rooms = new HashSet<>();

    public synchronized Room add(final Room room) {
        rooms.add(room);
        return room;
    }

    public Collection<Room> findAll(){
        return rooms;
    } 

    public Optional<Room> findById(final String id) {
        return rooms.stream()
            .filter(room -> room.getId().equals(id))
            .findFirst();
    }

    public Optional<Room> findByName(final String name) {
        return rooms.stream()
            .filter(room -> room.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    public synchronized boolean delete(final String id) {
        final Room room = findById(id).orElse(null); 
        return room == null ? false : rooms.remove(room); 
    }
}
