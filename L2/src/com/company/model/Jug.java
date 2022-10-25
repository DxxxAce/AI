package com.company.model;

public class Jug {
    public int capacity;
    public int content;

    public Jug(int capacity) {
        this.capacity = capacity;
        this.content = 0;
    }

    public Jug(Jug jug) {
        this.capacity = jug.capacity;
        this.content = jug.content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jug jug = (Jug) o;

        return this.content == jug.content && this.capacity == jug.capacity;
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + content;
        return result;
    }
}
