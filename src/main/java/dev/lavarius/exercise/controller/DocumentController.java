package dev.lavarius.exercise.controller;

import dev.lavarius.exercise.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// добавить операции
@RestController
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping()
    public Page<DocumentGetModel> getAllDocuments(@RequestParam Map<String, String> parameters) {
        return documentService.getAllDocuments(parameters);
    }

    @GetMapping("/{id}")
    public DocumentGetModel getDocumentById(@PathVariable Integer id) {
        return documentService.getDocumentById(id);
    }

    @PostMapping()
    public DocumentGetModel createDocument(@RequestBody DocumentPostModel document) {
        return documentService.createDocument(document);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Integer id) {
        documentService.deleteDocument(id);
    }

    @PutMapping("/{id}")
    public DocumentGetModel editDocument(@RequestBody DocumentPutModel document, @PathVariable Integer id) {
        return documentService.editDocument(document, id);
    }

    @PutMapping("/{id}/setEmployees")
    public void setEmployees(@PathVariable Integer id, @RequestBody Map<String, List<String>> employees) {
        documentService.setWorkers(id, employees.get("employees"));
    }

    @PutMapping("/{id}/report")
    public void report(@PathVariable Integer id) {
        documentService.report(id);
    }

    @PutMapping("/{id}/accept")
    public void acceptDocument(@PathVariable Integer id) {
        documentService.acceptDocument(id);
    }

    @PutMapping("/{id}/reject")
    public void rejectDocument(@PathVariable Integer id) {
        documentService.rejectDocument(id);
    }

}
