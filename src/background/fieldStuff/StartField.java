package background.fieldStuff;

/**
 * Trieda StartField predstavuje políčko na ktorom sa začnú objavovať nepriatelia a po ktorom sa možno hýbať.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class StartField extends SteppableField {
    /**
     * Inicializujú sa atribúty triedy podľa konštruktora triedy SteppableField.
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(START)
     * @param allowedDirection - smer, ktorým je možné sa vydať z daného políčka
     */
    public StartField(int x, int y, FieldType type, AllowedDirection allowedDirection) {
        super(x, y, type, allowedDirection);
    }
}
