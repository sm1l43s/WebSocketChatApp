package by.tsvetkov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Представляет комнату чата в приложении для обмена сообщениями.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chat_rooms")
public class ChatRoom {

    /*
     * Уникальный идентификатор комнаты чата.
     */
    @Id
    private Long id;

    /*
     * Уникальный идентификатор чата, к которому принадлежит эта комната.
     */
    private Long chatId;

    /*
     * Уникальный идентификатор отправителя, связанного с этой комнатой.
     */
    private Long senderId;

    /*
     * Уникальный идентификатор получателя, связанного с этой комнатой.
     */
    private Long recipientId;
}
