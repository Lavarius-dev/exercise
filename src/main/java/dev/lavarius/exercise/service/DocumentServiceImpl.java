package dev.lavarius.exercise.service;

import dev.lavarius.exercise.statemachine.DocumentMachinePair;
import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.controller.DocumentPostModel;
import dev.lavarius.exercise.controller.DocumentPutModel;
import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {
    private StateMachineFactory<State, Event> stateMachineFactory;
    private StateMachinePersister<State, Event, Integer> persister;
    private List<DocumentMachinePair> documentMachinePairs = new ArrayList<>();

    public DocumentServiceImpl(StateMachineFactory<State, Event> stateMachineFactory, StateMachinePersister<State, Event, Integer> persister) {
        this.stateMachineFactory = stateMachineFactory;
        this.persister = persister;
    }


    @Override
    public void deleteDocument(Integer id) {
        documentMachinePairs.removeIf(
                documentMachinePair -> documentMachinePair.documentGetModel().getId().equals(id));
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
            stateMachine.getExtendedState().getVariables().put("DOCUMENT_ID", newDocument.getId());
            try {
                persister.persist(stateMachine, newDocument.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            documentMachinePairs.add(new DocumentMachinePair(newDocument, stateMachine));
            return newDocument;
        }
    }

    @Override
    public DocumentGetModel editDocument(DocumentPutModel documentPutModel, Integer id) {
        DocumentMachinePair object = documentMachinePairs.stream().
                filter(documentMachinePair ->
                        documentMachinePair.documentGetModel().getId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("Document not found!"));
        object.documentGetModel().setInformation(documentPutModel.getInformation());
        if (!object.stateMachine().getState().getId().equals(State.REWORK)) {
            throw new RuntimeException("Невозможно выполнить изменение документа");
        } else {
            try {
                persister.persist(object.stateMachine(), id);
                object.stateMachine().sendEvent(Event.REWORK_DOCUMENT);
                object.documentGetModel().setAttributeOfPerformance(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return object.documentGetModel();
        }
    }

    @Override
    public DocumentGetModel getDocumentById(Integer id) {
        return documentMachinePairs.stream().filter(
                documentMachinePair -> documentMachinePair.documentGetModel().getId().equals(id)
        ).findAny().orElseThrow(() -> new NoSuchElementException("Document not found!")).documentGetModel();
    }

    @Override
    public Page<DocumentGetModel> getAllDocuments(Map<String, String> parameters) {
        List<DocumentGetModel> documents = new ArrayList<>();
        for (DocumentMachinePair documentMachinePair : documentMachinePairs) {
            documents.add(documentMachinePair.documentGetModel());
        }
        return new PageImpl<>(documents);
    }

    @Override
    public void acceptDocument(Integer id) {
        DocumentMachinePair object = documentMachinePairs.stream().
                filter(documentMachinePair ->
                        documentMachinePair.documentGetModel().getId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("Document not found!"));
        if (!object.stateMachine().getState().getId().equals(State.CONTROL)) {
            throw new RuntimeException("Невозможно выполнить изменение документа");
        } else {
            try {
                persister.persist(object.stateMachine(), id);
                object.stateMachine().sendEvent(Event.ACCEPT);
                object.documentGetModel().setAttributeOfControl(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void rejectDocument(Integer id) {
        DocumentMachinePair object = documentMachinePairs.stream().
                filter(documentMachinePair ->
                        documentMachinePair.documentGetModel().getId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("Document not found!"));
        if (!object.stateMachine().getState().getId().equals(State.CONTROL)) {
            throw new RuntimeException("Невозможно выполнить изменение документа");
        } else {
            try {
                persister.persist(object.stateMachine(), id);
                object.stateMachine().sendEvent(Event.REJECT);
                object.documentGetModel().setAttributeOfControl(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setWorkers(Integer id, List<String> employees) {
        DocumentMachinePair object = documentMachinePairs.stream().
                filter(documentMachinePair ->
                        documentMachinePair.documentGetModel().getId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("Document not found!"));
        if (!object.stateMachine().getState().getId().equals(State.PREPARATION)) {
            throw new RuntimeException("Невозможно выполнить изменение документа");
        } else {
            try {
                persister.persist(object.stateMachine(), id);
                object.stateMachine().sendEvent(Event.SET_EMPLOYEES);
                object.documentGetModel().setAttributeOfPerformance(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void report(Integer id) {
        DocumentMachinePair object = documentMachinePairs.stream().
                filter(documentMachinePair ->
                        documentMachinePair.documentGetModel().getId().equals(id))
                .findAny().orElseThrow(() -> new NoSuchElementException("Document not found!"));
        if (!object.stateMachine().getState().getId().equals(State.EXECUTION)) {
            throw new RuntimeException("Невозможно выполнить изменение документа");
        } else {
            try {
                persister.persist(object.stateMachine(), id);
                object.stateMachine().sendEvent(Event.REPORT);
                object.documentGetModel().setAttributeOfControl(true);
                object.documentGetModel().setAttributeOfPerformance(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
