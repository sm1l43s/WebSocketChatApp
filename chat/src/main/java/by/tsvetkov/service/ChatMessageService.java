package by.tsvetkov.service;

import by.tsvetkov.model.ChatMessage;
import by.tsvetkov.model.MessageStatus;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    long countNewMessages(Long senderId, Long recipientId);

    List<ChatMessage> findChatMessages(Long senderId, Long recipientId);

    ChatMessage findById(Long id);

    List<ChatMessage> findAll();

    void updateStatuses(Long senderId, Long recipientId, MessageStatus status);
}
