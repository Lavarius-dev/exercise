package dev.lavarius.exercise.controller;

import java.util.List;

public class DocumentPutModel {
    private List<String> employees;
    private String information;

    public DocumentPutModel(List<String> employees, String information) {
        this.employees = employees;
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
