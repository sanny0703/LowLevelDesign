package SnakeAndLadder.App;

import SnakeAndLadder.model.Game;
import SnakeAndLadder.model.Player;

import java.util.Scanner;

public class SnakeAndLadderApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the board size: ");
        int size = scanner.nextInt();
        System.out.println("Enter the players count: ");
        int playersCount = scanner.nextInt();
        System.out.println("Enter the ladders count: ");
        int laddersCount = scanner.nextInt();
        System.out.println("Enter the snakes count: ");
        int snakesCount = scanner.nextInt();
        Game game = new Game(size, snakesCount, laddersCount);
        for (int i = 0; i < playersCount; i++) {
            System.out.println("Enter the player " + (i + 1) + " name: ");
            String name = scanner.next();
            game.addPlayer(new Player(name, 1));
        }
        game.play();
    }
}
