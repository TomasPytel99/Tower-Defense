package gameSetup;

import background.Map;
import background.StatisticsBar;
import towers.Tower;
import fri.shapesge.Manazer;
import javax.swing.JOptionPane;
import java.io.IOException;

/**
 * Trieda Game sa stará o spoluprácu jenotlivých objektov hry.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Game {
    private Player player;
    private EnemyList enemyList;
    private TowerList towerList;
    private StatisticsBar statisticsBar;
    private Manazer manager;

    /**
     * Vytvorí sa inštancia hráča, zoznamu nepriateľov, zoznamu veží,
     * mapy a štatistík
     */
    public Game() {
        try {
            Map map = new Map(".\\Maps\\SpravnaMapa.txt", ".\\Pictures\\Map\\Map1.png");
            this.towerList = new TowerList(map);
            this.player = new Player(map, this.towerList, 250);
            this.enemyList = new EnemyList(map);
            this.statisticsBar = new StatisticsBar();
            this.manager = new Manazer();
            this.gameSetup();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,  "We failed to start the game!");
        }
    }

    private void gameSetup() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to start the game ? ") == 0) {
            this.manager.spravujObjekt(this.towerList);
            this.manager.spravujObjekt(this.player);
            this.manager.spravujObjekt(this.enemyList);
            this.manager.spravujObjekt(this.statisticsBar);
            this.manager.spravujObjekt(this);
        }
    }

    /**
     * Metóda prejde zoznam veží, pričom každá veža si prejde zoznam nepriateľov a vystrelí
     */
    public void scanEnemiesAndFire() {
        for (Tower tower : this.towerList.getTowerList()) {
            tower.scanEnemies(this.enemyList);
            tower.fire();
        }
    }

    /**
     * Metóda sa stará o úpravu štatistík hry, kontrolu stavu nepriateľov, kontrolu životov hráča, a kontrolu času
     */
    public void scanDeadEnemiesAndGameCheck() {
        this.player.moneyTransactions(this.enemyList.scanDeadEnemies(this.player));
        this.statisticsBar.changeMoneyStats(this.player);
        this.statisticsBar.changeLivesCount(this.player.getHealthPoints());
        if (this.statisticsBar.getMinutes() == 2) {
            this.endGame();
            JOptionPane.showMessageDialog(null, "Excellent you win!");
        }
        if (this.player.getHealthPoints() < 1) {
            this.endGame();
            JOptionPane.showMessageDialog(null, "Defeat! You lost all the lives.");
        }
    }
    private void endGame() {
        this.manager.prestanSpravovatObjekt(this);
        this.manager.prestanSpravovatObjekt(this.towerList);
        this.manager.prestanSpravovatObjekt(this.player);
        this.manager.prestanSpravovatObjekt(this.enemyList);
        this.manager.prestanSpravovatObjekt(this.statisticsBar);
    }
}
