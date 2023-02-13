package dev.lavarius.exercise;

import dev.lavarius.exercise.controller.DocumentGetModel;
import dev.lavarius.exercise.statemachine.Event;
import dev.lavarius.exercise.statemachine.State;
import org.springframework.statemachine.StateMachine;

public record DocumentMachinePair(DocumentGetModel documentGetModel, StateMachine<State, Event> stateMachine) {
}
