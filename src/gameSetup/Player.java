package gameSetup;

import background.Map;
import background.fieldStuff.BasicField;
import background.fieldStuff.TowerPheripheryField;
import background.fieldStuff.TowerPlace;
import fri.shapesge.Manazer;
import towers.Tower;

/**
 * Trieda predstavuje hráča, ktorým je použivateľ, stará sa o monitorovanie kliknutí hráča,
 * jeho počt uživotov, a peňazí, ktoré hráč má.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Player {
    private Manazer manazer;
    private final Map map;
    private final TowerList towerList;
    private int healthPoints;
    private int money;

    /**
     * Hráčovi sa priradí zoznam veží, nastaví sa počet životov a peňazí
     * @param map - mapa
     * @param towerList - zoznam veží
     * @param money - množstvo peňazí
     */
    public Player(Map map, TowerList towerList, int money) {

        this.map = map;
        this.towerList = towerList;
        this.money = money;
        this.healthPoints = 10;

    }

    /**
     * Metóda sa stará o postavenie veže na mieste, kde hráč klikol
     * @param x - súradnica kliknutia hráča
     * @param y - súradnica kliknutia hráča
     */
    public void vyberSuradnice(int x, int y) {
        Tower tower = this.towerList.buildTower(x, y, this);
        if ( tower != null) {
            this.towerList.addTower(tower);
            if (this.map.getField(x / BasicField.IMAGE_WIDTH, y / BasicField.IMAGE_WIDTH) instanceof TowerPlace towerPlace) {
                towerPlace.setTower(tower);
            } else if (this.map.getField(x / BasicField.IMAGE_WIDTH, y / BasicField.IMAGE_WIDTH) instanceof TowerPheripheryField towerPheriphery) {
                towerPheriphery.getTowerPlace().setTower(tower);
            }
        }
    }

    /**
     * Metóda zníži počet životov hráča podľa zadanej hodnoty
     * @param howMuch - o koľko sa majú ubrať životy hráčovi
     */
    public void lessenHealthPoints(int howMuch) {
        this.healthPoints -= howMuch;
    }

    /**
     * Metóda zmení finančný stav hráča podľa zadanej hodnoty
     * @param prize - koĺko peňazí sa má pridať/ubrať
     */
    public void moneyTransactions(int prize) {
        this.money += prize;
    }

    /**
     * Metóda vracia množstvo peňazí, ktoré má hráč aktuálne k dispozícii
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Metóda vracia aktuálny počet životov hráča
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }
}
