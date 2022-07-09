package SnakeAndLadder.model;

import java.util.*;

import org.apache.commons.lang3.RandomUtils;

public class Game {
    private final int snakesCount;
    private final int laddersCount;
    private final Queue<Player> players;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final Board board;
    private final Dice dice;
    private final Set<String> startEndPositionSet;

    public Game(int size, int snakesCount, int laddersCount) {
        this.snakesCount = snakesCount;
        this.laddersCount = laddersCount;
        board = new Board(size);
        dice = new Dice();
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        players = new LinkedList<>();
        startEndPositionSet = new HashSet<>();
        init();
    }

    private void init() {
        for (int i = 0; i < snakesCount; i++) {
            while (true) {
                int start = RandomUtils.nextInt(board.getStart(), board.getEnd());
                int end = RandomUtils.nextInt(board.getStart(), board.getEnd());
                if (start <= end)
                    continue;
                String startEndPair = String.valueOf(start) + end;
                if (!startEndPositionSet.contains(startEndPair)) {
                    Snake snake = new Snake(start, end);
                    snakes.add(snake);
                    startEndPositionSet.add(startEndPair);
                    break;
                }
            }
        }
        for (int i = 0; i < laddersCount; i++) {
            while (true) {
                int start = RandomUtils.nextInt(board.getStart(), board.getEnd());
                int end = RandomUtils.nextInt(board.getStart(), board.getEnd());
                if (start >= end)
                    continue;
                String startEndPair = String.valueOf(start) + end;
                if (!startEndPositionSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(start, end);
                    ladders.add(ladder);
                    startEndPositionSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.offer(player);
    }

    public void play() {
        while (true){
            Player player = players.poll();
            System.out.println("it's player **" + player.getName() + "** turn'");
            int diceValue = dice.roll();
            State newState = getState(player.getPosition() + diceValue);
            if (newState.getPosition() > board.getEnd()) {
                System.out.println("Player  " + player.getName() + " remained in the " + player.getPosition());
                players.offer(player);
            } else {
                if (newState.getPosition() == board.getEnd()) {
                    System.out.println("===== Player  " + player.getName() + " has won the game========");
                    player.setWon(true);
                    player.setPosition(newState.getPosition());
                } else {
                    if (newState.isBittenBySnake()) {
                        System.out.println("--------Player " + player.getName() + " has bitten by a snake and went down to " + newState.getPosition() + "---------");
                    } else if (newState.isClimbedLadder()) {
                        System.out.println("+++++++++Player " + player.getName() + "  has climbed a ladder and reached up to " + newState.getPosition() + "+++++++++");
                    } else {
                        System.out.println("Player " + player.getName() + "  has reached " + newState.getPosition());
                    }
                    player.setPosition(newState.getPosition());
                    players.offer(player);
                }
            }
            if(players.size()<2)
                break;
        }
    }

    private State getState(int position) {
        for (Snake snake : snakes) {
            if (position == snake.getHead()) {
                return new State(snake.getTail(), true, false);
            }
        }
        for (Ladder ladder : ladders) {
            if (position == ladder.getBottom()) {
                return new State(ladder.getTop(), false, true);
            }
        }
        return new State(position, false, false);
    }

}
