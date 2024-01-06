package org.example.db;

import org.example.model.Employee;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Repository {
    private FileReader fileReader;
    private FileWriter fileWriter;
    private final File employeeDataBase = new File("employees.txt");


    @Override
    public List<Employee> readFromFile() {
        List<Employee> employees = new ArrayList<>();
        try {
            fileReader = new FileReader(employeeDataBase);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null){
                String [] data = line.split(" ");
                Employee employee = new Employee();
                employee.setPersonnelNumber(Integer.parseInt(data[0]));
                employee.setName(data[1]);
                employee.setPhoneNumber(data[2]);
                employee.setWorkExperience(Integer.parseInt(data[3]));
                employees.add(employee);
                line = bufferedReader.readLine();
            }
            fileReader.close();
        } catch (IOException e){
            System.out.println("Problem with file");
        }
        return employees;
    }

    @Override
    public void writeToFile(Employee employee) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(employee.getPersonnelNumber());
        stringBuilder.append(" ");
        stringBuilder.append(employee.getName());
        stringBuilder.append(" ");
        stringBuilder.append(employee.getPhoneNumber());
        stringBuilder.append(" ");
        stringBuilder.append(employee.getWorkExperience());
        stringBuilder.append("\n");
        try {
            fileWriter = new FileWriter(employeeDataBase, true);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Problems with file");
        }
    }
}
