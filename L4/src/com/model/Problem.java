package com.model;

public class Problem {
    private final int size;
    private final int[][] board;

    public Problem(int size, Position[] blockedPositions) {
        this.size = size;
        this.board = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }

        for (Position pos : blockedPositions) {
            board[pos.row][pos.col] = -1;
        }
    }

    private boolean isBlocked(int row, int col) {
        return board[row][col] == -1;
    }

    private boolean isSafe(int row, int col) {
        int i, j;

        if (isBlocked(row, col)) {
            return false;
        }

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < size; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean solveColumn(int col)
    {
        if (col >= size)
            return true;

        for (int i = 0; i < size; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;

                if (solveColumn(col + 1))
                    return true;

                board[i][col] = 0;
            }
        }

        return false;
    }

    public boolean solve()
    {

        if (!solveColumn(0)) {
            System.out.print("Solution does not exist");
            return false;
        }

        return true;
    }

    public void printSolution()
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
