package dev.lavarius.exercise.statemachine;

import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.statemachine.StateMachine;

public record DocumentMachinePair(DocumentGetModel documentGetModel, StateMachine<State, Event> stateMachine) {
}
