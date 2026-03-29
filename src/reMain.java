import java.util.Scanner;
import java.util.Random;

public class reMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final Player player = new Player();

    // main method with a game logic
    public static void main(String[] args) {
        int enemiesDefeated = 0;
        System.out.println("=== RESIDENT EVIL: CONSOLE VERSION ===");

        // performing while player is alive or player killed less than 3 enemies
        while (enemiesDefeated < 3 && player.isAlive()) {
            Enemy enemy = spawnEnemy();
            System.out.println("\n!!! З'ЯВИВСЯ: " + enemy.getName() + " (HP: " + enemy.getHp() + ") !!!");

            while (enemy.isAlive() && player.isAlive()) {
                printStatus();
                System.out.println("1-Стріляти, 2-Лікуватися, 3-Граната");
                int choice = scanner.nextInt();

                // choosing action to perform
                if (choice == 1) {
                    performAttack(enemy);
                } else if (choice == 2) {
                    player.heal();
                    continue;
                } else if (choice == 3) {
                    if (player.getGrenades() > 0) {
                        enemy.takeDamage(100);
                        player.useGrenade();
                        System.out.println("БУМ! Граната нанесла 100 шкоди.");
                    } else {
                        System.out.println("Гранати закінчилися!");
                        continue;
                    }
                }

                // enemy action logic
                if (enemy.isAlive()) {
                    int damage = enemy.attack();
                    player.takeDamage(damage);
                    System.out.println(enemy.getName() + " вдарив вас на " + damage);
                }
            }

            if (player.isAlive()) {
                enemiesDefeated++;
                System.out.println("Ворог знищений!");
                generateDrop();
            }
        }

        System.out.println(player.isAlive() ? "ВИ ВИЖИЛИ!" : "ВИ ЗАГИНУЛИ.");
    }

    // method with spawning enemy logic
    private static Enemy spawnEnemy() {
        int type = random.nextInt(4);
        return switch (type) {
            case 0 -> new Zombie();
            case 1 -> new ZombieDog();
            case 2 -> new Licker();
            default -> new Tyrant();
        };
    }

    // method with attacking logic
    private static void performAttack(Enemy enemy) {
        System.out.println("Зброя: 1-Пістолет" + (player.hasShotgun() ? ", 2-Дробовик" : ""));
        int wChoice = scanner.nextInt();
        Weapon weapon = (wChoice == 2 && player.hasShotgun()) ? player.getShotgun() : player.getPistol();

        if (weapon.getAmmo() <= 0) {
            System.out.println("Немає набоїв!");
            return;
        }

        System.out.println("Ціль: 1-Голова, 2-Тулуб");
        int target = scanner.nextInt();
        weapon.useAmmo();

        if (random.nextInt(100) < weapon.getHitChance(target)) {
            int dmg = weapon.calculateDamage(target);
            enemy.takeDamage(dmg);
            System.out.println("ВЛУЧАННЯ! Нанесено " + dmg + " шкоди.");
        } else {
            System.out.println("ПРОМАХ!");
        }
    }

    // method with drop generating logic
    private static void generateDrop() {
        int drop = random.nextInt(100);
        if (drop < 40) {
            player.getPistol().setAmmo(player.getPistol().getAmmo() + 7);
            System.out.println("[Знайдено набої до пістолета]");
        } else if (drop < 60) {
            if(!player.hasShotgun()){
                player.setHasShotgun(true);
                System.out.println("[Знайдено ДРОБОВИК]");
            }
            player.getShotgun().setAmmo(player.getShotgun().getAmmo() + 3);
            System.out.println("[Знайдено набої для дробовика]");

        } else if (drop < 80) {
            player.addHerbs(1);
            System.out.println("[Знайдено траву]");
        } else {
            player.addGrenades(1);
            System.out.println("[Знайдено гранату]");
        }
    }

    // method with printing status logic
    private static void printStatus() {
        System.out.println("\nСтатус: HP=" + player.getHp() +
                " | Пістолет=" + player.getPistol().getAmmo() +
                " | Дробовик=" + player.getShotgun().getAmmo() +
                " | Трава=" + player.getHerbs() +
                " | Граната=" + player.getGrenades());
    }
}