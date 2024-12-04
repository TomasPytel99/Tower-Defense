package background.fieldStuff;

/**
 * Trieda BasicField predstavuje prototyp políčka z ktorých sa skladá herná plocha.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public abstract class BasicField {
    private final int x;
    private final int y;
    /**
     * Atribút IMAGE_WIDTH zodpovedá veľkosti herného políčka.
     */
    public static final int IMAGE_WIDTH = 8;
    private final int centerOfFieldX;
    private final int centerOfFieldY;
    private final FieldType type;

    /**
     * Vytvorí sa obyčajné políčko, nastaví sa jeho poloha a typ.
     * @param x - ová súradnica ľavého horného rohu políčka
     * @param y - ová súradnica ľavého horného rohu políčka
     * @param type - typ políčka(ROAD, GRASS...)
     */
    public BasicField(int x, int y, FieldType type) {
        this.x = x;
        this.y = y;
        this.centerOfFieldX = this.x + BasicField.IMAGE_WIDTH / 2;
        this.centerOfFieldY = this.y + BasicField.IMAGE_WIDTH / 2;
        this.type = type;
    }
    /**
     * Metóda vracia x - ovú súradnicu ľavého horného rohu políčka
     */
    public int getX() {
        return this.x;
    }
    /**
     * Metóda vracia y - ovú súradnicu ľavého horného rohu políčka
     */
    public int getY() {
        return this.y;
    }
    /**
     * Metóda vracia x - ovú súradnicu stredu políčka
     */
    public int getCenterOfFieldX() {
        return this.centerOfFieldX;
    }
    /**
     * Metóda vracia y - ovú súradnicu stredu políčka
     */
    public int getCenterOfFieldY() {
        return this.centerOfFieldY;
    }
    /**
     * Metóda vracia typ políčka(ROAD, GRASS...)
     */
    public FieldType getType() {
        return this.type;
    }
}
