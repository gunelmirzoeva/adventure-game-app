package battle;

import player.Player;

import java.util.Random;

public class Monster {
    private String monsterName;
    private Integer monsterDamage;
    private Integer monsterHealth;
    private Integer treasure;

    private static final Random random = new Random();

    public Monster(String monsterName, Integer monsterDamage, Integer monsterHealth) {
        this.monsterName = monsterName;
        this.monsterDamage = monsterDamage;
        this.monsterHealth = monsterHealth;
        this.treasure = random.nextInt(3) + 1;
    }

    public String getMonsterName() {
        return monsterName;
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
        this.monsterHealth -= damage;
        if(this.monsterHealth <= 0) {
            this.monsterHealth = 0;
            System.out.println(monsterName + " has been defeated!\n");
        } else {
            System.out.println(monsterName + " took " + damage + " damage. Remaining health: " + monsterHealth);
        }
    }

    public void attack(Player player) {
        System.out.println(monsterName + " attacks for " + monsterDamage + " damage!\n");
        player.setHealth(player.getHealth() - monsterDamage);

        if(player.getHealth() <= 0) {
            player.setHealth(0);
            System.out.println("You have been defeated by " + monsterName + "...\n");
        } else {
            System.out.println("Your remaining health: " + player.getHealth());
        }
    }

}
