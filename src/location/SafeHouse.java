package location;
import player.Player;

import static game.Game.menu;
import static sounds.SoundManager.playSound;
import static util.InputUtil.getText;

public class SafeHouse extends Location {

    private static final int HEAL_COST = 5;

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\n-------------SAFE HOUSE---------------\n");
        System.out.println("You have entered the Safe House.");
        System.out.println("Health restoration costs $" + HEAL_COST);
        System.out.println("Your current balance: $" + player.getMoney());
        if(player.getHealth() == player.getCharacterType().getBaseHealth()) {
            System.out.println("Your health is already full!");
            System.out.println("\n--------------------------------------\n\n\n");
            menu();
        }
        else if (player.getMoney() >= HEAL_COST) {
            player.setMoney(player.getMoney() - HEAL_COST);
            new Thread(() -> playSound("src/sounds/music/health.wav")).start();
            player.restoreHealth();

            System.out.println("Your health has been restored to " + player.getHealth() + "!");
            System.out.println("Remaining balance: $" + player.getMoney());
        } else {
            System.out.println("You don't have enough money to restore health.");
        }
        return true;
    }
}
