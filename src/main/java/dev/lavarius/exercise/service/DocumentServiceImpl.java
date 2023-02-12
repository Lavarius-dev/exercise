package dev.lavarius.exercise.service;

import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.controller.DocumentPostModel;
import dev.lavarius.exercise.controller.DocumentPutModel;
import dev.lavarius.exercise.statemachine.Event;
import dev.lavarius.exercise.statemachine.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private StateMachine<State, Event> stateMachine;
    private List<DocumentGetModel> documents = new ArrayList<>();

    @Override
    public void deleteDocument(Integer id) {
        documents.removeIf(document -> document.getId().equals(id));
    }

    @Override
    public DocumentGetModel createDocument(DocumentPostModel documentPostModel) {
        if (documentPostModel.getDate().isBefore(LocalDate.now()) ||
                documentPostModel.getDate().isEqual(LocalDate.now())) {
            throw new DateTimeException("Invalid date format!");
        } else {
            DocumentGetModel newDocument = new DocumentGetModel(
                    documentPostModel.getId(), documentPostModel.getSubject(), documentPostModel.getAuthor(),
                    Collections.emptyList(), documentPostModel.getDate(), false, false,
                    documentPostModel.getInformation()
            );
            documents.add(newDocument);
            return newDocument;
        }
    }

    @Override
    public DocumentGetModel editDocument(DocumentPutModel documentPutModel, Integer id) {
        DocumentGetModel editDocument = getDocumentById(id);
        editDocument.setInformation(documentPutModel.getInformation());
        setEmployees(id, documentPutModel.getEmployees());
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

    /* Ниже функция добавления исполнителей
        Либо сделать так, либо в функцию editDocument добавить условие
        if (!employees.isEmpty()){
            document.setEmployees(employees);
            document.setAttributeOfPerformance(true);
            stateMachine.sendEvent(Event.SET_EMPLOYEES);
        }
    */
    @Override
    public void setEmployees(Integer id, List<String> employees) {
        var document = getDocumentById(id);
        if (!employees.isEmpty()) {
            document.setEmployees(employees);
            stateMachine.sendEvent(Event.SET_EMPLOYEES);
            document.setAttributeOfPerformance(true);
        }
    }

    @Override
    public void report(Integer id) {
        var document = getDocumentById(id);
        stateMachine.sendEvent(Event.REPORT);
        document.setAttributeOfPerformance(false);
        document.setAttributeOfControl(true);
    }
}
