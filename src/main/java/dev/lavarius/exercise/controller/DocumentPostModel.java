package dev.lavarius.exercise.controller;

import java.time.LocalDate;
import java.util.List;

public class DocumentPostModel {
    private static Integer id = 0;
    private String subject;
    private String author;
    private List<String> employees;
    private LocalDate date;
    private String information;

    public DocumentPostModel(String subject, String author, List<String> employees, LocalDate date, String information) {
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

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
