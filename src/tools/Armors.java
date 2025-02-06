package tools;

public class Armors {
    private Integer id;
    private String type;
    private Integer cost;
    private Integer armorBoost;

    public Armors(Integer id, String type, Integer cost, Integer armorBoost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.armorBoost = armorBoost;
    }

   /* private static Armors[] setArmors() {
        Armors[] armors = new Armors[6];
        armors[0] = new Armors(1, "Nano Suit", 30, 2);
        weapons[1] = new Weapons(2, "Bazooka", 45, 10);
        weapons[2] = new Weapons(3, "Pistol", 35, 5);
        weapons[3] = new Weapons(4, "Blaster", 55, 15);
        weapons[4] = new Weapons(5, "Mace", 33, 4);
        weapons[5] = new Weapons(6, "Sniper(AK-47)", 38, 8);
        return weapons;
    }*/

}
