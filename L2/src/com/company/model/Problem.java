package com.company.model;

import com.company.strategy.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Problem {
    private final int INVALID_INPUT = -1;

    private final State initialState;
    private Algorithm algorithm;
    private List<State> solution;

    public Problem() {
        int n, m, k;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("Please enter the capacity of the first jug:");
                n = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                n = INVALID_INPUT;
            }

            scanner.nextLine();
        }
        while (n <= 0);

        do {
            try {
                System.out.println("Please enter the capacity of the second jug:");
                m = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                m = INVALID_INPUT;
            }

            scanner.nextLine();
        }
        while (m <= 0);

        do {
            try {
                System.out.println("Please enter the quantity to be measured:");
                k = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                k = INVALID_INPUT;
            }

            scanner.nextLine();
        }
        while (k <= 0);

        this.initialState = new State(n, m, k);
    }

    public void chooseStrategy() {
        int algorithm;

        do {
            System.out.println("Please select a digit for the strategy you would like to use:");
            System.out.println("1. Backtracking");
            System.out.println("2. Breadth-First Search");
            System.out.println("3. Hill Climbing");
            System.out.println("4. A*");

            Scanner scanner = new Scanner(System.in);
            try {
                algorithm = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                algorithm = INVALID_INPUT;
            }

            scanner.nextLine();
        }
        while (algorithm < 1 || algorithm > 4);

        this.algorithm = Algorithm.values()[algorithm - 1];
    }

    private void tryBKT() {
        Strategy strategy = new BKT(initialState);
        solution = strategy.run();
    }

    private void tryBFS() {
        Strategy strategy = new BFS(initialState);
        solution = strategy.run();
    }

    private void tryHillClimbing() {
        Strategy strategy = new HillClimbing(initialState);
        solution = strategy.run();
    }

    private void tryAStar() {
        Strategy strategy = new AStar(initialState);
        solution = strategy.run();
    }

    public void solve() {
        if (!initialState.admitsSolution()) {
            solution = new ArrayList<>();
            return;
        }

        chooseStrategy();

        switch (algorithm) {
            case BKT -> tryBKT();
            case BFS -> tryBFS();
            case HILL_CLIMBING -> tryHillClimbing();
            case A_STAR -> tryAStar();
            default -> solution = new ArrayList<>();
        }
    }

    public void printSolution() {
        if (solution.isEmpty()) {
            System.out.println("There is no solution for this problem.");
            return;
        }

        System.out.println("Solution:");

        for (var state : solution) {
            System.out.print(state);
        }
    }
}
