package com.example.websocketdemo.pojos;

import lombok.Data;

@Data
public abstract class BaseEvent {
    private EventType type;
}
