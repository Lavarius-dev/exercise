package dev.lavarius.exercise.controller;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class DocumentGetModel {
    private Integer id;
    private String subject;
    private String author;
    private List<String> employees;

    @NotNull
    private LocalDate date;
    private Boolean attributeOfControl;
    private Boolean attributeOfPerformance;
    private String information;

    public DocumentGetModel(Integer id, String subject, String author,
                            List<String> performers, LocalDate date, Boolean attributeOfControl,
                            Boolean attributeOfPerformance, String information) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.employees = performers;
        this.date = date;
        this.attributeOfControl = attributeOfControl;
        this.attributeOfPerformance = attributeOfPerformance;
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

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
        return "DocumentGetModel{" + "id=" + id + ", subject='" + subject + '\'' + ", author='" + author + '\'' + ", employees=" + employees + ", date='" + date + '\'' + ", attributeOfControl=" + attributeOfControl + ", attributeOfPerformance=" + attributeOfPerformance + ", information='" + information + '\'' + '}';
    }
}
