package dev.lavarius.exercise.statemachine.persist;

import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.state.State;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class DocumentStatePersister implements StateMachinePersist<State, Event, Integer> {
    private HashMap<Integer, StateMachineContext<State, Event>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<State, Event> stateMachineContext, Integer integer) throws Exception {
        contexts.put(integer, stateMachineContext);
    }

    @Override
    public StateMachineContext<State, Event> read(Integer integer) throws Exception {
        return contexts.get(integer);
    }
}
