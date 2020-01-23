package com.github.szsalyi.bvhomework.websocket.controller;

import com.github.szsalyi.bvhomework.message.Message;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {



    @MessageMapping("/message")
    @SendToUser("/queue/reply")
    public Message processMessageFromClient(@Payload final Message message, final Principal principal) throws Exception {
        return message;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(final Throwable exception) {
        return exception.getMessage();
    }
}
