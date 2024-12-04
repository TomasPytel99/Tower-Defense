package background.fieldStuff;

import towers.Tower;

import java.util.Optional;

/**
 * Interface Constructive slúži na rozlíšenie políčok na ktorých sa môže postaviť veža.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public interface Constructive {
    /**
     * Metóda vracia inštanciu veže ak je postavená, ak nie tak vráti prázdny Optional.
     */
    Optional<Tower> getTower();

    /**
     * Metóda priradí políčku vežu, ktorá je na ňom postavená.
     * @param tower - veža, ktorá je postavená na danom políčku
     */
    void setTower(Tower tower);
}
