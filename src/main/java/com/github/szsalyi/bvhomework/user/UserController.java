package com.github.szsalyi.bvhomework.user;

import com.github.szsalyi.bvhomework.message.InstantMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @MessageMapping("/register")
    @SendTo("/topic/public")
    private InstantMessage register(@Payload InstantMessage instantMessage, Principal principal) {
        instantMessage.setContent("Welcome " + instantMessage.getFromUser());
        userService.saveIfNotRegistered(new User(instantMessage.getFromUser()));
        logger.debug("Welcome user " + instantMessage.getFromUser());
        return instantMessage;
    }

    @MessageMapping("/unregister")
    @SendTo("/topic/public")
    private InstantMessage unregister(@Payload InstantMessage instantMessage, Principal principal) {
        instantMessage.setContent("Goodbye " + instantMessage.getFromUser());
        userService.deleteUser(new User(instantMessage.getFromUser()));
        return instantMessage;
    }

}
