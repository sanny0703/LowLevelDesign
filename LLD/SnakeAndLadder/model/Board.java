package SnakeAndLadder.model;

public class Board {
    private int start;
    private int end;
    private int size;

    public Board(int size) {
        this.size = size;
        start = 1;
        end = size;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
