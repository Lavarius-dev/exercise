package dev.lavarius.exercise.config;

import dev.lavarius.exercise.statemachine.Event;
import dev.lavarius.exercise.statemachine.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;
import java.util.Optional;

@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<State, Event> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
        config.withConfiguration().autoStartup(true).listener(listener());
    }

    private StateMachineListener<State, Event> listener() {
        return new StateMachineListenerAdapter<State, Event>() {
            @Override
            public void transition(Transition<State, Event> transition) {
                log.info("Change state from {} to {}",
                        transition.getSource().getId(), transition.getTarget().getId());
            }
        };
    }


    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        states.withStates()
                .initial(State.PREPARATION)
                .end(State.RECEIVE)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        transitions.withExternal()
                .source(State.PREPARATION).target(State.EXECUTION).event(Event.SET_EMPLOYEES)
                .action(setEmployees())
                .and().withExternal()
                .source(State.EXECUTION).target(State.CONTROL).event(Event.REPORT)
                .action(report())
                .and().withExternal()
                .source(State.CONTROL).target(State.REWORK).event(Event.REJECT)
                .action(checkDocumentToReject())
                .and().withExternal()
                .source(State.CONTROL).target(State.RECEIVE).event(Event.ACCEPT)
                .action(checkDocumentToAccept());
        // добавить еще ветку на rework -> execution
    }

    private Action<State, Event> checkDocumentToAccept() {
        return stateContext -> log.info("Document accepted!");
    }

    private Action<State, Event> checkDocumentToReject() {
        return stateContext -> log.info("Need rework document!");
    }

    private Action<State, Event> setEmployees() {
        return stateContext -> log.info("Need set employees!");
    }

    private Action<State, Event> report() {
        return stateContext -> log.info("Document is ready to control");
    }
}
