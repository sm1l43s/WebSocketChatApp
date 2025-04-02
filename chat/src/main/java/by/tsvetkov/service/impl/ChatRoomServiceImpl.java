package by.tsvetkov.service.impl;

import by.tsvetkov.model.ChatRoom;
import by.tsvetkov.repository.ChatRoomRepository;
import by.tsvetkov.service.ChatRoomService;
import by.tsvetkov.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final CounterService counterService;

    @Override
    public Optional<Long> getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    Long chatId = counterService.getNextSequence("chatId");

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .id(counterService.getNextSequence("chatRoomId"))
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .id(counterService.getNextSequence("chatRoomId"))
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
