package dev.lavarius.exercise.service;

import dev.lavarius.exercise.DocumentMachinePair;
import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.controller.DocumentPostModel;
import dev.lavarius.exercise.controller.DocumentPutModel;
import dev.lavarius.exercise.statemachine.Event;
import dev.lavarius.exercise.statemachine.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
//    private List<DocumentGetModel> documents = new ArrayList<>();
    private StateMachineFactory<State, Event> stateMachineFactory;
    private List<DocumentMachinePair> documentMachinePairs = new ArrayList<>();

    public DocumentServiceImpl(StateMachineFactory<State, Event> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public void deleteDocument(Integer id) {
        documentMachinePairs.removeIf(
                documentMachinePair -> documentMachinePair.documentGetModel().getId().equals(id));
//        documents.removeIf(document -> document.getId().equals(id));
    }

    @Override
    public DocumentGetModel createDocument(DocumentPostModel documentPostModel) {
        if (documentPostModel.getDate().isBefore(LocalDate.now()) ||
                documentPostModel.getDate().isEqual(LocalDate.now())) {
            throw new DateTimeException("Invalid date format!");
        } else {
            StateMachine<State, Event> stateMachine = stateMachineFactory.getStateMachine();
            DocumentGetModel newDocument = new DocumentGetModel(
                    documentPostModel.getId(), documentPostModel.getSubject(), documentPostModel.getAuthor(),
                    Collections.emptyList(), documentPostModel.getDate(), false, false,
                    documentPostModel.getInformation()
            );
//            documents.add(newDocument);
            documentMachinePairs.add(new DocumentMachinePair(newDocument, stateMachine));
            return newDocument;
        }
    }

    @Override
    public DocumentGetModel editDocument(DocumentPutModel documentPutModel, Integer id) {
        DocumentGetModel editDocument = getDocumentById(id);
        editDocument.setInformation(documentPutModel.getInformation());
        editDocument.setEmployees(documentPutModel.getEmployees());
//        setEmployees(id, documentPutModel.getEmployees());
        return editDocument;
    }

    @Override
    public DocumentGetModel getDocumentById(Integer id) {
        return documentMachinePairs.stream().filter(
                documentMachinePair -> documentMachinePair.documentGetModel().getId().equals(id)
        ).findAny().orElseThrow(() -> new NoSuchElementException("Document not found!")).documentGetModel();
//        return documents.stream().filter(document -> document.getId().equals(id)).findAny()
//                .orElseThrow(() -> new NoSuchElementException("Document not found!"));
    }

    @Override
    public Page<DocumentGetModel> getAllDocuments(Map<String, String> parameters) {
        List<DocumentGetModel> documents = new ArrayList<>();
        for (DocumentMachinePair documentMachinePair : documentMachinePairs) {
            documents.add(documentMachinePair.documentGetModel());
        }
        if (parameters.isEmpty()) {
            return new PageImpl<>(documents);
        }
        else {
//            return new PageImpl<>(documents.stream().filter(documentGetModel ->
//                    documentGetModel.getAuthor().equals(parameters.get("author"))).collect(Collectors.toList()));
        }
        return new PageImpl<>(documents);
    }

    @Override
    public Boolean acceptDocument(Integer id) {
        return null;
    }

    @Override
    public Boolean rejectDocument(Integer id) {
        return null;
    }

    @Override
    public Boolean setWorkers(Integer id, List<String> employees) {
        DocumentGetModel document = getDocumentById(id);
        document.setEmployees(employees);
        return null;
    }

    @Override
    public Boolean report(Integer id) {
        return null;
    }

    @Override
    public Boolean reworkDocument(Integer id) {
        return null;
    }
}
