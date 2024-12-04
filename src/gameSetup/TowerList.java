package gameSetup;

import background.Map;
import background.fieldStuff.Constructive;
import background.fieldStuff.BasicField;
import background.fieldStuff.TowerPlace;
import background.fieldStuff.TowerPheripheryField;
import background.fieldStuff.FieldType;
import towers.Tower;
import towers.TowerLevel;
import towers.TowerType;
import towers.MageTower;
import towers.ArtileryTower;
import towers.ArcherTower;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Trieda TowerList predstavuje zoznam veží a spravuje ho.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class TowerList {
    private final ArrayList<Tower> towerList;
    private final Map map;

    /**
     * Vytvorí sa nový zoznam veží a mapa sa uloží do atribútu
     * @param map - mapa
     */
    public TowerList(Map map) {
        this.towerList = new ArrayList<>();
        this.map = map;
    }

    /**
     * Metóda vracia zoznam veží ako List
     */
    public List<Tower> getTowerList() {
        return Collections.unmodifiableList(this.towerList);
    }

    /**
     * Metóda pridá vežu do zoznamu
     * @param tower - veža
     * */
    public void addTower(Tower tower) {
        this.towerList.add(tower);
    }
    private void upgradeTower(Constructive constructive, Player player) {
        Optional<Tower> tower = constructive.getTower();
        if (tower.isPresent()) {
            TowerLevel currentLevel = tower.get().getTowerLevel();
            if (currentLevel != TowerLevel.TOWER_LEVEL3) {
                int costForUpgrade = 0;
                switch (currentLevel) {
                    case TOWER_LEVEL1 -> {
                        costForUpgrade = tower.get().getTowerType().getTowerLevel2Info().upgradeCost();
                    }
                    case TOWER_LEVEL2 -> {
                        costForUpgrade = tower.get().getTowerType().getTowerLevel3Info().upgradeCost();
                    }
                }
                if (JOptionPane.showConfirmDialog(null, "Do you want to upgrade this tower? (" + costForUpgrade + ")") == 0) {
                    if (player.getMoney() >= costForUpgrade) {
                        tower.get().upgrade();
                        player.moneyTransactions(-costForUpgrade);
                    } else {
                        JOptionPane.showMessageDialog(null, "You don't have enough money to upgrade this tower.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You have already upgraded your tower to the highest level.");
            }
        }

    }

    /**
     * Metóda sa stará o vytvorenie veže na základe požiadavky hráča,
     * prípadne ak už na danom mieste veža stojí, tak ju umožní vylepšiť
     * @param x - ová súradnica miesta, kde chce hráč postaviť vežu
     * @param y - ová súradnica miesta, kde chce hráč postaviť vežu
     * @param player - hráč, ktorého predstavuje používateľ
     */
    public Tower buildTower(int x, int y, Player player) {
        BasicField field = this.map.getField(x / BasicField.IMAGE_WIDTH, y / BasicField.IMAGE_WIDTH);
        if (field instanceof TowerPheripheryField towerPheripheryField) {
            field = towerPheripheryField.getTowerPlace();
        }
        if (field instanceof TowerPlace towerPlace) {
            if (this.checkTowerPresence(towerPlace)) {
                this.upgradeTower(towerPlace, player);
            } else {
                if (field.getType() == FieldType.CENTER_OF_TOWER) {
                    String[] options = {"Archer (70)", "Mage (100)", "Artillery(120)"};
                    String choice = (String)JOptionPane.showInputDialog(null, "Choose which type of tower do you want to build ", "Tower Type", JOptionPane.QUESTION_MESSAGE, null, options, options[0]); // prebraté z internetu https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/
                    TowerType type;
                    if (choice != null) {
                        type = this.decideOnChoice(choice);
                        if (JOptionPane.showConfirmDialog(null, "Do you want to build a tower ?") == 0) {
                            if (type != null && player.getMoney() >= type.getInitialCost()) {
                                Tower tower = this.createTower(type, field);

                                player.moneyTransactions(-type.getInitialCost());
                                return tower;
                            } else {
                                JOptionPane.showMessageDialog(null, "You don't have enough money for building tower.");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    private TowerType decideOnChoice(String choice) {
        TowerType type = null;
        switch (choice) {
            case "Archer (70)" -> {
                type = TowerType.ARCHER;
            }
            case "Mage (100)" -> {
                type = TowerType.MAGE;
            }
            case "Artillery(120)" -> {
                type = TowerType.ARTILLERY;
            }
        }
        return type;
    }
    private Tower createTower(TowerType type, BasicField field) {
        Tower tower = null;
        switch (type) {
            case MAGE -> {
                tower = new MageTower(field.getCenterOfFieldX() - 5 * BasicField.IMAGE_WIDTH - BasicField.IMAGE_WIDTH / 2, field.getCenterOfFieldY() - 7 * BasicField.IMAGE_WIDTH - BasicField.IMAGE_WIDTH / 2);
            }
            case ARCHER -> {
                tower = new ArcherTower(field.getCenterOfFieldX() - 7 * BasicField.IMAGE_WIDTH, field.getCenterOfFieldY() - 9 * BasicField.IMAGE_WIDTH);
            }
            case ARTILLERY -> {
                tower = new ArtileryTower(field.getCenterOfFieldX() - 6 * BasicField.IMAGE_WIDTH, field.getCenterOfFieldY() - 7 * BasicField.IMAGE_WIDTH);
            }
        }
        return tower;
    }
    private boolean checkTowerPresence(TowerPlace towerPlace) {
        if (towerPlace.getTower().isPresent()) {
            return true;
        }
        return false;
    }
}
