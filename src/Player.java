class Player {
    private int hp = 100;
    private int herbs = 1;
    private int grenades = 0;
    private final Weapon pistol = new Pistol();
    private final Weapon shotgun = new Shotgun();
    private boolean hasShotgun = false;

    // some basic methods
    public boolean isAlive() { return hp > 0; }
    public int getHp() { return hp; }
    public void takeDamage(int damage) { this.hp -= damage; }

    // healing logic
    public void heal() {
        if (herbs > 0) {
            hp = Math.min(100, hp + 40);
            herbs--;
            System.out.println("Використано траву. HP: " + hp);
        } else {
            System.out.println("Трав немає!");
        }
    }

    // some methods for "inventory"
    public Weapon getPistol() { return pistol; }
    public Weapon getShotgun() { return shotgun; }
    public boolean hasShotgun() { return hasShotgun; }
    public void setHasShotgun(boolean value) { this.hasShotgun = value; }
    public void addHerbs(int count) { herbs += count; }
    public void addGrenades(int count) { grenades += count; }
    public int getHerbs() { return herbs; }
    public int getGrenades() { return grenades; }
    public void useGrenade() { grenades--; }
}