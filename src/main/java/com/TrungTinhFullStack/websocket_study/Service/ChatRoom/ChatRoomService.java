package com.TrungTinhFullStack.websocket_study.Service.ChatRoom;

import com.TrungTinhFullStack.websocket_study.Entity.ChatRoom;
import com.TrungTinhFullStack.websocket_study.Repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Optional<Long> getChatRoomId(Long senderId,
                                        Long recipientId,
                                        boolean createNewRoomIfNotExists) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId,recipientId);
                        return Optional.of(chatId);
                    }
                    return null;
                });
    }

    private Long createChatId(Long senderId, Long recipientId) {
        Long chatId = senderId * 100 + recipientId;
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
