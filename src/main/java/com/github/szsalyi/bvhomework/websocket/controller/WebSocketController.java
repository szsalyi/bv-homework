package com.github.szsalyi.bvhomework.greeting;

import com.github.szsalyi.bvhomework.message.Message;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class WebSocketController {

    /*@Autowired
    private SimpMessageSendingOperations sendingOperations;
    */

    @MessageMapping("/message")
    @SendToUser("/queue/reply")
    public Message processMessageFromClient(@Payload final Message message, final Principal principal) throws Exception {
        System.out.println(new Gson().toJson(message));
        return new Message(message.getSender(), message.getContent());
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(final Throwable exception) {
        return exception.getMessage();
    }
}
