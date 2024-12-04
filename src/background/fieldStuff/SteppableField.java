package background.fieldStuff;

/**
 * Trieda SteppableField predstavuje políčko, po ktorom sa môžu nepriatelia pohybovať.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class SteppableField extends BasicField {
    private final AllowedDirection allowedDirection;

    /**
     * Inicializujú sa atribúty triedy podľa konštruktora triedy BasicField
     * a nastaví sa smer, ktorým je možné pokračovať v pohybe.
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(ROAD, FINISH, START)
     * @param allowedDirection - smer, ktorým je možné pohnúť sa na ďalšie políčko
     */
    public SteppableField(int x, int y, FieldType type, AllowedDirection allowedDirection) {
        super(x, y, type);
        this.allowedDirection = allowedDirection;
    }

    /**
     * Metóda vracia smer, ktorým je možné pohnúť sa ďalej
     */
    public AllowedDirection getAllowedDirection() {
        return this.allowedDirection;
    }
}
