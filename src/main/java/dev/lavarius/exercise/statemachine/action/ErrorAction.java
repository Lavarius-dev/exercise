package dev.lavarius.exercise.statemachine.action;

import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ErrorAction implements Action<State, Event> {
    @Override
    public void execute(StateContext<State, Event> stateContext) {
        System.out.println("Ошибка при переходе в состояние " + stateContext.getTarget().getId());
    }
}
