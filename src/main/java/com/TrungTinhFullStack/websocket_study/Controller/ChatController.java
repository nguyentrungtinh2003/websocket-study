package com.TrungTinhFullStack.websocket_study.Controller;

import com.TrungTinhFullStack.websocket_study.Entity.ChatMessage;
import com.TrungTinhFullStack.websocket_study.Entity.ChatNotification;
import com.TrungTinhFullStack.websocket_study.Service.ChatMessage.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ) {
        ChatMessage savedMsg = chatMessageService.saveChatMessage(chatMessage);

        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()),"/queue/messages", ChatNotification.builder()
                                .id(savedMsg.getId())
                                .senderId(savedMsg.getSenderId())
                                .recipientId(savedMsg.getRecipientId())
                                .content(savedMsg.getContent())
                        .build()
        );

    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
