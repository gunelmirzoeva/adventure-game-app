package player;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

enum CharacterType {
    SAMURAI, KNIGHT, ARCHER
}

public class Player {
    private String name;
    private Integer level;
    private Integer xp;
    private Integer money;
    private Integer health;
    private Integer damage;
    private CharacterType characterType;
    private static final Integer LEVEL_UP_XP = 100;
    public Player() {
    }
    public Player(String name, CharacterType characterType) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.characterType = characterType;
        this.money = 20;
    }
    public void chooseCharacter() {
        Scanner sc = new Scanner(System.in);
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println(name + ", choose a character: (If you choose any other number than 1,2,3, it will default to Samurai)");
            System.out.println("""
                1. Samurai 
                2. Knight
                3. Archer
            """);
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        this.characterType = CharacterType.SAMURAI;
                        this.health = 100;
                        this.damage = 15;
                    }
                    case 2 -> {
                        this.characterType = CharacterType.KNIGHT;
                        this.health = 120;
                        this.damage = 12;
                    }
                    case 3 -> {
                        this.characterType = CharacterType.ARCHER;
                        this.health = 80;
                        this.damage = 18;
                    }
                    default -> {
                        this.characterType = CharacterType.SAMURAI;
                        this.health = 100;
                        this.damage = 15;
                    }
                }

                System.out.println("You have chosen: " + this.characterType);
                System.out.println(characterType + "'s health: " + getHealth() + " and damage: " + getDamage());

                System.out.println("Do you want to change your choice (Yes/No)?");
                String change = sc.nextLine().toLowerCase();
                if (!change.equalsIgnoreCase("yes")) {
                    validChoice = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
    }


    private void levelUp() {
        this.xp -= LEVEL_UP_XP;
        this.level++;
        this.health += 10;
        this.damage += 2;
        System.out.println(name + ", Congratulations! You have level up! " + "\nYour level is " + this.level);
    }

    public void gainXp(int amount) {
        this.xp += amount;
        System.out.println(name + ", You gained " + amount + " XP");
        while (this.xp >= LEVEL_UP_XP) {
            levelUp();
        }
    }

    public void randomXpGained() {
        Random rand = new Random();
        int xpGained = rand.nextInt(10 + (level * 5)) + 10;
        gainXp(xpGained);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
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
