package location;
import player.Player;

public class ToolStore extends Location {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public void enter() {
        System.out.println("You have entered the Tool Store.");
        // Tool store specific actions (e.g., buying items)
    }

    @Override
    public boolean onLocation() {
        return false;
    }
}
