package location;
import player.Player;

public class River extends Location {

    public River(Player player) {
        super(player, "River");
    }

    @Override
    public void enter() {
        System.out.println("You have entered the River.");
        // River specific actions (e.g., fishing)
    }

    @Override
    public boolean onLocation() {
        return false;
    }
}
