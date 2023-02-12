package dev.lavarius.exercise.statemachine;

import java.util.Objects;

public enum State {
    PREPARATION {
        @Override
        public State nextState(Symbol symbol) {
            if (Objects.requireNonNull(symbol) == Symbol.SET_EMPLOYEES) {
                // вызов функции добавления работников
                return EXECUTION;
            }
            throw new RuntimeException("State machine error!");
        }
    },
    EXECUTION {
        @Override
        public State nextState(Symbol symbol) {
            if (Objects.requireNonNull(symbol) == Symbol.SET_EMPLOYEES) {
                // вызов функции добавления работников
                return this;
            } else if (Objects.requireNonNull(symbol) == Symbol.REPORT) {
                // вызов функции доклада, что поручение готово к проверке
                return CONTROL;
            } else throw new RuntimeException("State machine error!");
        }
    },
    CONTROL {
        @Override
        public State nextState(Symbol symbol) {
            switch (symbol) {

                case SET_EMPLOYEES, DECLINE -> {
                    // вновь добавить работников
                    return REWORK;
                }
                case REPORT -> throw new RuntimeException("State machine error!");
                case ACCEPT -> {
                    return RECEIVE;
                }
            }
            throw new RuntimeException("State machine error!");
        }
    },
    REWORK {
        @Override
        public State nextState(Symbol symbol) {
            // Узнать в чем разница от исполенения
            return null;
        }
    },

    RECEIVE {
        @Override
        public State nextState(Symbol symbol) {
            return this;
        }
    };
    public Boolean readyToFinish = false;

    public void changeStatus(boolean status) {
        readyToFinish = status;
    }

    public abstract State nextState(Symbol symbol);
}