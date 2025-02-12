package battle;

public enum MonsterType {
    ZOMBIE("Zombie", 5, 10, "src/sounds/music/zombie.wav"),
    VAMPIRE("Vampire", 7, 13, "src/sounds/music/vampire.wav"),
    BEAR("Bear", 10, 15, "src/sounds/music/bear_roar.wav");

    private final String name;
    private final int baseDamage;
    private final int baseHealth;
    private final String soundEffectPath;

    MonsterType(String name, int baseDamage, int baseHealth, String soundEffectPath) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.baseHealth = baseHealth;
        this.soundEffectPath = soundEffectPath;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public String getSoundEffectPath() {
        return soundEffectPath;
    }
}
