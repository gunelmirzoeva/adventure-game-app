package location.danger_zone;

import battle.Monster;
import battle.MonsterType;
import location.Location;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static loading.Loading.loading;
import static sounds.SoundManager.*;
import static util.InputUtil.getText;
import static game.Game.menu;

public class DangerZone extends Location {

    private static final Random random = new Random();
    private List<Monster> monsters;

    public DangerZone(Player player, String locationName) {
        super(player, locationName);
        this.monsters = new ArrayList<>();
    }

    @Override
    public boolean onLocation() {

        getPlayer().getInventory().equipForBattle(getPlayer());

        loading(1000);

        System.out.println("\nYou have entered " + getLocationName() + ". Prepare for battle!\n");
        stopMusic();

        switch (getLocationName()) {
            case "Cave" -> new Thread(() -> playMusic("src/sounds/music/cave.wav")).start();
            case "Forest" -> new Thread(() -> playMusic("src/sounds/music/forest.wav")).start();
            case "River" -> new Thread(() -> playMusic("src/sounds/music/river.wav")).start();
        }

        if (getText("Do you want to stop the music? (yes/no)").equalsIgnoreCase("yes")) {
            stopMusic();
        }

        monsters.clear();
        spawnMonsters();
        startBattle();

        return true;
    }

    private void spawnMonsters() {
        int monsterNum = random.nextInt(11) + 5;
        MonsterType monsterType = switch (getLocationName()) {
            case "Cave" -> MonsterType.ZOMBIE;
            case "Forest" -> MonsterType.VAMPIRE;
            case "River" -> MonsterType.BEAR;
            default -> throw new IllegalStateException("Unexpected value: " + getLocationName());
        };
        for (int i = 0; i < monsterNum; i++) {
            monsters.add(new Monster(monsterType));
        }
        System.out.println(monsterNum + " " + monsterType.getName().toLowerCase() + "(s) appeared in the " + getLocationName() + "!");
    }

    private void startBattle() {
        int monsterCount = 1;
        Player player = getPlayer();

        while(!monsters.isEmpty() && player.getHealth() > 0) {
            Monster currentMonster = monsters.get(0);
            boolean playerTurn = random.nextBoolean();

            if(playerTurn) {
                System.out.println("\nYou attack!");
                playerAttack(currentMonster);
            } else {
                System.out.println("\n" + currentMonster.getMonsterName() + " attacks!");
                currentMonster.attack(player);
            }

            loading(1000);

            if(!currentMonster.isAlive()) {
                player.setMoney(player.getMoney() + currentMonster.getTreasure());
                playSoundEffect(currentMonster.getSoundEffect());

                System.out.println("You defeated " + currentMonster.getMonsterName() + " " + monsterCount++ + " and earned " + currentMonster.getTreasure() + "$");
                monsters.remove(currentMonster);
                loading(1000);
            }

            if(monsters.isEmpty()) {
                System.out.println("All monsters have been defeated! You survived!!!");
                playSoundEffect("src/sounds/music/victory.wav");
                System.out.println("VICTORY!!!");
                player.restoreHealth();
                loading(1000);
                stopMusic();
                loading(2000);

                if (getText("Do you want to play again?(yes/no)").equalsIgnoreCase("yes")) {
                    onLocation();
                } else {
                    new Thread(() -> playMusic("src/sounds/music/start.wav")).start();
                    if (getText("Do you want to stop the music? (yes/no)").equalsIgnoreCase("yes")) {
                        stopMusic();
                    }
                }
                return;
            }
            if (player.getHealth() <= 0) {
                playSoundEffect("src/sounds/music/man-death-scream.wav");
                loading(1000);
                System.out.println("You died...");
                playSoundEffect("src/sounds/music/game_over.wav");
                System.out.println("Game Over.........");
                stopMusic();
                loading(2000);

                if (getText("Do you want to play again?(yes/no)").equalsIgnoreCase("yes")) {
                    player.restoreHealth();
                    onLocation();
                } else {
                    new Thread(() -> playMusic("src/sounds/music/start.wav")).start();
                    if (getText("Do you want to stop the music? (yes/no)").equalsIgnoreCase("yes")) {
                        stopMusic();
                    }

                    menu();
                }
                return;
            }
        }
    }
    private void playerAttack(Monster monster) {
        int attackDamage = getPlayer().getDamage();
        System.out.println("You deal " + attackDamage + " damage to " + monster.getMonsterName() + "!");
        monster.takeDamage(attackDamage);
    }

}
