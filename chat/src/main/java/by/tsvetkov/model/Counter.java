package by.tsvetkov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Представляет счетчик в приложении.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "counters")
public class Counter {

    /*
     * Уникальный идентификатор счетчика.
     */
    @Id
    private String id;

    /*
     * Текущее значение последовательности счетчика.
     */
    private long sequence;
}
