abstract class Weapon {
    private final String name;
    private final int baseDamage;
    private final int headshotChance;
    private final int bodyHitChance;
    private int ammo;

    public Weapon(String name, int baseDamage, int headshotChance, int bodyHitChance, int ammo) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.headshotChance = headshotChance;
        this.bodyHitChance = bodyHitChance;
        this.ammo = ammo;
    }

    // getters, setters and some shooting logic
    public String getName() { return name; }
    public int getAmmo() { return ammo; }
    public void setAmmo(int ammo) {
        this.ammo = Math.clamp(ammo, 0, 7);
    }
    public void useAmmo() { if (ammo > 0) ammo--; }

    public int getHitChance(int targetZone) {
        return (targetZone == 1) ? headshotChance : bodyHitChance;
    }

    public int calculateDamage(int targetZone) {
        return (targetZone == 1) ? baseDamage * 3 : baseDamage;
    }
}

// some types of weapons
class Pistol extends Weapon {
    public Pistol() { super("Пістолет", 15, 30, 80, 5); }
}

class Shotgun extends Weapon {
    public Shotgun() { super("Дробовик", 45, 40, 90, 0); }
}