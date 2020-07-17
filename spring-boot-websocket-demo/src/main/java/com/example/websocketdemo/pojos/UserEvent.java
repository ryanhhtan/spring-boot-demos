package com.example.websocketdemo.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserEvent extends BaseEvent{
    private ChatUser user;
}
