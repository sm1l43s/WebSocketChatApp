package by.tsvetkov.service;

import java.util.Optional;

public interface ChatRoomService {
    Optional<Long> getChatId(Long senderId, Long recipientId, boolean createIfNotExist);
}
