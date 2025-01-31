package location;
import player.Player;

public class Cave extends Location {

    public Cave(Player player) {
        super(player, "Cave");
    }

    @Override
    public void enter() {
        System.out.println("You have entered the Cave.");
        // Cave specific actions (e.g., finding enemies)
    }

    @Override
    public boolean onLocation() {
        return false;
    }
}
