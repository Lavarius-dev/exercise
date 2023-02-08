package dev.lavarius.exercise.controller;

import dev.lavarius.exercise.model.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/documents")
public class DocumentController {

    private List<Document> documents = List.of(
            new Document(1L, "subj_1", "auth_1",
                    "perf_1, perf_2", "2 years",
                    false, false, "information 1"),
            new Document(2L, "subj_2", "auth_2",
                    "perf_3, perf_4", "5 years",
                    false, false, "information 2"),
            new Document(3L, "subj_3", "auth_3",
                    "perf_5, perf_6", "13 years",
                    false, false, "information 3")
    );

    @GetMapping()
    public List<Document> getAllDocuments() {
        return documents;
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findAny().orElse(null);
    }

    @PostMapping("/create")
    public String createDocument(Document document) {
        documents.add(document);
        return "redirect:/documents";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Long id) {
        documents.removeIf(document -> document.getId().equals(id));
        return "redirect:/documents";
    }

    public String editDocument() {
        return null;
    }

    public String getDocumentsByAttribute() {
        return null;
    }
}
