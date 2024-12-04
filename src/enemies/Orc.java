package enemies;

import background.Map;
/**
 * Trieda Orc predstavuje typ nepriateľa
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Orc extends BasicEnemy {
    /**
     * Inicializujú sa atribúty na pomocou konštruktora triedy BasicEnemy
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     * @param map - mapa
     */
    public Orc(int x, int y, Map map) {
        super(x, y, 32, 80, 9, 1, ".\\Pictures\\Enemies\\Orc\\OrcLeft1.png",
                ".\\Pictures\\Enemies\\Orc\\OrcLeft2.png", ".\\Pictures\\Enemies\\Orc\\OrcRight1.png",
                ".\\Pictures\\Enemies\\Orc\\OrcRight2.png",  map);
    }
}
