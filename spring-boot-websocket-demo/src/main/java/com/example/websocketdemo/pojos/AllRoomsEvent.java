package com.example.websocketdemo.pojos;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AllRoomsEvent extends BaseEvent {
    private Collection<Room> rooms;
}
