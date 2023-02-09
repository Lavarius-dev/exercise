package dev.lavarius.exercise.controller;

import java.util.ArrayList;

public class DocumentEditModel {
    private ArrayList<String> employees;
    private String information;

    public DocumentEditModel(ArrayList<String> employees, String information) {
        this.employees = employees;
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }
}
