package player;
import java.util.Random;
import static util.InputUtil.*;

import static sounds.SoundManager.playSound;

public class Player {
    private String name;
    private int level;
    private int xp;
    private int money;
    private int health;
    private int damage;
    private int maxHealth;
    private CharacterType characterType;
    private static final int LEVEL_UP_XP = 100;

    public Player() {
        this.money = 20;
    }

    public Player(String name, CharacterType characterType) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.money = 20;
        setCharacterType(characterType);
    }

    public void chooseCharacter() {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println(name + ", choose a character:");
            System.out.println("""
                1. Samurai 
                2. Knight
                3. Archer
            """);
            int choice = getInt("Enter your choice (any other number defaults to Samurai)");

            switch (choice) {
                case 2 -> setCharacterType(CharacterType.KNIGHT);
                case 3 -> setCharacterType(CharacterType.ARCHER);
                default -> setCharacterType(CharacterType.SAMURAI);
            }

            new Thread(() -> playSound("src/sounds/music/character_sound.wav")).start();
            System.out.println(" You have chosen: " + this.characterType);
            System.out.println(" Health: " + this.health + " Damage: " + this.damage);

            String change = getText("Do you want to change your choice? (Yes/No)").toLowerCase();
            if (!change.equalsIgnoreCase("yes")) {
                validChoice = true;
            }
        }
    }

    private void levelUp() {
        xp -= LEVEL_UP_XP;
        level++;
        maxHealth += 10;
        health = maxHealth;
        damage += 2;

        System.out.println(name + ", Congratulations! You leveled up!");
        System.out.println("Level: " + level);
        System.out.println("Health: " + health + " Damage: " + damage);
    }

    public void gainXp(int amount) {
        xp += amount;
        System.out.println(name + " gained " + amount + " XP!");
        while (xp >= LEVEL_UP_XP) {
            levelUp();
        }
    }

    public void randomXpGained() {
        Random rand = new Random();
        int xpGained = rand.nextInt(10 + (level * 5)) + 10;
        gainXp(xpGained);
    }

    public void restoreHealth() {
        this.health = maxHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
        this.health = this.maxHealth = characterType.getBaseHealth();
        this.damage = characterType.getBaseDamage();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", xp=" + xp +
                ", money=" + money +
                ", health=" + health +
                ", damage=" + damage +
                ", characterType='" + characterType + '\'' +
                '}';
    }
}
