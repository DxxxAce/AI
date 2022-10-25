package com.company.strategy;

import com.company.model.State;
import com.company.model.Transition;

import java.util.*;

import static com.company.model.State.MAX_TRANSITIONS;

public class BFS extends Strategy {
    private final Queue<State> queue;
    private final Set<State> visited;

    public BFS(State initialState) {
        this.initialState = initialState;
        this.solution = new ArrayList<>();
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    @Override
    public List<State> run() {
        State currentState;
        queue.add(initialState);

        while (!queue.isEmpty()) {
            currentState = queue.poll();

            if (!currentState.admitsSolution()) {
                continue;
            }

            if (!visited.add(currentState)) {
                continue;
            }

            solution.add(currentState);

            if (currentState.isFinal()) {
                return solution;
            }

            State newState;
            for (int i = 0; i < MAX_TRANSITIONS; i++) {
                newState = currentState.transform(Transition.values()[i]);

                if (!visited.contains(newState)) {
                    queue.add(newState);
                }
            }
        }

        return solution;
    }
}
