package bulletStuff;

/**
 * Trieda Arrow predstavuje typ náboja pre vežu Archer.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Arrow extends Bullet {
    /**
     * Inicializujú sa atribúty na základe konštruktora triedy Bullet a obrázok náboja sa otočí smerom,
     * v ktorom sa bude pohybovať
     * @param framePositionX - x - ová súradnica ľavého horného rohu obrázku náboja
     * @param framePositionY - y - ová súradnica ľavého horného roju obrázku náboja
     * @param enemyPositionX - x - ová súradnica stredu obrázka nepriateľa
     * @param enemyPositionY - y - ová súradnica stredu obrázka nepriateľa
     */
    public Arrow(int framePositionX, int framePositionY, int enemyPositionX, int enemyPositionY) {
        super(framePositionX, framePositionY, enemyPositionX, enemyPositionY, BulletType.ARCHERBULLET);
        this.rotateImage(135 + this.getUholOtocenia(enemyPositionX, enemyPositionY));// + 135 až je otočený doľava ten šíp
    }
    private int getUholOtocenia(int enemyPositionX, int enemyPositionY) {
        int angle = (int)Math.toDegrees(Math.atan( (enemyPositionY - this.getPositionY() + 0.0) / (enemyPositionX - this.getPositionX())));
        return angle;
    }
}
