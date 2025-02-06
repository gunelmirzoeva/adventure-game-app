package inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Weapons> weapons;
    private List<Armors> armors;
    public Inventory() {
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();
    }

    public void addWeapon(Weapons weapon) {
        weapons.add(weapon);
        System.out.println(weapon.getType() + " has been added to your inventory.");
    }

    public void removeWeapon(Weapons weapon) {
        if (weapons.remove(weapon)) {
            System.out.println(weapon.getType() + " has been removed from your inventory.");
        } else {
            System.out.println("Weapon not found in inventory.");
        }
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

    public List<Weapons> getWeapons() {
        return weapons;
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
            System.out.println("Weapon not found in inventory.");
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
}
