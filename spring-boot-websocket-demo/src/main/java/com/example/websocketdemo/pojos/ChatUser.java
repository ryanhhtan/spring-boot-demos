package com.example.websocketdemo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ChatUser {
    private String id;
    private String displayName;
    @JsonIgnore
    private Room room;
}
