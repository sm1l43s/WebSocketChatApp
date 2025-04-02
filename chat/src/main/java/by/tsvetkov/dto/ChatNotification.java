package by.tsvetkov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Класс, представляющий уведомление о чате.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatNotification {

    /*
     * Идентификатор уведомления.
     */
    private Long id;

    /*
     * Идентификатор отправителя сообщения.
     */
    private Long senderId;

    /*
     * Имя отправителя сообщения.
     */
    private String senderName;
}
