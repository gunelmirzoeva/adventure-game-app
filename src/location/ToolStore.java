package location;
import player.Player;
import tools.Weapons;
import tools.Armors;
import static game.Game.menu;
import static util.InputUtil.getInt;
import static loading.Loading.showLoadingBar;

public class ToolStore extends Location {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }


    @Override
    public boolean onLocation() {
        System.out.println("\n-------------TOOL STORE---------------\n");
        System.out.println("You have entered the Tool Store.");

        while(true) {
            System.out.println("Your current balance: $" + player.getMoney());
            System.out.println("""
                    1. Buy Weapon
                    2. Buy Armor
                    3. Exit Store
                    """);

            int choice = getInt("What do you want to buy? (1. Weapon / 2. Armor)");
            System.out.println();
            switch (choice) {
                case 1 -> buyWeapon();
                case 2 -> buyArmor();
                case 3 -> {
                    System.out.println("Leaving the Tool Store...");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println();
                    menu();
                    return true;
                }
                default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

    }
    private void buyWeapon() {
        Weapons.buyWeapon(player);
    }
    private void buyArmor() {
        //Armors.buyArmor(player);
    }

}
