package org.example.db;

import org.example.model.Employee;

import java.util.List;

public interface Repository {
    /**
     * Чтение данных о сотрудниках из файла
     * @return список сотрудников
     */
    List<Employee> readFromFile();

    /**
     * Запись данных о сотрудниках в файл
     * @param employee объек сотрудника
     */
    void writeToFile(Employee employee);
}
