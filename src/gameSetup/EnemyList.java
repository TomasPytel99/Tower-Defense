package gameSetup;

import background.fieldStuff.BasicField;
import background.fieldStuff.FieldType;
import background.Map;
import enemies.BasicEnemy;
import enemies.Goblin;
import enemies.Orc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Trieda EnemyList sa stará o pridávanie nepriateľov do hry a skenovanie zničených nepriateľov.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class EnemyList {
    private final ArrayList<BasicEnemy> listOfEnemies;
    private int counter;
    private final Map map;

    /**
     * Vytvorí sa prázdny zoznam s nepriateľmi a počítadlo sekúnd sa nastaví na 0
     * @param map - mapa hry
     */
    public EnemyList(Map map) {
        this.listOfEnemies = new ArrayList<>();
        this.counter = 0;
        this.map = map;
    }

    /**
     * Metóda pridáva v určitých časových intervaloch nových nepriateľov do hry a na rôzne pozície
     */
    public void addEnemy() {
        Random random = new Random();
        int spawnFieldNumber = random.nextInt(3);
        if (this.counter % 20 == 0) {
            Orc orc = new Orc(this.map.getStartFields().get(spawnFieldNumber).getX() - BasicField.IMAGE_WIDTH, this.map.getStartFields().get(spawnFieldNumber).getY(), this.map);
            this.listOfEnemies.add(orc);
        } else if (this.counter % 5 == 0) {
            Goblin goblin = new Goblin(this.map.getStartFields().get(spawnFieldNumber).getX() - BasicField.IMAGE_WIDTH, this.map.getStartFields().get(spawnFieldNumber).getY(), this.map);
            this.listOfEnemies.add(goblin);
        }
        this.counter++;
    }

    private void removeEnemy(int indexInList) {
        this.listOfEnemies.remove(indexInList);
    }

    /**
     * Metóda prechádza zoznam nepriateľov a zisťuje, či má každý nepriateľ viac ako 0 životov,
     * ak nie, v tom prípade sa nepriateľ odstráni zo zoznamu.
     * Metóda tiež kontroluje, ći sa nepriatelia nachádzajú na konci mapy v cieli, ak áno,
     * potom hráčovi ubudne daný počet životov.
     * @param player - inštancia hráča
     * @return enemyReward - vracia peniaze utŕžené za zničenie nepriateľa
     */
    public int scanDeadEnemies(Player player) {
        int enemyReward = 0;
        for (int i = 0; i < this.listOfEnemies.size(); i++) {
            if (this.listOfEnemies.get(i).getHealth() <= 0) {
                enemyReward += this.listOfEnemies.get(i).getReward();
                this.listOfEnemies.get(i).destroy();
                this.removeEnemy(i);
            } else if (this.listOfEnemies.get(i).getCurrentField().getType() == FieldType.FINISH) {
                player.lessenHealthPoints(this.listOfEnemies.get(i).getFine());
                this.listOfEnemies.get(i).destroy();
                this.removeEnemy(i);
            }
        }
        return enemyReward;
    }

    /**
     * Metóda prejde zoznamom nepriateľov a pohne každého z nich
     */
    public void moveEnemy() {
        for (BasicEnemy enemy : this.listOfEnemies) {
            enemy.animation();
            enemy.move();
        }
    }

    /**
     * Metóda vracia zoznam nepriateľov na mape vo forme Listu
     */
    public List<BasicEnemy> getEnemyList() {
        return Collections.unmodifiableList(this.listOfEnemies);
    }
}
