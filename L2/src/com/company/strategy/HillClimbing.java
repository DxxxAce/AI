package com.company.strategy;

import com.company.model.State;

import java.util.List;

public class HillClimbing extends Strategy {
    public HillClimbing(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public List<State> run() {
        return solution;
    }
}
