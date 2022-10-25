package com.company.model;

import com.company.validator.TransitionValidator;

public class State {
    public static final int MAX_TRANSITIONS = 6;

    public Jug n;
    public Jug m;
    public int k;

    public State(int nCapacity, int mCapacity, int k) {
        this.n = new Jug(nCapacity);
        this.m = new Jug(mCapacity);
        this.k = k;
    }

    public State(State state) {
        this.n = new Jug(state.n);
        this.m = new Jug(state.m);
        this.k = state.k;
    }

    public boolean isFinal() {
        return m.content == k || n.content == k;
    }

    public boolean admitsSolution() {
        return areCoprime() && k <= n.capacity && k <= m.capacity;
    }

    private boolean areCoprime() {
        int x = n.capacity;
        int y = m.capacity;
        int r = n.capacity % m.capacity;

        while(x * y != 0) {
            x = y;
            y = r;

            if (y != 0) {
                r = x % y;
            }
        }

        return x == 1;
    }

    public State transform(Transition t) {
        if (!TransitionValidator.validate(n, m, t)) {
            return this;
        }

        State newState = new State(this);

        switch (t) {
            case FILL_M -> {
                newState.m.content = newState.m.capacity;
            }
            case FILL_N -> {
                newState.n.content = newState.n.capacity;
            }
            case EMPTY_M -> {
                newState.m.content = 0;
            }
            case EMPTY_N -> {
                newState.n.content = 0;
            }
            case M_TO_N -> {
                int quantity = Math.min(newState.m.content, newState.n.capacity - newState.n.content);
                newState.m.content -= quantity;
                newState.n.content += quantity;
            }
            case N_TO_M -> {
                int quantity = Math.min(newState.n.content, newState.m.capacity - newState.m.content);
                newState.m.content += quantity;
                newState.n.content -= quantity;
            }
            default -> { }
        }

          return newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return this.n.equals(state.n) && this.m.equals(state.m) && this.k == state.k;
    }

    @Override
    public int hashCode() {
        int result = n.hashCode();
        result = 31 * result + m.hashCode();
        result = 31 * result + k;
        return result;
    }

    @Override
    public String toString() {
        return "(" + n.content +
                ", " + m.content +
                ")\n";
    }
}