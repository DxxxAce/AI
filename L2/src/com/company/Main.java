package com.company;

import com.company.model.Problem;
import com.company.model.State;

public class Main {
    public static void main(String[] args) {
        // State initialState = new State(3, 4, 1);

        Problem problem = new Problem();
        problem.solve();
        problem.printSolution();
    }
}