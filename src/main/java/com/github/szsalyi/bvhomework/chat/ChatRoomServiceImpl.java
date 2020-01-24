package com.github.szsalyi.bvhomework.chat;

import com.github.szsalyi.bvhomework.message.InstantMessage;
import com.github.szsalyi.bvhomework.message.InstantMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private InstantMessageService instantMessageService;

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void sendPrivateMessage(InstantMessage instantMessage) {
/*
        jmsTemplate.convertAndSend(queue, instantMessage);
*/
        messagingTemplate.convertAndSendToUser(
                instantMessage.getToUser().getName(), instantMessage.getFromUser().getName(), instantMessage);
        instantMessageService.appendInstantMessageToConversations(instantMessage);
    }

    @Override
    public void sendPublicMessage(InstantMessage instantMessage) {
            messagingTemplate.convertAndSend
                    ("/topic/public.messages", instantMessage);
    }
}
