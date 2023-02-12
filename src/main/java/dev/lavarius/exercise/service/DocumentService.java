package dev.lavarius.exercise.service;

import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.controller.DocumentPostModel;
import dev.lavarius.exercise.controller.DocumentPutModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    void deleteDocument(Integer id);

    DocumentGetModel createDocument(DocumentPostModel documentPostModel);

    DocumentGetModel editDocument(DocumentPutModel documentPutModel, Integer id);

    DocumentGetModel getDocumentById(Integer id);

    Page<DocumentGetModel> getAllDocuments(Map<String, String> parameters);

    void setEmployees(Integer id, List<String> employees);

    void report(Integer id);

//    void acceptDocument(Integer id);// Нужно доделать
//
//    void rejectDocument(Integer id);// Нужно доделать

}
