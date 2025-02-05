package tools;

import static game.Game.menu;

import player.Player;


import static util.InputUtil.getInt;

public class Weapons {
    int id;
    String type;
    int cost;
    int damageBoost;

    public Weapons(int id, String type, int cost, int damageBoost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.damageBoost = damageBoost;
    }

    private static Weapons[] setWeapons() {
        Weapons[] weapons = new Weapons[6];
        weapons[0] = new Weapons(1, "Gladius", 30, 2);
        weapons[1] = new Weapons(2, "Bazooka", 45, 10);
        weapons[2] = new Weapons(3, "Pistol", 35, 5);
        weapons[3] = new Weapons(4, "Blaster", 55, 15);
        weapons[4] = new Weapons(5, "Mace", 33, 4);
        weapons[5] = new Weapons(6, "Sniper(AK-47)", 38, 8);
        return weapons;
    }

    private static void showWeapons() {
        Weapons[] weapons = setWeapons();
        for (Weapons weapon : weapons) {
            System.out.println(weapon.id + ". " + weapon.type + " | Cost: $" + weapon.cost + " | Damage: +" + weapon.damageBoost);
        }
    }

    public static void buyWeapon(Player player) {
        Weapons[] weapons = setWeapons();

        System.out.println("Your Balance: $" + player.getMoney());
        System.out.println("Choose a weapon to buy: ");
        showWeapons();

        int choice = getInt("\nEnter weapon ID (or 0 to exit)");
        if (choice == 0) {
            menu();
        } else if (choice <= 6 || choice >= 1) {
            for (Weapons weapon : weapons) {
                if (weapon.id == choice) {
                    if (player.getMoney() >= weapon.cost) {
                        player.setMoney(player.getMoney() - weapon.cost);
                        player.setDamage(player.getDamage() + weapon.damageBoost);

                        System.out.println("You bought " + weapon.type + "!");
                        System.out.println("New Damage: " + player.getDamage());
                        System.out.println("Remaining Balance: $" + player.getMoney());
                    } else {
                        System.out.println("Not enough money to buy " + weapon.type + "!");
                    }
                    return;//it will be added to your inventory
                }
            }

        } else {
            System.out.println("Invalid weapon ID! Try again.");
            buyWeapon(player);
        }

    }
}
