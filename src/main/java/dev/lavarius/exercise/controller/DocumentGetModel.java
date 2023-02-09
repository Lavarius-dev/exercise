package dev.lavarius.exercise.controller;

import java.util.ArrayList;

public class DocumentGetModel {
    private Integer id;
    private String subject;
    private String author;
    private ArrayList<String> employees;
    private String date;
    private Boolean attributeOfControl;
    private Boolean attributeOfPerformance;
    private String information;

    public DocumentGetModel(Integer id, String subject, String author, ArrayList<String> performers,
                            String date, Boolean signControl, Boolean signPerformance, String information) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.employees = performers;
        this.date = date;
        this.attributeOfControl = signControl;
        this.attributeOfPerformance = signPerformance;
        this.information = information;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getAttributeOfControl() {
        return attributeOfControl;
    }

    public void setAttributeOfControl(Boolean attributeOfControl) {
        this.attributeOfControl = attributeOfControl;
    }

    public Boolean getAttributeOfPerformance() {
        return attributeOfPerformance;
    }

    public void setAttributeOfPerformance(Boolean attributeOfPerformance) {
        this.attributeOfPerformance = attributeOfPerformance;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "DocumentGetModel{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", author='" + author + '\'' +
                ", employees=" + employees +
                ", date='" + date + '\'' +
                ", attributeOfControl=" + attributeOfControl +
                ", attributeOfPerformance=" + attributeOfPerformance +
                ", information='" + information + '\'' +
                '}';
    }
}
