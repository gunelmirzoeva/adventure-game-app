package location;
import player.Player;

public class SafeHouse extends Location {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public void enter() {
        System.out.println("You have entered the Safe House.");
        // Safe house specific actions (e.g., healing)
    }

    @Override
    public boolean onLocation() {
        return false;
    }
}
