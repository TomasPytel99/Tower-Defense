package bulletStuff;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

/**
 * Trieda Bullet predstavuje náboj, ktorý vystrelí veža.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public abstract class Bullet {
    private int positionX;
    private int positionY;
    private int framePositionX;
    private int framePositionY;
    private final int enemyPositionX;
    private final int enemyPositionY;
    private final int shiftX;
    private final int shiftY;
    private final Obrazok image;
    private final Manazer manager;

    /**
     * Inicializujú sa atribúty, nastaví sa pozícia,
     * na ktorej sa má obrázok náboja vykresliť a následne sa vykreslí
     * @param framePositionX - x - ová súradnica ľavého horného rohu obrázka
     * @param framePositionY - y - ová súradnica ľavého horného rohu obrázka
     * @param enemyPositionX - x - ová súradnica stredu obrázka
     * @param enemyPositionY - x - ová súradnica stredu obrázka
     * @param bulletType - typ náboja
     */
    public Bullet(int framePositionX, int framePositionY, int enemyPositionX, int enemyPositionY, BulletType bulletType) {
        this.framePositionX = framePositionX;
        this.framePositionY = framePositionY;
        int imageWidth = bulletType.getImageWidht();
        this.positionX = this.framePositionX + imageWidth / 2;
        this.positionY = this.framePositionY + imageWidth / 2;
        this.image = new Obrazok(bulletType.getPathToImage(), this.framePositionX, this.framePositionY);
        this.image.zobraz();
        this.enemyPositionX = enemyPositionX;
        this.enemyPositionY = enemyPositionY;
        int distanceX = enemyPositionX - this.positionX;
        int distanceY = enemyPositionY - this.positionY;
        this.shiftX = distanceX / 5;
        this.shiftY = distanceY / 5;
        this.manager = new Manazer();
        this.manager.spravujObjekt(this);
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
     * Metóda otočí obrázok o daný uhol
     */
    public void rotateImage(int angle) {
        this.image.zmenUhol(angle);
    }

    /**
     * Metóda posunie obrázok náboja o zadanú dĺžku vodorovne aj zvisle
     */
    public void moveBullet() {
        this.framePositionX += this.shiftX;
        this.framePositionY += this.shiftY;
        this.positionX += this.shiftX;
        this.positionY += this.shiftY;
        this.image.posunVodorovne(this.shiftX);
        this.image.posunZvisle(this.shiftY);
        if (Math.abs(this.positionX - this.enemyPositionX) < 5 && Math.abs(this.positionY - this.enemyPositionY) < 5) {
            this.image.skry();
            this.manager.prestanSpravovatObjekt(this);
        }
    }
}
