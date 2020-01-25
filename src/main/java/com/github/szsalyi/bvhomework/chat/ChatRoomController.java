package com.github.szsalyi.bvhomework.chat;

import com.github.szsalyi.bvhomework.message.InstantMessage;
import com.github.szsalyi.bvhomework.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/send")
    public void sendMessage(@Payload InstantMessage instantMessage, Principal principal,
                            SimpMessageHeaderAccessor headerAccessor) {
        logger.debug("principal " + principal.toString());
        instantMessage.setFromUser(principal.getName());

        logger.debug(instantMessage.getFromUser() + " from - to " + instantMessage.getToUser() + " principal" + principal.getName());

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
