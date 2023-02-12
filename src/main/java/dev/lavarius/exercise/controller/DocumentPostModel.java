package dev.lavarius.exercise.controller;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class DocumentPostModel {
    private static Integer id = 0;
    private String subject;
    private String author;
    private LocalDate date;
    private String information;

    public DocumentPostModel(String subject, String author, LocalDate date, String information) {
        this.subject = subject;
        this.author = author;
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
