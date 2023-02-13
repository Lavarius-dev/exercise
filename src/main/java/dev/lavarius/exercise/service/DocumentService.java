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

    Boolean acceptDocument(Integer id);

    Boolean rejectDocument(Integer id);

    Boolean setWorkers(Integer id, List<String> employees);

    Boolean report(Integer id);

    Boolean reworkDocument(Integer id);


}
