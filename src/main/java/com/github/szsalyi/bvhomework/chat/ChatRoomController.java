package com.github.szsalyi.bvhomework.chat;

import com.github.szsalyi.bvhomework.message.InstantMessage;
import com.github.szsalyi.bvhomework.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/send.message")
    public void sendMessage(@Payload InstantMessage instantMessage, Principal principal,
                            SimpMessageHeaderAccessor headerAccessor) {
       /* String chatRoomId = headerAccessor.getSessionAttributes().get("chatRoomId").toString();*/
        instantMessage.setFromUser(new User(principal.getName()));
       /* instantMessage.setChatRoomId(chatRoomId);*/
        if (instantMessage.isPublic()) {
            chatRoomService.sendPublicMessage(instantMessage);
        } else {
            chatRoomService.sendPrivateMessage(instantMessage);
        }
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(final Throwable exception) {
        return exception.getMessage();
    }
}
