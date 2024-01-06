package org.example.model;

public class Employee {
    private int personnelNumber;
    private String name;
    private String phoneNumber;
    private int workExperience;

    public int getWorkExperience() {
        return workExperience;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

}
