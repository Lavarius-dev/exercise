package dev.lavarius.exercise.statemachine.listener;

import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.transition.Transition;

public class DocumentEventListener implements StateMachineListener<State, Event> {

    @Override
    public void stateChanged(org.springframework.statemachine.state.State<State, Event> from,
                             org.springframework.statemachine.state.State<State, Event> to) {
        if (from.getId() != null) {
            System.out.println("Переход из состояния " + from.getId() + " в состояние " + to.getId());
        }
    }

    @Override
    public void stateEntered(org.springframework.statemachine.state.State<State, Event> state) {

    }

    @Override
    public void stateExited(org.springframework.statemachine.state.State<State, Event> state) {

    }

    @Override
    public void eventNotAccepted(Message<Event> message) {
        System.out.println("Событие не принято " + message);
    }

    @Override
    public void transition(Transition<State, Event> transition) {

    }

    @Override
    public void transitionStarted(Transition<State, Event> transition) {

    }

    @Override
    public void transitionEnded(Transition<State, Event> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<State, Event> stateMachine) {
        System.out.println("Ура! Заработало!");
    }

    @Override
    public void stateMachineStopped(StateMachine<State, Event> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<State, Event> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<State, Event> stateContext) {

    }
}
