package game;

import player.Player;
import util.InputUtil;
import static util.InputUtil.getInt;
import static util.InputUtil.getText;

public class Game {
    public static void start() {
        System.out.println("\n\t\t\tWelcome to the Adventure game! ");
        System.out.println("---------------------------------------------------------------------\n");
        String name = getText("Enter your name");
        Player player = new Player();
        player.setName(name);
        System.out.println("Welcome, " + player.getName() + "! Everything you see here is real.");
        System.out.println("\n----------------------------------------------------------------------\n");
        player.chooseCharacter();
        System.out.println("\n----------------------------------------------------------------------\n");
    }
    public static void menu() {

    }
}