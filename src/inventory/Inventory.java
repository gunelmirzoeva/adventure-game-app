package inventory;

import player.Player;

import java.util.ArrayList;
import java.util.List;

import static loading.Loading.loading;
import static util.InputUtil.getText;

public class Inventory {

    private List<Weapons> weapons;
    private List<Armors> armors;
    private Weapons equippedWeapon;
    private Armors equippedArmor;

    public Inventory() {
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }

    public void addWeapon(Weapons weapon, Player player) {
        weapons.add(weapon);
        System.out.println(weapon.getType() + " has been added to your inventory.");


    }

    public void removeWeapon(Weapons weapon) {
        if (weapons.remove(weapon)) {
            System.out.println(weapon.getType() + " has been removed from your inventory.");
            if (equippedWeapon == weapon) {
                unequipWeapon();
            }
        } else {
            System.out.println("Weapon not found in inventory.");
        }
    }

    public void equipWeapon(Player player, Weapons weapon) {
        if (!weapons.contains(weapon)) {
            System.out.println("You don't own this weapon.");

        }
        if (equippedWeapon != null) {
            unequipWeapon();
        }
        equippedWeapon = weapon;
        player.setDamage(player.getDamage() + weapon.getDamageBoost());
        System.out.println("Equipped " + weapon.getType() + " (Damage: +" + weapon.getDamageBoost() + ").");
    }

    public void unequipWeapon() {
        if (equippedWeapon != null) {
            System.out.println("Unequipped " + equippedWeapon.getType() + ".");
            equippedWeapon = null;
        }
    }

    public void equipForBattle(Player player) {
        listWeapons();
        if (!weapons.isEmpty()) {
            String weaponChoice = getText("Enter the weapon you want to equip");
            Weapons chosenWeapon = getWeaponByName(weaponChoice);
            while (chosenWeapon == null) {
                System.out.println("Invalid weapon choice.");
                String answer = getText("Do you want to change your choice? (yes/no)");
                if(answer.equalsIgnoreCase("yes")) {
                    weaponChoice = getText("Enter the weapon you want to equip");
                    chosenWeapon = getWeaponByName(weaponChoice);
                } else {
                    System.out.println("Going without weapon!");
                    break;
                }
            }
            if (chosenWeapon != null) {
                equipWeapon(player, chosenWeapon);
            }
        } else {
            System.out.println("You don't have any weapons.");
        }

        listArmors();
        if (!armors.isEmpty()) {
            String armorChoice = getText("Enter the armor you want to equip");
            Armors chosenArmor = getArmorByName(armorChoice);
            while (chosenArmor == null) {
                System.out.println("Invalid armor choice.");
                String answer = getText("Do you want to change your choice? (yes/no)");
                if(answer.equalsIgnoreCase("yes")) {
                    armorChoice = getText("Enter the armor you want to equip");
                    chosenArmor = getArmorByName(armorChoice);
                } else {
                    System.out.println("Going without armor!");
                    break;
                }
            }
            if (chosenArmor != null) {
                equipArmor(player, chosenArmor);
            }
        } else {
            System.out.println("You don't have any armors.");
        }

        System.out.println("You are ready for the fight!");
        loading(1000);
    }


    public void listWeapons() {
        if (weapons.isEmpty()) {
            System.out.println("Your weapon inventory is empty.");
            return;
        }
        System.out.println("Your Weapons:");
        for (Weapons weapon : weapons) {
            System.out.println("- " + weapon.getType() + " (Damage: +" + weapon.getDamageBoost() + ")");
        }
    }


    public Weapons getWeaponByName(String type) {
        for (Weapons weapon : weapons) {
            if (weapon.getType().equalsIgnoreCase(type)) {
                return weapon;
            }
        }
        return null;
    }

    public Armors getArmorByName(String type) {
        for (Armors armor : armors) {
            if (armor.getType().equalsIgnoreCase(type)) {
                return armor;
            }
        }
        return null;
    }

    public void addArmor(Armors armor) {
        armors.add(armor);
        System.out.println(armor.getType() + " has been added to your inventory.");

    }

    public void removeArmor(Armors armor) {
        if (armors.remove(armor)) {
            System.out.println(armor.getType() + " has been removed from your inventory.");
        } else {
            if (equippedArmor == armor) {
                unequipArmor();
            }
            System.out.println("Armor not found in inventory.");
        }
    }

    public void equipArmor(Player player, Armors armor) {
        if (!armors.contains(armor)) {
            System.out.println("You do not own this armor.");
            return;
        }
        if (equippedArmor != null) {
            unequipArmor();
        }
        equippedArmor = armor;
        player.setHealth(player.getHealth() + armor.getArmorBoost());
        System.out.println("Equipped " + armor.getType() + " (Defense: +" + armor.getArmorBoost() + ").");
    }

    public void unequipArmor() {
        if (equippedArmor != null) {
            System.out.println("Unequipped " + equippedArmor.getType() + ".");
            equippedArmor = null;
        }
    }

    public void listArmors() {
        if (armors.isEmpty()) {
            System.out.println("Your armor inventory is empty.");
            return;
        }
        System.out.println("Your Armors:");
        for (Armors armor : armors) {
            System.out.println("- " + armor.getType() + " (Defense: +" + armor.getArmorBoost() + ")");
        }
    }


    public void listInventory() {
        listWeapons();
        listArmors();
    }
    public Weapons getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armors getEquippedArmor() {
        return equippedArmor;
    }

    public List<Weapons> getWeapons() {
        return weapons;
    }

    public List<Armors> getArmors() {
        return armors;
    }

}
