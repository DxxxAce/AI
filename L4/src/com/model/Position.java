package com.model;

public class Position {
    public int row;
    public int col;

    public Position() { }

    public Position(int row, int col) {
        this.row = row - 1;
        this.col = col - 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row - 1;
    }

    public void setCol(int col) {
        this.col = col - 1;
    }
}
