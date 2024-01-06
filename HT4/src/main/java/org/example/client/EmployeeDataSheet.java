package org.example.client;

import org.example.db.Repository;
import org.example.model.Employee;
import org.example.model.EmployeeDirectory;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Класс табличного отображения информации
 */
public class EmployeeDataSheet extends DefaultTableModel {
    private EmployeeDirectory employeeDirectory = new EmployeeDirectory();
    private List<Employee> searchResult;
    private Repository repository;
    private View view;

    private final String[] columnNames = new String[] {
            "Табельный номер", "Имя", "Номер телефона", "Опыт работы"
    };
    private final Class[] columnClass = new Class[] {
            Integer.class, String.class, String.class, Integer.class
    };

    /**
     * Конструктор класса
     * @param repository интерфейс хранения/чтения данных
     * @param view интерфейс отображения данных
     */
    public EmployeeDataSheet(Repository repository, View view){
        this.repository = repository;
        this.view = view;
    }


    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    /**
     * Добавление сотрудника в коллекцию, добавление в таблицу
     * @param rowData массив данных о сотруднике
     */
    public void addEmployee(Object[] rowData){
        Employee employee = new Employee();
        employee.setPersonnelNumber((Integer)rowData[0]);
        employee.setName((String)rowData[1]);
        employee.setPhoneNumber((String)rowData[2]);
        employee.setWorkExperience((Integer)rowData[3]);
        employeeDirectory.addEmployee(employee);
        addRow(convertToVector(rowData));
        repository.writeToFile(employee);
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    /**
     * Получение данных об именах столбцов таблицы данных
     * @return массив имен столбцов
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * Поиск сотрудника по опыту работы
     * @param experience опыт работы
     */
    public void findEmployeeByExperience(int experience){
        clearDataSheet();
        searchResult = employeeDirectory.findEmployeeByExperience(experience);
        updateDataSheet(searchResult);
    }

    /**
     * Поиск сотрудника по табельному номеру
     * @param personalNumber табельный номер
     */
    public void findEmployeeByPersonalNumber(int personalNumber){
        clearDataSheet();
        searchResult = employeeDirectory.findEmployeeByPersonnelNumber(personalNumber);
        updateDataSheet(searchResult);
    }

    /**
     * Поиск сотрудника по имени
     * @param name имя сотрудника
     */
    public void findEmployeeByName(String name){
        clearDataSheet();
        searchResult = employeeDirectory.findEmployeeByName(name);
        updateDataSheet(searchResult);
    }

    /**
     * Поиск сотрудника по номеру телефона
     * @param phoneNumber номер телефона сотрудника
     */
    public void findEmployeeByPhoneNumber(String phoneNumber){
        clearDataSheet();
        searchResult = employeeDirectory.findEmployeeByPhoneNumber(phoneNumber);
        updateDataSheet(searchResult);
    }

    /**
     * Очистка таблицы данных
     */
    private void clearDataSheet(){
        if (this.getRowCount() > 0) {
            for (int i = this.getRowCount() - 1; i > -1; i--) {
                this.removeRow(i);
            }
        }
    }

    /**
     * Обновление таблицы данных
     * @param employees коллекция сотрудников
     */
    public void updateDataSheet(List<Employee> employees){
        clearDataSheet();
        Object [] data = new Object[4];
        for(Employee employee : employees){
            data[0] = employee.getPersonnelNumber();
            data[1] = employee.getName();
            data[2] = employee.getPhoneNumber();
            data[3] = employee.getWorkExperience();
            addRow(data);
        }
    }

    /**
     * Загрузка данных о сотрудниках в коллекцию и таблицу данных
     */
    public void loadData() {
        clearDataSheet();
        searchResult = repository.readFromFile();
        employeeDirectory.setEmployeeList(searchResult);
        updateDataSheet(employeeDirectory.getEmployeeList());
    }

}
