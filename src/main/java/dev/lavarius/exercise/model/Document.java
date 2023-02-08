package dev.lavarius.exercise.model;

public class Document {
    private Long id;
    private String subject;
    private String author;
    private String performers;
    private String data;
    private Boolean signControl;
    private Boolean signPerformance;
    private String information;

    public Document(Long id, String subject, String author,
                    String performers, String data, Boolean signControl,
                    Boolean signPerformance, String information) {
        this.id = id;
        this.subject = subject;
        this.author = author;
        this.performers = performers;
        this.data = data;
        this.signControl = signControl;
        this.signPerformance = signPerformance;
        this.information = information;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
                ", data='" + data + '\'' +
                ", signControl=" + signControl +
                ", signPerformance=" + signPerformance +
                ", information='" + information + '\'' +
                '}';
    }
}
