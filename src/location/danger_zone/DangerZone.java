package location.danger_zone;

import battle.Bear;
import battle.Vampire;
import battle.Zombie;
import location.Location;
import player.Player;
import battle.Monster;
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
        if(getLocationName().equals("Cave")) {
            stopMusic();
            new Thread(() -> playMusic("src/sounds/music/cave.wav")).start();
            String answer = getText("Do you want to stop the music?(yes/no)").toLowerCase();
            if(answer.equals("yes")) {
                stopMusic();
            }
        } else if(getLocationName().equals("Forest")) {
            stopMusic();
            new Thread(() -> playMusic("src/sounds/music/forest.wav")).start();
            String answer = getText("Do you want to stop the music?(yes/no)").toLowerCase();
            if(answer.equals("yes")) {
                stopMusic();
            }
        } else if (getLocationName().equals("River")) {}
        spawnMonsters();
        startBattle();

        return true;
    }

    private void spawnMonsters() {
        int monsterNum = random.nextInt(15) + 5;
        if(getLocationName().equals("Cave")) {
            for(int i = 0; i < monsterNum; i++) {
                monsters.add(new Zombie());
            }
            System.out.println(monsterNum + " zombies appeared in the cave!");

        } else if (getLocationName().equals("Forest")) {
            for(int i = 0; i < monsterNum; i++) {
                monsters.add(new Vampire());
            }
            System.out.println(monsterNum + " vampires appeared in the forest!");
        } else if (getLocationName().equals("River")) {
            for(int i = 0; i < monsterNum; i++) {
                monsters.add(new Bear());
            }
            System.out.println(monsterNum + " bears appeared in the river!");
        }
    }

    private void startBattle() {
        int monsterCount = 1;
        Player player = getPlayer();
        while(!monsters.isEmpty() && player.getHealth() > 0) {
            Monster currentMonster = monsters.get(0);
            boolean playerTurn = random.nextBoolean();
            if(playerTurn) {
                System.out.println("\nYou attack first!");
                playerAttack(currentMonster);
                loading(1000);
            } else {
                System.out.println("\n" + currentMonster.getMonsterName() + " attacks first!");
                currentMonster.attack(player);
                loading(1000);
            }

            if(!currentMonster.isAlive()) {

                System.out.println("You defeated " + currentMonster.getMonsterName() + (monsterCount++) + " and earned " + currentMonster.getTreasure() + " $");
                monsters.remove(currentMonster);
            }

            if(monsters.isEmpty()) {
                System.out.println("All monsters have been defeated! You survived!!!");
                break;
            }
            if (player.getHealth() <= 0) {
                new Thread(() -> playSoundEffect("src/sounds/music/man-death-scream.wav")).start();
                loading(1000);
                System.out.println("You died...");
                new Thread(() -> playSoundEffect("src/sounds/music/game_over.wav")).start();
                System.out.println("Game Over.........");
                stopMusic();
                loading(2000);
                new Thread(() -> playMusic("src/sounds/music/start.wav")).start();
                String answer = getText("Do you want to play again?(yes/no)").toLowerCase();
                if(answer.equals("yes")) {
                    onLocation();
                } else {
                    menu();
                }
                break;
            }
        }
    }
    private void playerAttack(Monster monster) {
        int attackDamage = getPlayer().getDamage();
        System.out.println("You deal " + attackDamage + " damage to " + monster.getMonsterName() + "!");
        monster.takeDamage(attackDamage);
    }
}
