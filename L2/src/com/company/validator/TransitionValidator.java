package com.company.validator;

import com.company.model.Jug;
import com.company.model.Transition;

public class TransitionValidator {

    public static boolean validate(Jug n, Jug m, Transition t) {
        switch (t) {
            case FILL_M -> {
                return m.content < m.capacity;
            }
            case FILL_N -> {
                return n.content < n.capacity;
            }
            case EMPTY_M -> {
                return m.content > 0;
            }
            case EMPTY_N -> {
                return n.content > 0;
            }
            case M_TO_N -> {
                return m.content > 0 && n.content < n.capacity;
            }
            case N_TO_M -> {
                return n.content > 0 && m.content < m.capacity;
            }
            default -> {
                return false;
            }
        }
    }
}
