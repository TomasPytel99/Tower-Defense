package towers;

/**
 * Trieda ArtilleryTower predstavuje typ veže, ktorá strieľa na nepriateľov
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class ArtileryTower extends Tower {
    /**
     * Inicializujú sa atribúty pomocou konštruktora triedy Tower
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     */
    public ArtileryTower(int x, int y) {
        super(x, y, 30, 90, TowerType.ARTILLERY, TowerLevel.TOWER_LEVEL1, 32, 30);
    }
}