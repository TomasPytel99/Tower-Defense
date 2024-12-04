package bulletStuff;
/**
 * Trieda MagicBall predstavuje typ náboja pre vežu Mage.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class MagicBall extends Bullet {
    /**
     * Inicializujú sa atribúty na základe konštruktora triedy Bullet
     * @param framePositionX - x - ová súradnica ľavého horného rohu obrázku náboja
     * @param framePositionY - y - ová súradnica ľavého horného roju obrázku náboja
     * @param enemyPositionX - x - ová súradnica stredu obrázka nepriateľa
     * @param enemyPositionY - y - ová súradnica stredu obrázka nepriateľa
     */
    public MagicBall(int framePositionX, int framePositionY, int enemyPositionX, int enemyPositionY) {
        super(framePositionX, framePositionY, enemyPositionX, enemyPositionY, BulletType.MAGEBULLET);
    }
}
