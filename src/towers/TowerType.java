package towers;

import bulletStuff.BulletType;
import bulletStuff.MagicBall;
import bulletStuff.Arrow;
import bulletStuff.Bomb;

/**
 * Enumeration TowerType predstavuje jednotlivé typy veží, ktoré je možné postaviť,
 * spravuje údaje o obrázkoch veží na jednotlivých úrovniach, cenu vylepšenia pre rôzne typy,
 * o koľko sa majú zmeniť štatistiky veží na daných úrovniach, a náboje pre jednotlivé typy
 *
 * @author Tomáš Pytel
 * @version 1
 */
public enum TowerType {
    MAGE(".\\Pictures\\Towers\\Mage\\MageTowerLvl1.png", ".\\Pictures\\Towers\\Mage\\MageTowerLvl2.png",
            ".\\Pictures\\Towers\\Mage\\MageTowerLvl3.png", 100, new TowerLevelInfo(120, 50, 10),
            new TowerLevelInfo(150, 60, 20), BulletType.MAGEBULLET),
    ARCHER(".\\Pictures\\Towers\\Archer\\ArcherTowerLvl1.png", ".\\Pictures\\Towers\\Archer\\ArcherTowerLvl2.png",
            ".\\Pictures\\Towers\\Archer\\ArcherTowerLvl3.png", 70, new TowerLevelInfo(100, 30, 50),
            new TowerLevelInfo(120, 40, 40), BulletType.ARCHERBULLET),
    ARTILLERY(".\\Pictures\\Towers\\Artillery\\ArtilleryLvl1.png", ".\\Pictures\\Towers\\Artillery\\ArtilleryLvl2.png",
            ".\\Pictures\\Towers\\Artillery\\ArtilleryLvl3.png", 120, new TowerLevelInfo(120, 50, 45),
            new TowerLevelInfo(200, 60, 60), BulletType.ARTILERYBULLET);


    private final String pathToImageLvl1;
    private final String pathToImageLvl2;
    private final String pathToImageLvl3;
    private final int initialCost;
    private final TowerLevelInfo towerLevel2Info;
    private final TowerLevelInfo towerLevel3Info;
    private final BulletType bulletType;
    TowerType(String pathToImageLvl1, String pathToImageLvl2, String pathToImageLvl3, int initialCost, TowerLevelInfo towerLevel2Info, TowerLevelInfo towerLevel3Info, BulletType bulletType) {
        this.pathToImageLvl1 = pathToImageLvl1;
        this.pathToImageLvl2 = pathToImageLvl2;
        this.pathToImageLvl3 = pathToImageLvl3;
        this.towerLevel2Info = towerLevel2Info;
        this.towerLevel3Info = towerLevel3Info;
        this.bulletType = bulletType;
        this.initialCost = initialCost;
    }

    /**
     * Metóda vracia relatívnu cestu k obrázku veže úrovne 1
     */
    public String getPathToImageLvl1() {
        return this.pathToImageLvl1;
    }
    /**
     * Metóda vracia relatívnu cestu k obrázku veže úrovne 2
     */
    public String getPathToImageLvl2() {
        return this.pathToImageLvl2;
    }
    /**
     * Metóda vracia relatívnu cestu k obrázku veže úrovne 3
     */
    public String getPathToImageLvl3() {
        return this.pathToImageLvl3;
    }
    /**
     * Metóda vracia cenu postavenia veže
     */
    public int getInitialCost() {
        return this.initialCost;
    }

    /**
     * Metóda vracia record, ktorý nesie informácie o úrovni 2
     */
    public TowerLevelInfo getTowerLevel2Info() {
        return this.towerLevel2Info;
    }
    /**
     * Metóda vracia record, ktorý nesie informácie o úrovni 3
     */
    public TowerLevelInfo getTowerLevel3Info() {
        return this.towerLevel3Info;
    }

    /**
     * Metóda vytvorí náboj na základe typu náboja, ktorý daná veža používa
     * @param bulletX - x - ová súradnica ľavého horného rohu obrázku náboja
     * @param bulletY - y - ová súradnica ľavého horného rohu obrázku náboja
     * @param enemyPositionX - x - ová súradnica stredu obrázku nepriateľa
     * @param enemyPositionY - y - ová súradnica stredu obrázku nepriateľa
     */
    public void createBullet(int bulletX, int bulletY, int enemyPositionX, int enemyPositionY) {
        switch (this.bulletType) {
            case MAGEBULLET -> new MagicBall(bulletX, bulletY, enemyPositionX, enemyPositionY);
            case ARCHERBULLET -> new Arrow(bulletX, bulletY, enemyPositionX, enemyPositionY);
            case ARTILERYBULLET -> new Bomb(bulletX, bulletY, enemyPositionX, enemyPositionY);
        }
    }
}
