package com.example.websocketdemo.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode (callSuper = true)
public class RoomEvent extends BaseEvent{
    private Room room;
}
