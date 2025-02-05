package location;
import player.Player;
import tools.Weapons;

import static util.InputUtil.getInt;

public class ToolStore extends Location {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }


    @Override
    public boolean onLocation() {
        System.out.println("\n-------------TOOL STORE---------------\n");
        System.out.println("You have entered the Tool Store.");
        System.out.println("Your current balance: $" + player.getMoney());
        int choice = getInt("What do you want to buy? (1. Weapon / 2. Armor)");
        System.out.println();
        if(choice == 1) {
            Weapons.buyWeapon(player);
            //if you have enough money to buy weapons it will be added to characters strength otherwise it will show the
            //message that you don't have enough money to buy this weapon
        } else if(choice == 2) {
            //armor
        } else {
            System.out.println("Invalid choice");
        }
        return true;
    }

}
