package com.TrungTinhFullStack.websocket_study.Service.ChatMessage;

import com.TrungTinhFullStack.websocket_study.Entity.ChatMessage;
import com.TrungTinhFullStack.websocket_study.Repository.ChatMessageRepository;
import com.TrungTinhFullStack.websocket_study.Service.ChatRoom.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        // Retrieve the chatId based on sender and recipient
        Optional<Long> chatIdOptional = chatRoomService.getChatRoomId(chatMessage.getSenderId(),
                chatMessage.getRecipientId(), true);

        // Kiểm tra nếu chatId có giá trị thì mới set cho chatMessage
        chatIdOptional.ifPresent(chatMessage::setChatId);

        // Lưu chatMessage vào cơ sở dữ liệu
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(
            Long senderId,
            Long recipientId
    ) {
        var chatId = chatRoomService.getChatRoomId(senderId,
                recipientId,false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
