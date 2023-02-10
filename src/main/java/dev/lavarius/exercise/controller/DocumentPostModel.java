package dev.lavarius.exercise.controller;

import java.util.ArrayList;

public class DocumentPostModel {
    private static Integer id = 0;
    private String subject;
    private String author;
    private ArrayList<String> employees;
    private String date;
    private String information;

    public DocumentPostModel(String subject, String author, ArrayList<String> employees, String date, String information) {
        this.subject = subject;
        this.author = author;
        this.employees = employees;
        this.date = date;
        this.information = information;
        id++;
    }

    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
