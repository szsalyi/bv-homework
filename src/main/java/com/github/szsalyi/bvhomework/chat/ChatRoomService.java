package com.github.szsalyi.bvhomework.chat;

import com.github.szsalyi.bvhomework.message.InstantMessage;

public interface ChatRoomService {
    void sendPrivateMessage(InstantMessage instantMessage);
    void sendPublicMessage(InstantMessage instantMessage);
}
