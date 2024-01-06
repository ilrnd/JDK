package org.example.client;

import org.example.db.FileStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс визуального отображения данных о сотрудниках
 */
public class ClientsGUI extends JFrame implements View{
    private EmployeeDataSheet model;
    private static final int WIDHT = 600;
    private static final int HEIGHT = 500;

    private JTextField tfPersonalNumber, tfName, tfPhoneNumber, tfWorkExperience, tfSearch;
    private JComboBox searchList;
    private JTable employeesTable;
    private Object[] data = new Object[4];
    private JButton btnAddEmployee, btnSearch, btnShowAll;

    private JPanel panelTopAddEmployee, panelTopSearch, panelMiddle;

    /**
     * Конструктор класса
     */
    public ClientsGUI() {
        model = new EmployeeDataSheet(new FileStorage(), this);
        showAll();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDHT, HEIGHT);
        setTitle("Данные о сотрудниках");
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(createPanelTopAddEmployee(), constraints);

        constraints.gridy = 1;
        add(createPanelTopSearch(), constraints);

        constraints.gridy = 2;
        constraints.ipady = 300;
        add(createPanelMiddle(), constraints);

        setVisible(true);
    }

    /**
     * Создание верхней панели добавления сотрудника
     * @return компонент верхней панели
     */
    private Component createPanelTopAddEmployee(){
        JLabel labelPersonalNumber = new JLabel("Табельный номер");
        JLabel labelName = new JLabel("Имя");
        JLabel labelPhoneNumber = new JLabel("Номер телефона");
        JLabel labelWorkExperience = new JLabel("Опыт работы");
        tfPersonalNumber = new JTextField();
        tfName = new JTextField();
        tfPhoneNumber = new JTextField();
        tfWorkExperience = new JTextField();
        panelTopAddEmployee = new JPanel(new GridLayout(5,2));
        panelTopAddEmployee.add(labelPersonalNumber);
        panelTopAddEmployee.add(tfPersonalNumber);
        panelTopAddEmployee.add(labelName);
        panelTopAddEmployee.add(tfName);
        panelTopAddEmployee.add(labelPhoneNumber);
        panelTopAddEmployee.add(tfPhoneNumber);
        panelTopAddEmployee.add(labelWorkExperience);
        panelTopAddEmployee.add(tfWorkExperience);
        panelTopAddEmployee.add(createButtonAddEmployee());
        panelTopAddEmployee.add(createButtonShowAll());
        panelTopAddEmployee.setVisible(true);
        return panelTopAddEmployee;
    }

    /**
     * Создание верхней панели поиска сотрудника
     * @return панель поиска сотрудника
     */
    private Component createPanelTopSearch(){
        panelTopSearch = new JPanel(new GridLayout(1,2));
        String [] items = model.getColumnNames();
        searchList = new JComboBox<>(items);
        tfSearch = new JTextField();
        panelTopSearch.add(searchList);
        panelTopSearch.add(tfSearch);
        panelTopSearch.add(createButtonSearch());
        panelTopSearch.setVisible(true);
        return panelTopSearch;
    }

    /**
     * Создание области отображения данные о сотрудника
     * @return панель отображения данных о сотрудников
     */
    private Component createPanelMiddle(){
        employeesTable = new JTable(model);
        panelMiddle = new JPanel(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane(employeesTable);
        panelMiddle.add(jScrollPane);
        return panelMiddle;
    }

    /**
     * Создание кнопки добавления сотрудника
     * @return кнопка добавления сотрудника
     */
    private Component createButtonAddEmployee(){
        btnAddEmployee = new JButton("Добавить");
        btnAddEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        return btnAddEmployee;
    }


    /**
     * Создание кнопки поиска сотрудника
     * @return кнопка поиска сотрудника
     */
    private Component createButtonSearch(){
        btnSearch = new JButton("Поиск сотрудника");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (searchList.getSelectedIndex()){
                    case 0:
                        findEmployeeByPersonalNumber(stringToInt(tfSearch.getText()));
                        break;
                    case 1:
                        findEmployeeByName(tfSearch.getText());
                        break;
                    case 2:
                        findEmployeeByPhoneNumber(tfSearch.getText());
                        break;
                    case 3:
                        findEmployeeByExperience(stringToInt(tfSearch.getText()));
                        break;
                }
                tfSearch.setText("");
            }
        });
        return btnSearch;
    }

    /**
     * Создание кнопки отображения всех сотрудников
     * @return кнопка отображения всех сотрудников
     */
    private Component createButtonShowAll(){
        btnShowAll = new JButton("Показать все");
        btnShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
            }
        });
        return btnShowAll;
    }

    @Override
    public void addEmployee() {
        data[0] = stringToInt(tfPersonalNumber.getText());
        data[1] = tfName.getText();
        data[2] = tfPhoneNumber.getText();
        data[3] = stringToInt(tfWorkExperience.getText());
        model.addEmployee(data);
        tfPersonalNumber.setText("");
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfWorkExperience.setText("");
    }

    @Override
    public void findEmployeeByPersonalNumber(int personalNumber) {
        model.findEmployeeByPersonalNumber(personalNumber);
    }

    @Override
    public void findEmployeeByName(String name) {
        model.findEmployeeByName(name);
    }

    @Override
    public void findEmployeeByPhoneNumber(String phoneNumber) {
        model.findEmployeeByPhoneNumber(phoneNumber);
    }

    @Override
    public void findEmployeeByExperience(int experience) {
        model.findEmployeeByExperience(experience);
    }


    /**
     * Метод отображения всех сотрудников
     */
    private void showAll(){
        model.loadData();
    }

    /**
     * Метод преобразования строкового значения в целочисленный
     * @param value строковое значение
     * @return целочисленное значение
     */
    private int stringToInt(String value){
        int number = 0;
        try{
        number = Integer.parseInt(value);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Табельный номер и опыт работы должны быть числами");
        }
        return number;
    }
}
