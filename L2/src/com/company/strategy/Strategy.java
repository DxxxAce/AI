package com.company.strategy;

import com.company.model.State;

import java.util.List;

public abstract class Strategy {
    protected State initialState;
    protected List<State> solution;

    public abstract List<State> run();
}
