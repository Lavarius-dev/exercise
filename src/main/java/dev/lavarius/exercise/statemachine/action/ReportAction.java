package dev.lavarius.exercise.statemachine.action;

import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ReportAction implements Action<State, Event> {
    @Override
    public void execute(StateContext<State, Event> stateContext) {
        Integer documentId = stateContext.getExtendedState().get("DOCUMENT_ID", Integer.class);
        System.out.println("Документ номер " + documentId + " отправлен на проверку!");
    }
}
