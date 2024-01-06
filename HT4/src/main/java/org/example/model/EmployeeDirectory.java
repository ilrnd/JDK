package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private List<Employee> employeeList = new ArrayList<>();

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }


    public List<Employee> findEmployeeByExperience(int  experience){
        List<Employee> result = new ArrayList<>();
        for(Employee employee: employeeList){
            if (employee.getWorkExperience() == experience){
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> findEmployeeByName(String name){
        List<Employee> result = new ArrayList<>();
        for(Employee employee: employeeList){
            if (employee.getName().equalsIgnoreCase(name)){
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> findEmployeeByPhoneNumber(String phoneNumber){
        List<Employee> result = new ArrayList<>();
        for(Employee employee: employeeList){
            if (employee.getPhoneNumber().equalsIgnoreCase(phoneNumber)){
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> findEmployeeByPersonnelNumber(int personnelNumber){
        List<Employee> result = new ArrayList<>();
        for(Employee employee: employeeList){
            if (employee.getPersonnelNumber() == personnelNumber){
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }



}
