package dev.lavarius.exercise.service;

import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.controller.DocumentPostModel;
import dev.lavarius.exercise.controller.DocumentPutModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private List<DocumentGetModel> documents = new ArrayList<>();

    @Override
    public void deleteDocument(Integer id) {
        documents.removeIf(document -> document.getId().equals(id));
    }

    @Override
    public DocumentGetModel createDocument(DocumentPostModel documentPostModel) {
        DocumentGetModel newDocument = new DocumentGetModel(
                documentPostModel.getId(), documentPostModel.getSubject(), documentPostModel.getAuthor(),
                documentPostModel.getEmployees(), documentPostModel.getDate(), false,
                false, documentPostModel.getInformation()
        );
        documents.add(newDocument);
        return newDocument;
    }

    @Override
    public DocumentGetModel editDocument(DocumentPutModel documentPutModel, Integer id) {
        DocumentGetModel editDocument = getDocumentById(id);
        editDocument.setInformation(documentPutModel.getInformation());
        editDocument.setEmployees(documentPutModel.getEmployees());
        return editDocument;
    }

    @Override
    public DocumentGetModel getDocumentById(Integer id) {
        return documents.stream().filter(document -> document.getId().equals(id)).findAny()
                .orElseThrow(() -> new NoSuchElementException("Document not found!"));
    }

    @Override
    public Page<DocumentGetModel> getAllDocuments(Map<String, String> parameters) {
        if (parameters.isEmpty()) return new PageImpl<>(documents);
        else {
            return new PageImpl<>(documents.stream().filter(documentGetModel ->
                    documentGetModel.getAuthor().equals(parameters.get("author"))).collect(Collectors.toList()));
        }
    }
}
