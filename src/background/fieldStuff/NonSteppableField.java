package background.fieldStuff;

/**
 * Trieda NonSteppableField predstavuje políčko, na ktoré sa nedá stúpiť a ani sa na ňom nedá stavať.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class NonSteppableField extends BasicField {
    /**
     * Inicializujú sa atribúty triedy podľa konštruktora triedy BasicField.
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(GRASS)
     */
    public NonSteppableField(int x, int y, FieldType type) {
        super(x, y, type);
    }
}
