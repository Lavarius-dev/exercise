package dev.lavarius.exercise.config;

import dev.lavarius.exercise.statemachine.action.*;
import dev.lavarius.exercise.statemachine.event.Event;
import dev.lavarius.exercise.statemachine.listener.DocumentEventListener;
import dev.lavarius.exercise.statemachine.persist.DocumentStatePersister;
import dev.lavarius.exercise.statemachine.state.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<State, Event> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
        config.withConfiguration().autoStartup(true).listener(new DocumentEventListener());
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
                .action(setEmployeesAction(), errorAction())
                .and().withExternal()
                .source(State.EXECUTION).target(State.CONTROL).event(Event.REPORT)
                .action(reportAction(), errorAction())
                .and().withExternal()
                .source(State.CONTROL).target(State.REWORK).event(Event.REJECT)
                .action(rejectDocumentAction(), errorAction())
                .and().withExternal()
                .source(State.CONTROL).target(State.RECEIVE).event(Event.ACCEPT)
                .action(acceptDocumentAction(), errorAction())
                .and().withExternal()
                .source(State.REWORK).target(State.EXECUTION).event(Event.REWORK_DOCUMENT)
                .action(reworkDocumentAction(), errorAction());
    }

    @Bean
    public Action<State, Event> acceptDocumentAction() {
        return new AcceptDocumentAction();
    }

    @Bean
    public Action<State, Event> rejectDocumentAction() {
        return new RejectDocumentAction();
    }

    @Bean
    public Action<State, Event> setEmployeesAction() {
        return new SetEmployeesAction();
    }

    @Bean
    public Action<State, Event> reportAction() {
        return new ReportAction();
    }

    @Bean
    public Action<State, Event> reworkDocumentAction() {
        return new ReworkDocumentAction();
    }

    @Bean
    public Action<State, Event> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public StateMachinePersister<State, Event, Integer> persister() {
        return new DefaultStateMachinePersister<>(new DocumentStatePersister());
    }
}
