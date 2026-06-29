import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionTableTest {

    @Test // тест сценарію 7 - постріл у голову, успішне влучання
    void testCriticalHitLogic(){
        Pistol pistol = new Pistol();
        // перевіряємо умову х1 = 1, х2 = 1, х3 = 1
        int damage = pistol.calculateDamage(1);// 1 - голова
        pistol.useAmmo();

        assertEquals(45, damage, "Шкода має бути критичною (15*3)");
        assertEquals(4, pistol.getAmmo(), "Набій має бути списаний");

    }

    @Test // тест сценарію 5 - постріл у тулуб, успішне влучання
    void testBodyShotSuccess() {
        Pistol pistol = new Pistol();
        // перевіряємо умови х1=1, x2=0, x3=1
        int damage = pistol.calculateDamage(2);
        pistol.useAmmo();

        assertEquals(15, damage, "Шкода в тулуб має бути базовою (1x15=15)");
        assertEquals(4, pistol.getAmmo(), "Після пострілу набій має бути списано");
    }

    @Test // тест сценарію 4,6 - промах при наявності набоїв
    void testMissLogic() {
        Pistol pistol = new Pistol();
        // перевіряємо умову x1=1, x3=0
        pistol.useAmmo();

        assertEquals(4, pistol.getAmmo(), "Після пострілу набій має бути списано");
    }

    @Test // тест сценарію 0-3 - спроба пострілу без набоїв
    void testNoAmmoLogic() {
        Pistol pistol = new Pistol();
        pistol.setAmmo(0); // x1=0
        pistol.useAmmo();

        assertEquals(0, pistol.getAmmo(), "Кількість набоїв не може бути від'ємною");
    }
}
