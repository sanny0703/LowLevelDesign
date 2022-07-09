package SnakeAndLadder.model;

public class State {
    private int position;
    private boolean isBittenBySnake;
    private boolean isClimbedLadder;

    public State(int position, boolean isBittenBySnake, boolean isClimbedLadder) {
        this.position = position;
        this.isBittenBySnake = isBittenBySnake;
        this.isClimbedLadder = isClimbedLadder;
    }

    public int getPosition() {
        return position;
    }

    public boolean isBittenBySnake() {
        return isBittenBySnake;
    }

    public boolean isClimbedLadder() {
        return isClimbedLadder;
    }
}
