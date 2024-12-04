package towers;
/**
 * Trieda MageTower predstavuje typ veže, ktorá strieľa na nepriateľov
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class MageTower extends Tower {
    /**
     * Inicializujú sa atribúty pomocou konštruktora triedy Tower
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     */
    public MageTower(int x, int y) {
        super(x, y, 13, 80, TowerType.MAGE, TowerLevel.TOWER_LEVEL1, 17, 15);
    }
}
