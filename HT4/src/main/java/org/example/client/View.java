package org.example.client;

public interface View {
    /**
     * Метод добавления сотрудника
     */
    void addEmployee();

    /**
     * Поиск сотрудника по табельному номеру
     * @param personalNumber табельный номер
     */
    void findEmployeeByPersonalNumber(int personalNumber);

    /**
     * Поиск сотрудника по имени
     * @param name имя сотрудника
     */
    void findEmployeeByName(String name);

    /**
     * Поиск сотрудника по номеру телефона
     * @param phoneNumber номер телефона
     */
    void findEmployeeByPhoneNumber(String phoneNumber);

    /**
     * Поиск сотрудника по опыту работы
     * @param experience опыт работы
     */
    void findEmployeeByExperience(int experience);
}
