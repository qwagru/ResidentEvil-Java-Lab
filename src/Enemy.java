import java.util.Random;

abstract class Enemy {
    protected String name;
    protected int hp;
    protected Random random = new Random();

    public Enemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    // abstract method for hitting
    public abstract int attack();

    // some musthave methods
    public void takeDamage(int amount) { this.hp -= amount; }
    public boolean isAlive() { return hp > 0; }
    public String getName() { return name; }
    public int getHp() { return hp; }
}
    // types of enemies
class Zombie extends Enemy {
    public Zombie() { super("Зомбі", 40); }
    public int attack() { return random.nextInt(10) + 5; }
}

class ZombieDog extends Enemy {
    public ZombieDog() { super("Зомбі-пес", 25); }
    public int attack() { return random.nextInt(12) + 10; }
}

class Licker extends Enemy {
    public Licker() { super("Лизун", 80); }
    public int attack() { return random.nextInt(20) + 15; }
}

class Tyrant extends Enemy {
    public Tyrant() { super("ТІРАН", 150); }
    public int attack() { return random.nextInt(30) + 20; }
}