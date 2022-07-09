package SnakeAndLadder.model;

public class Player {
    private String name;
    private int position;
    private boolean isWon;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
        isWon = false;
    }

    public String getName() {
        return name;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
