package towers;

import gameSetup.EnemyList;
import enemies.BasicEnemy;
import fri.shapesge.Obrazok;

/**
 * Trieda Tower predstavuje vežu, ktorá strieľa po nepriateľoch a dá sa vylepšiť.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public abstract class Tower {
    private final int positionX;
    private final int positionY;
    private BasicEnemy enemy;
    private int firepower;
    private int range;
    private final Obrazok image;
    private final TowerType towerType;
    private TowerLevel towerLevel;
    private final int scanInterval;
    private final int fireInterval;
    private int scanCounter;
    private int fireCounter;

    /**
     * Inicializujú sa atribúty, a zobrazí sa obrázok
     * @param x - ová súradnica ľavého horného rohu obrázka
     * @param y - ová súradnica ľavého horného rohu obrázka
     * @param firepower - palebná sila veže
     * @param range - dostrel veže
     * @param towerType - typ veže
     * @param towerLevel - level veže
     */
    public Tower(int x, int y, int firepower, int range, TowerType towerType, TowerLevel towerLevel, int scanInterval, int fireInterval) {
        int frameWidth = 100;
        this.positionX = x + frameWidth / 2;
        this.positionY = y + frameWidth / 2;
        this.towerType = towerType;
        this.image = new Obrazok(this.towerType.getPathToImageLvl1(), x, y);
        this.image.zobraz();
        this.firepower = firepower;
        this.range = range;
        this.towerLevel = towerLevel;
        this.fireInterval = fireInterval;
        this.scanInterval = scanInterval;
        this.fireCounter = 0;
        this.scanCounter = 0;
    }

    /**
     * Metóda vracia akého typu je veža
     */
    public TowerType getTowerType() {
        return this.towerType;
    }

    /**
     * Metóda vracia aktuálnu úroveň vylepšenia veže
     */
    public TowerLevel getTowerLevel() {
        return this.towerLevel;
    }

    /**
     * Metóda v určitých časových intervaloch vytvorí náboj ak je na blízku nepriateľ
     */
    public void fire() {
        if (this.fireCounter == this.fireInterval) {
            if (this.enemy != null) {
                this.towerType.createBullet(this.positionX, this.positionY, this.enemy.getPositionX(), this.enemy.getPositionY());
                this.enemy.setHealth(-this.firepower);
                this.enemy = null;
            }
            this.fireCounter = 0;
        }
        this.fireCounter++;
    }

    /**
     * Metóda sa stará o vylepšenie veže
     */
    public void upgrade() {
        if (this.towerLevel == TowerLevel.TOWER_LEVEL1) {
            this.image.zmenObrazok(this.towerType.getPathToImageLvl2());
            this.setTowerLevel(TowerLevel.TOWER_LEVEL2);
            this.firepower += this.towerType.getTowerLevel2Info().firepowerUp();
            this.range += this.towerType.getTowerLevel2Info().rangeUp();
        } else if (this.towerLevel == TowerLevel.TOWER_LEVEL2) {
            this.image.zmenObrazok(this.towerType.getPathToImageLvl3());
            this.setTowerLevel(TowerLevel.TOWER_LEVEL3);
            this.firepower += this.towerType.getTowerLevel3Info().firepowerUp();
            this.range += this.towerType.getTowerLevel3Info().rangeUp();
        }
    }

    /**
     * Metóda prechádza zoznamom nepriateľov v určitých časových intervaloch
     * a prvý čo je na dostrel sa nastaví ako cieľ
     * @param enemyList - zoznam nepriateľov
     */
    public void scanEnemies(EnemyList enemyList) {
        if (this.scanCounter == this.scanInterval) {
            for (BasicEnemy currentEnemy : enemyList.getEnemyList()) {
                if (this.getDistance(this.positionX, this.positionY, currentEnemy.getPositionX(), currentEnemy.getPositionY()) <= this.range) {
                    this.enemy = currentEnemy;
                }
            }
            this.scanCounter = 0;
        }
        this.scanCounter++;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int)Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }

    private void setTowerLevel(TowerLevel towerLevel) {
        this.towerLevel = towerLevel;
    }
}
