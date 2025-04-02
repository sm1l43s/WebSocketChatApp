package by.tsvetkov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/*
 * Представляет сообщение в чате в приложении для обмена сообщениями.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "chat_messages")
public class ChatMessage {

    /*
     * Уникальный идентификатор сообщения.
     */
    @Id
    private Long id;

    /*
     * Уникальный идентификатор чата, к которому принадлежит это сообщение.
     */
    private Long chatId;

    /*
     * Уникальный идентификатор отправителя сообщения.
     */
    private Long senderId;

    /*
     * Уникальный идентификатор получателя сообщения.
     */
    private Long recipientId;

    /*
     * Имя отправителя сообщения.
     */
    private String senderName;

    /*
     * Имя получателя сообщения.
     */
    private String recipientName;

    /*
     * Содержимое сообщения.
     */
    private String content;

    /*
     * Время, когда сообщение было отправлено.
     */
    private Date timestamp;

    /*
     * Статус сообщения
     */
    private MessageStatus status;
}
