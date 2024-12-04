package background.fieldStuff;

/**
 * Trieda TowerPheripheryField predstavuje políčko ktoré je okolo políčka TowerPlace.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class TowerPheripheryField extends BasicField {
    private TowerPlace towerPlace;

    /**
     * Inicializujú sa atribúty triedy podlľa konštruktora triedy BasicField a navyše
     * sa inicializuje atribút, ktorý uchováva referenciu na najbližšie políčko,
     * ktoré je typu TowerPlace.
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(TOWER_PLACE)
     */
    public TowerPheripheryField(int x, int y, FieldType type) {
        super(x, y, type);
        this.towerPlace = null;
    }

    /**
     * V metóde sa nastaví referencia na najbližšie políčko, ktoré je typu CENTER_OF_TOWER do atribútu towerPlace
     * @param towerPlace - najbližšie políčko, ktoré je typu CENTER_OF_TOWER
     */
    public void setTowerPlace(TowerPlace towerPlace) {
        this.towerPlace = towerPlace;
    }

    /**
     * Metóda vracia referenciu na najbližšie políčko, ktoré je typu CENTER_OF_TOWER
     */
    public TowerPlace getTowerPlace() {
        return this.towerPlace;
    }
}
