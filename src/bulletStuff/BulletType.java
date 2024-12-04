package bulletStuff;

/**
 * Enumeration BulletType predstavuje rôzne typy nábojov pre rôzne typy veží.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public enum BulletType {
    MAGEBULLET(".\\Pictures\\Bullets\\naboj.png", 7),
    ARCHERBULLET(".\\Pictures\\Bullets\\arrow.png", 10),
    ARTILERYBULLET(".\\Pictures\\Bullets\\Bomb.png", 15);

    private final String pathToImage;
    private final int imageWidht;

    BulletType(String pathToImage, int imageWidht) {
        this.pathToImage = pathToImage;
        this.imageWidht = imageWidht;
    }

    /**
     * Metóda vracia veľkosť obrázka náboja
     */
    public int getImageWidht() {
        return this.imageWidht;
    }

    /**
     * Metóda vracia relatívnu cestu k súboru s obrázkom ako String
     */
    public String getPathToImage() {
        return this.pathToImage;
    }
}
