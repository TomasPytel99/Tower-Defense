package background.fieldStuff;

import towers.Tower;

import java.util.Optional;

/**
 * Trieda TowerPlace predstavuje políčko, na ktorom je možné postaviť vežu.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class TowerPlace extends BasicField implements Constructive {
    private Tower tower;

    /**
     * Inicializujú sa atribúty triedy podlľa konštruktora triedy BasicField
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(CENTER_OF_TOWER)
     */
    public TowerPlace(int x, int y, FieldType type) {
        super(x, y, type);
    }

    /**
     * Metóda vracia inštanciu veže, ktorá je na danom políčku postavená
     */
    @Override
    public Optional<Tower> getTower() {
        return Optional.ofNullable(this.tower);
    }

    /**
     * Metóda nastaví do atribútu tower referenciu na inštanciu veźe, ktorá je na danom políčku postavená
     * @param tower - veža, ktorá je postavená na danom políčku
     */
    @Override
    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
