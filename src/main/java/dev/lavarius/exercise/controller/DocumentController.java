package dev.lavarius.exercise.controller;

import dev.lavarius.exercise.model.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private List<Document> documents = new ArrayList<>();

    @GetMapping()
    public List<Document> getAllDocuments() {
        return documents;
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Integer id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findAny().orElse(null);
    }

    @PostMapping("/create")
    public Document createDocument(@RequestBody Document document) {
        documents.add(document);
        return document;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDocument(@PathVariable Integer id) {
        documents.removeIf(document -> document.getId().equals(id));
    }

    public Document editDocument() {
        return null;
    }

    public String getDocumentsByAttribute() {
        return null;
    }
}
