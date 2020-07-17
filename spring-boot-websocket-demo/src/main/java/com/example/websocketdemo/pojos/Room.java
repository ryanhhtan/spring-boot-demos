package com.example.websocketdemo.pojos;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Room {
    @NonNull
    private String id;
    @NonNull
    private String name;
    private Set<ChatUser> users = new HashSet<>();

    public void addUser(ChatUser user) {
        users.add(user);
    }

    public boolean deleteUser(ChatUser user) {
        return users.remove(user);
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}
