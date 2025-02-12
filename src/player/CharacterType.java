package player;

public enum CharacterType {
    SAMURAI(100, 15),
    KNIGHT(120, 12),
    ARCHER(80, 18);

    private final int baseHealth;
    private final int baseDamage;

    CharacterType(int baseHealth, int baseDamage) {
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
