package enemies;

import background.Map;

/**
 * Trieda Goblin predstavuje typ nepriateľa
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Goblin extends BasicEnemy {
    /**
     * Inicializujú sa atribúty na pomocou konštruktora triedy BasicEnemy
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     * @param map - mapa
     */
    public Goblin(int x, int y, Map map) {
        super(x, y, 32, 20, 3, 1, ".\\Pictures\\Enemies\\Goblin\\GoblinLeft2 (2).png",
                ".\\Pictures\\Enemies\\Goblin\\GoblinLeft1 (1).png", ".\\Pictures\\Enemies\\Goblin\\GoblinRight1(1).png",
                ".\\Pictures\\Enemies\\Goblin\\GoblinRight2(2).png", map);
    }
}

