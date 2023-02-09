package dev.lavarius.exercise.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private List<DocumentGetModel> documents = new ArrayList<>();

    @GetMapping()
    public Page<DocumentGetModel> getAllDocuments(Pageable pageable) {
        return null;
    }

    @GetMapping("/{id}")
    public DocumentGetModel getDocumentById(@PathVariable Integer id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findAny()
                .orElseThrow(() -> new NoSuchElementException("Document not found!"));
    }

    @PostMapping()
    public DocumentGetModel createDocument(@RequestBody DocumentCreateModel document) {
        DocumentGetModel newDocument = new DocumentGetModel(
                document.getId(), document.getSubject(), document.getAuthor(),
                document.getEmployees(), document.getDate(), false,
                false, document.getInformation()
        );
        documents.add(newDocument);
        return newDocument;
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Integer id) {
        documents.removeIf(document -> document.getId().equals(id));
    }

    @PutMapping("/{id}")
    public DocumentGetModel editDocument(@RequestBody DocumentEditModel document, @PathVariable Integer id) {
        DocumentGetModel editDocument = getDocumentById(id);
        editDocument.setInformation(document.getInformation());
        editDocument.setEmployees(document.getEmployees());
        return editDocument;
    }
}
