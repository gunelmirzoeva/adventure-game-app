package inventory;

import location.ToolStore;
import player.Player;


import static sounds.SoundManager.playSoundEffect;
import static util.InputUtil.*;

public class Weapons {
    private Integer id;
    private String type;
    private Integer cost;
    private Integer damageBoost;

    public Weapons(Integer id, String type, Integer cost, Integer damageBoost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.damageBoost = damageBoost;
    }
    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getDamageBoost() {
        return damageBoost;
    }

    private static Weapons[] setWeapons() {
        Weapons[] weapons = new Weapons[6];
        weapons[0] = new Weapons(1, "AK-47", 35, 5);
        weapons[1] = new Weapons(2, "Bazooka", 45, 10);
        weapons[2] = new Weapons(3, "Pistol", 30, 2);
        weapons[3] = new Weapons(4, "Blaster", 55, 15);
        weapons[4] = new Weapons(5, "Mace", 33, 4);
        weapons[5] = new Weapons(6, "Sniper", 38, 8);
        return weapons;
    }

    private static void showWeapons() {
        System.out.println("-----------WEAPONS----------");
        Weapons[] weapons = setWeapons();
        for (Weapons weapon : weapons) {
            System.out.println(weapon.id + ". " + weapon.type + " | Cost: $" + weapon.cost + " | Damage: +" + weapon.damageBoost);
        }
    }

    public static void buyWeapon(Player player, ToolStore store) {
        Weapons[] weapons = setWeapons();

        System.out.println("Your Balance: $" + player.getMoney());
        System.out.println("Choose a weapon to buy: ");
        showWeapons();

        int choice = getInt("\nEnter weapon ID (or 0 to exit)");
        if (choice == 0) {
            store.leaveLocation();
            return;
        }

        if (choice >= 1 && choice <= weapons.length) {
            Weapons selectedWeapon = weapons[choice - 1];

            if (player.getInventory().getWeaponByName(selectedWeapon.getType()) != null) {
                System.out.println("\nYour Current Inventory: ");
                player.getInventory().listInventory();
                System.out.println("You already own " + selectedWeapon.getType() + "! You cannot buy it again.\n");
                String answer = getText("Do you want to buy another weapon? (yes/no)").toLowerCase();
                if (!answer.equals("yes")) {
                    store.leaveLocation();
                } else {
                    buyWeapon(player, store);
                }
                return;
            }

            if (player.getMoney() >= selectedWeapon.getCost()) {
                player.setMoney(player.getMoney() - selectedWeapon.getCost());
                new Thread(() -> playSoundEffect("src/sounds/music/item_added.wav")).start();
                System.out.println("You bought " + selectedWeapon.getType() + "!");
                player.getInventory().addWeapon(selectedWeapon, player);
                System.out.println("New Balance: $" + player.getMoney());
                System.out.println();
                System.out.println("\nYour Updated Inventory:");
                player.getInventory().listInventory();
                System.out.println();
            } else {
                System.out.println("Not enough money to buy " + selectedWeapon.getType() + "!");
                String answer = getText("Do you want to buy another item? (yes/no)").toLowerCase();
                if(!answer.equals("yes")) {
                    store.leaveLocation();
                } else {

                    buyWeapon(player, store);
                }
            }
        } else {
            System.out.println("Invalid weapon ID! Try again.");
            buyWeapon(player, store);
        }
    }


}
