package com.example.websocketdemo.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.example.websocketdemo.pojos.ChatUser;

import org.springframework.stereotype.Component;

@Component
public class ChatUserRepository {
    private static Set<ChatUser> users = new HashSet<>();

    public synchronized ChatUser add(ChatUser user) {
        users.add(user);
        return user;
    }

    public Optional<ChatUser> findById(final String id) {
        return users
            .stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
    }

    public synchronized boolean deleteById(final String id) {
        final ChatUser user = findById(id).orElse(null);
        return user != null ? users.remove(user) : false;
    }

	public synchronized boolean delete(ChatUser user) {
        return users.remove(user);
	}
}
