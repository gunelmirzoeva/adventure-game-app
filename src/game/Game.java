package game;

import location.*;
import player.Player;

import static util.InputUtil.*;

public class Game {
    static Player player = new Player();

    private static final int SAFE_HOUSE = 1;
    private static final int TOOL_STORE = 2;
    private static final int CAVE = 3;
    private static final int FOREST = 4;
    private static final int RIVER = 5;
    private static final int EXIT = 6;

    public static void start() {
        System.out.println("\n\t\t\tWelcome to the Adventure game! ");
        System.out.println("---------------------------------------------------------------------\n");
        String name = getText("Enter your name");

        player.setName(name);
        System.out.println("Welcome, " + player.getName() + "! Everything you see here is real.");
        System.out.println("\n----------------------------------------------------------------------\n");
        player.chooseCharacter();
        System.out.println("\n----------------------------------------------------------------------\n");

        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("""
                    Choose a location: 
                    1. Safe House
                    2. ToolStore
                    3. Cave
                    4. Forest
                    5. River
                    6. Exit""");

            int choice = getInt("Enter your choice");
            Location location = getLocation(choice);
            System.out.println();
            if (location != null) {
                location.onLocation();
                location.setLocationName(location.getLocationName());
                break;
            } else {
                if (choice == EXIT) {
                    String answer = getText("Are you sure you want to exit? (Yes/No)").toLowerCase();
                    if(answer.equalsIgnoreCase("Yes")) {
                        System.out.println("Goodbye!!!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            }
        }
        System.out.println("\n-------------------------------------------\n");
    }

    private static Location getLocation(int choice) {
        return switch (choice) {
            case SAFE_HOUSE -> new SafeHouse(player);
            case TOOL_STORE -> new ToolStore(player);
            case CAVE -> new Cave(player);
            case FOREST -> new Forest(player);
            case RIVER -> new River(player);
            default -> null;
        };
    }
}
