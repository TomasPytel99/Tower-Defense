package towers;

/**
 * Trieda ArcherTower predstavuje typ veže, ktorá strieľa šípy na nepriateľov
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class ArcherTower extends Tower {
    /**
     * Inicializujú sa atribúty pomocou konštruktora triedy Tower
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     */
    public ArcherTower(int x, int y) {
        super(x, y, 5, 80, TowerType.ARCHER, TowerLevel.TOWER_LEVEL1, 10, 8);
    }
}