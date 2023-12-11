package org.example;
import java.util.List;

/**
 * Интерфейс наблюдателя
 */
public interface Observer {
    /**
     * Обновление лога
     * @param message входящее сообщение для лога
     */
    void updateMessages(String message);

}
