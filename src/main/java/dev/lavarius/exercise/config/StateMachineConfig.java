package dev.lavarius.exercise.config;

import dev.lavarius.exercise.statemachine.Event;
import dev.lavarius.exercise.statemachine.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<State, Event> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
        config.withConfiguration().autoStartup(true);
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
                .action(setEmployeesAction())
                .and().withExternal()
                .source(State.EXECUTION).target(State.CONTROL).event(Event.REPORT)
                .action(reportAction())
                .and().withExternal()
                .source(State.CONTROL).target(State.REWORK).guard(rejectedGuard()).event(Event.REJECT)
                .action(rejectDocumentAction())
                .and().withExternal()
                .source(State.CONTROL).target(State.RECEIVE).event(Event.ACCEPT)
                .action(acceptDocumentAction())
                .and().withExternal()
                .source(State.REWORK).target(State.EXECUTION).event(Event.REWORK_DOCUMENT)
                .action(reworkDocumentAction());
    }

    private Guard<State, Event> rejectedGuard() {
        return stateContext -> {
            return (Boolean) stateContext.getExtendedState().getVariables().get("rejected");
        };
    }

    private Action<State, Event> acceptDocumentAction() {
        return stateContext -> log.info("Document accepted!");
    }

    private Action<State, Event> rejectDocumentAction() {
        return stateContext -> {
            log.info("Need rework document!");
            stateContext.getExtendedState().getVariables().put("rejected", true);
        };
    }

    private Action<State, Event> setEmployeesAction() {
        return stateContext -> log.info("Need set employees!");
    }

    private Action<State, Event> reportAction() {
        return stateContext -> log.info("Document is ready to control");
    }

    private Action<State, Event> reworkDocumentAction() {
        return stateContext -> log.info("Document was reworked!");
    }
}
