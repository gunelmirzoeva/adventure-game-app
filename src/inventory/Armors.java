package inventory;

import location.ToolStore;
import player.Player;
import static sounds.SoundManager.playSoundEffect;
import static util.InputUtil.*;

public class Armors {
    private Integer id;
    private String type;
    private Integer cost;
    private Integer armorBoost;

    public Armors(Integer id, String type, Integer cost, Integer armorBoost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.armorBoost = armorBoost;
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

    public Integer getArmorBoost() {
        return armorBoost;
    }

    private static Armors[] setArmors() {
        Armors[] armors = new Armors[6];
        armors[0] = new Armors(1, "Nano Suit", 35, 35);
        armors[1] = new Armors(2, "Ghost Armor", 15, 10);
        armors[2] = new Armors(3, "Power Armor", 20, 14);
        armors[3] = new Armors(4, "Blood Veil", 30, 30);
        armors[4] = new Armors(5, "Daedric Armor", 26, 23);
        armors[5] = new Armors(6, "Havel's Armor", 23, 18);
        return armors;
    }

    private static void showArmors() {
        System.out.println("----------ARMORS-----------");
        Armors[] armors = setArmors();
        for (Armors armor : armors) {
            System.out.println(armor.id + ". " + armor.type + " | Cost: $" + armor.cost + " | Defense: +" + armor.armorBoost);
        }
    }

    public static void buyArmor(Player player, ToolStore store) {
        Armors[] armors = setArmors();

        System.out.println("Your Balance: $" + player.getMoney());
        System.out.println("Choose an armor to buy: ");
        showArmors();

        int choice = getInt("\nEnter armor ID (or 0 to exit)");
        if (choice == 0) {
            store.leaveLocation();
            return;
        }

        if (choice >= 1 && choice <= armors.length) {
            Armors selectedArmor = armors[choice - 1];

            if (player.getInventory().getArmorByName(selectedArmor.getType()) != null) {
                System.out.println("\nYour Current Inventory:");
                player.getInventory().listInventory();
                System.out.println("You already own " + selectedArmor.getType() + "! You cannot buy it again.\n");
                String answer = getText("Do you want to buy another armor? (yes/no)").toLowerCase();
                if (!answer.equals("yes")) {
                    store.leaveLocation();
                } else {
                    buyArmor(player, store);
                }
                return;
            }

            if (player.getMoney() >= selectedArmor.getCost()) {
                player.setMoney(player.getMoney() - selectedArmor.getCost());
                new Thread(() -> playSoundEffect("src/sounds/music/item_added.wav")).start();
                System.out.println("You bought " + selectedArmor.getType() + "!");
                player.getInventory().addArmor(selectedArmor);
                System.out.println("New Balance: $" + player.getMoney());
                System.out.println();

                System.out.println("\nYour Updated Inventory:");
                player.getInventory().listInventory();
                System.out.println();
            } else {
                System.out.println("Not enough money to buy " + selectedArmor.getType() + "!");
                String answer = getText("Do you want to buy another item? (yes/no)").toLowerCase();
                if(!answer.equals("yes")) {
                    store.leaveLocation();
                } else {

                    buyArmor(player, store);
                }
            }
        } else {
            System.out.println("Invalid armor ID! Try again.");
            buyArmor(player, store);
        }
    }


}
