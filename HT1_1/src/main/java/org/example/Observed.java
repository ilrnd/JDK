package org.example;

/**
 * Интерфейс наблюдаемого
 */

public interface Observed {
    /**
     * Добавление наблюдателя - в нашем случае GUI
     * @param observer интерфейс GUI
     */
    void addObserver(Observer observer);

    /**
     * Удаление наблюдателя - в нашем случае GUI
     * @param observer интерфейс GUI
     */
    void removeObserver(Observer observer);

    /**
     * Уведомление наблюдателей об изменении лога (добавление сообщений)
     */
    void notifyObserver();
}
