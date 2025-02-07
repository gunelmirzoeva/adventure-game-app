package battle;
import inventory.Armors;
import inventory.Inventory;
import player.Player;

import java.util.Random;

public class Monster {
    private final MonsterType type;
    private final Integer monsterDamage;
    private Integer monsterHealth;
    private final Integer treasure;

    private static final Random random = new Random();

    public Monster(MonsterType type) {
        this.type = type;
        this.monsterDamage = type.getBaseDamage();
        this.monsterHealth = type.getBaseHealth();
        this.treasure = random.nextInt(3) + 1;
    }

    public String getMonsterName() {
        return type.getName();
    }
    public Integer getMonsterDamage() {
        return monsterDamage;
    }
    public Integer getMonsterHealth() {
        return monsterHealth;
    }
    public boolean isAlive() {
        return monsterHealth > 0;
    }
    public Integer getTreasure() {
        return treasure;
    }

    public void takeDamage(int damage) {
        monsterHealth = Math.max(monsterHealth - damage, 0);
        if(monsterHealth == 0) {
            System.out.println(getMonsterName() + " has been defeated!\n");
        } else {
            System.out.println(getMonsterName() + " took " + damage + " damage. Remaining health: " + monsterHealth);
        }
    }

    public void attack(Player player) {
        System.out.println(getMonsterName() + " attacks for " + monsterDamage + " damage!\n");
        System.out.println(getMonsterName() + "'s current health: " + monsterHealth);
        player.setHealth(player.getHealth() - monsterDamage);

        if (player.getHealth() <= 0) {
            player.setHealth(0);
            System.out.println("You have been defeated by " + getMonsterName() + "...\n");
        } else {
            int boost = 0;
            if(player.getInventory().getEquippedArmor() != null) {
                boost = player.getInventory().getEquippedArmor().getArmorBoost();
            }
            System.out.println("Your remaining health: " + (player.getHealth() + boost));
        }
    }


    public String getSoundEffect() {
        return type.getSoundEffectPath();
    }

}
