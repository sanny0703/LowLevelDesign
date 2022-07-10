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
        do {
            Player player = players.poll();
            System.out.println("@@@@@@@@@@@ it's player **" + player.getName() + "** turn @@@@@@@@@@");
            int diceValue = dice.roll();
            System.out.println("it's  " + diceValue);
            int position = getState(player.getPosition() + diceValue);
            if (position > board.getEnd()) {
                System.out.println("player " + player.getName() + "  will remain at the same position");
                players.offer(player);
            } else if (position == board.getEnd()) {
                player.setWon(true);
                player.setPosition(position);
                System.out.println("===========   player  " + player.getName() + "  has won the game ==========");
            } else {
                player.setPosition(position);
                players.offer(player);
                System.out.println("player  " + player.getName() + "  has reached " + player.getPosition());
            }
        } while (players.size() >= 2);
    }

    private int getState(int position) {
        for (Snake snake : snakes) {
            if (position == snake.getHead()) {
                System.out.println("Got bitten by a snake and reached down to  " + snake.getTail());
                return snake.getTail();
            }
        }
        for (Ladder ladder : ladders) {
            if (position == ladder.getBottom()) {
                System.out.println("Climbed a  ladder and reached up to  " + ladder.getTop());
                return ladder.getTop();
            }
        }
        return position;
    }

}
