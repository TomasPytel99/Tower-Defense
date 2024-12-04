package enemies;

import background.fieldStuff.AllowedDirection;
import background.fieldStuff.BasicField;
import background.fieldStuff.SteppableField;
import background.Map;
import fri.shapesge.Obrazok;

/**
 * Trieda BasicEnemy predstavuje nepriateľa,
 * ktorý je reprezentovaný na hracej ploche pomocou obrázka.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public abstract class BasicEnemy {
    private int positionX;
    private int positionY;
    private int framePositionX;
    private int framePositionY;
    private int health;
    private int frameCounter;
    private int fieldIndexX;
    private int fieldIndexY;
    private final Map map;
    private final int reward;
    private final int fine;
    private final String pathToLeftImage1;
    private final String pathToLeftImage2;
    private final String pathToRightImage1;
    private final String pathToRightImage2;
    private Obrazok actualFrame;
    private BasicField currentField;

    /**
     * Inicializujú sa atribúty, nastaví sa začiatočný obrázok a vykreslí sa
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     * @param health - počet životov nepriateľa
     * @param reward - odmena za zničenie nepriateľa
     * @param fine - pokuta, ak sa nepriateľ dostane na koniec mapy
     * @param pathToLeftImage1 - relatívna cesta k 1. obrázku nepriateľa otočeného doľava
     * @param pathToLeftImage2 - relatívna cesta k 2. obrázku nepriateľa otočeného doľava
     * @param pathToRightImage1 - relatívna cesta k 1. obrázku nepriateľa otočeného doprava
     * @param pathToRightImage2 - relatívna cesta k 2. obrázku nepriateľa otočeného doprava
     * @param map - mapa
     */
    public BasicEnemy(int x, int y, int frameWidth, int health, int reward, int fine, String pathToLeftImage1, String pathToLeftImage2, String pathToRightImage1, String pathToRightImage2, Map map) {
        this.framePositionX = x;
        this.framePositionY = y;
        this.positionX = this.framePositionX + frameWidth / 2;
        this.positionY = this.framePositionY + frameWidth / 2;
        this.health = health;
        this.reward = reward;
        this.fine = fine;
        this.frameCounter = 1;
        this.pathToLeftImage1 = pathToLeftImage1;
        this.pathToLeftImage2 = pathToLeftImage2;
        this.pathToRightImage1 = pathToRightImage1;
        this.pathToRightImage2 = pathToRightImage2;
        this.actualFrame = new Obrazok(this.pathToLeftImage1, this.framePositionX, this.framePositionY);
        this.actualFrame.zobraz();
        this.map = map;
        this.fieldIndexX = this.framePositionX / BasicField.IMAGE_WIDTH;
        this.fieldIndexY = this.framePositionY / BasicField.IMAGE_WIDTH;
        this.currentField = this.map.getField(this.fieldIndexX, this.fieldIndexY);
    }

    /**
     * Metóda si podľa aktuálneho smeru vypýta od mapy ďalšie políčko v poradí
     * a nepriateľ sa posunie v zadanom smere
     */
    public void move() {
        if (this.currentField instanceof SteppableField field) {
            AllowedDirection allowedDirection = field.getAllowedDirection();
            switch (allowedDirection) {
                case NORTH -> {
                    this.fieldIndexY--;
                    this.moveUp();
                }
                case SOUTH -> {
                    this.fieldIndexY++;
                    this.moveDown();
                }
                case EAST -> {
                    this.fieldIndexX++;
                    this.moveRight();
                }
                case WEST -> {
                    this.fieldIndexX--;
                    this.moveLeft();
                }
                case NORTH_WEST -> {
                    this.fieldIndexY--;
                    this.fieldIndexX--;
                    this.moveUp();
                    this.moveLeft();
                }
                case NORTH_EAST -> {
                    this.fieldIndexY--;
                    this.fieldIndexX++;
                    this.moveUp();
                    this.moveRight();
                }
                case SOUTH_WEST -> {
                    this.fieldIndexY++;
                    this.fieldIndexX--;
                    this.moveDown();
                    this.moveLeft();
                }
                case SOUTH_EAST -> {
                    this.fieldIndexY++;
                    this.fieldIndexX++;
                    this.moveDown();
                    this.moveRight();
                }
            }
            this.currentField = this.map.getField(this.fieldIndexX, this.fieldIndexY);
        }
    }
    private void moveUp() {
        this.framePositionY -= BasicField.IMAGE_WIDTH;
        this.positionY -= BasicField.IMAGE_WIDTH;
        this.actualFrame.posunZvisle(-BasicField.IMAGE_WIDTH);
    }
    private void moveDown() {
        this.framePositionY += BasicField.IMAGE_WIDTH;
        this.positionY += BasicField.IMAGE_WIDTH;
        this.actualFrame.posunZvisle(BasicField.IMAGE_WIDTH);
    }
    private void moveRight() {
        this.framePositionX += BasicField.IMAGE_WIDTH;
        this.positionX += BasicField.IMAGE_WIDTH;
        this.actualFrame.posunVodorovne(BasicField.IMAGE_WIDTH);
    }
    private void moveLeft() {
        this.framePositionX -= BasicField.IMAGE_WIDTH;
        this.positionX -= BasicField.IMAGE_WIDTH;
        this.actualFrame.posunVodorovne(-BasicField.IMAGE_WIDTH);
    }

    /**
     * Metóda strieda obrázky nepriateľa a tým vytvára animáciu
     */
    public void animation() {
        if (this.currentField instanceof SteppableField field) {
            if (field.getAllowedDirection() == AllowedDirection.EAST || field.getAllowedDirection() == AllowedDirection.NORTH_EAST ||
                field.getAllowedDirection() == AllowedDirection.SOUTH_EAST) {
                this.frameChange(this.pathToRightImage1, this.pathToRightImage2);
            } else {
                this.frameChange(this.pathToLeftImage1, this.pathToLeftImage2);
            }
        }
    }

    private void frameChange(String pathToImage1, String pathToImage2) {
        if (this.frameCounter == 1) {
            this.actualFrame.zmenObrazok(pathToImage1);
            this.frameCounter = 2;
        } else {
            this.actualFrame.zmenObrazok(pathToImage2);
            this.frameCounter = 1;
        }
    }

    /**
     * Metóda aktualizuje životy nepriateľa podľa zadanej hodnoty
     * @param howMuch - o koľko sa majú zmeniť životy
     */
    public void setHealth(int howMuch) {
        this.health += howMuch;
    }

    /**
     * Metóda vracia x - ovú súradnicu stredu obrázka
     */
    public int getPositionX() {
        return this.positionX;
    }
    /**
     * Metóda vracia y - ovú súradnicu stredu obrázka
     */
    public int getPositionY() {
        return this.positionY;
    }
    /**
     * Metóda vracia počet životov nepriateľa
     */
    public int getHealth() {
        return this.health;
    }
    /**
     * Metóda vracia hodnotu odmeny za zničeného nepriateľa
     */
    public int getReward() {
        return this.reward;
    }
    /**
     * Metóda presunie nepriateľa z dosahu veží a skryje jeho obrázok
     */
    public void destroy() {
        this.actualFrame.skry();
        this.actualFrame = null;
        this.positionX = 2000;
        this.positionY = 2000;
    }
    /**
     * Metóda vracia pokutu za nepriateľa, ktorému sa podarilo dostať na koniec mapy
     */
    public int getFine() {
        return this.fine;
    }
    /**
     * Metóda vracia inštanciu políčka na ktorom sa aktuálne nepriateľ nachádza
     */
    public BasicField getCurrentField() {
        return this.currentField;
    }
}
