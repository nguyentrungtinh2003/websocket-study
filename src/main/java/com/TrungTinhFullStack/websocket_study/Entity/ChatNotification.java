package com.TrungTinhFullStack.websocket_study.Entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class ChatNotification {

    private Long id;

    private Long senderId;

    private Long recipientId;

    private String content;
}
