package dev.lavarius.exercise.model;

public class Document {
    private Integer id;
    private String subject;
    private String author;
    private String performers;
    private String date;
    private Boolean signControl;
    private Boolean signPerformance;
    private String information;

    public Document(Integer id, String subject, String author,
                    String performers, String data, Boolean signControl,
                    Boolean signPerformance, String information) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.performers = performers;
        this.date = data;
        this.signControl = signControl;
        this.signPerformance = signPerformance;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPerformers() {
        return performers;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSignControl() {
        return signControl;
    }

    public void setSignControl(Boolean signControl) {
        this.signControl = signControl;
    }

    public Boolean getSignPerformance() {
        return signPerformance;
    }

    public void setSignPerformance(Boolean signPerformance) {
        this.signPerformance = signPerformance;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", author='" + author + '\'' +
                ", performers='" + performers + '\'' +
                ", data='" + date + '\'' +
                ", signControl=" + signControl +
                ", signPerformance=" + signPerformance +
                ", information='" + information + '\'' +
                '}';
    }
}
