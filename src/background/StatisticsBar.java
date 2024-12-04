package background;

import gameSetup.Player;
import fri.shapesge.FontStyle;
import fri.shapesge.Obrazok;
import fri.shapesge.Text;

/**
 * Trieda StatisticBar sa stará a zobrazovanie štatistík priebehu hry,
 * ako sú čas, počet životov hráča a peňazí hráča.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class StatisticsBar {
    private final Text timeStats;
    private int seconds;
    private int minutes;
    private final Text lifeStats;
    private final Text moneyStats;

    /**
     * Inicializujú sa minúty, sekundy, textové polia s peniazmi, časom a životmi hráča a vykreslí sa obrázok štatistík.
     */
    public StatisticsBar() {
        Obrazok statisticMenu = new Obrazok(".\\Pictures\\PanelImages\\StatisticMenu.png", 25, 22);
        statisticMenu.zobraz();
        //Prebraté z projektu Pac Man z predmetu úvod do štúdia - autor Adam Adamec
        this.timeStats = new Text("00:00", 120, 78);
        this.timeStats.zmenFont("Arial", FontStyle.BOLD, 15);
        this.timeStats.zmenFarbu("white");
        this.timeStats.zobraz();
        this.lifeStats = new Text(0 + "", 80, 50);
        this.lifeStats.zmenFont("Arial", FontStyle.BOLD, 15);
        this.lifeStats.zmenFarbu("white");
        this.lifeStats.zobraz();
        this.minutes = 0;
        this.moneyStats = new Text(0 + "", 150, 50);
        this.moneyStats.zmenFont("Arial", FontStyle.BOLD, 15);
        this.moneyStats.zmenFarbu("white");
        this.moneyStats.zobraz();
        //
    }

    /**
     * Metóda zmení text v stave životov hráča
     * @param healthPoints - počet životov hráča
     */
    public void changeLivesCount(int healthPoints) {
        this.lifeStats.changeText( healthPoints + "");
    }

    /**
     * Metóda sa stará a zmenu textu s časom na plátne.
     */
    public void changeTime() {
        if (this.seconds / 59 < 1) {
            this.seconds++;

        } else if (this.seconds / 59 == 1) {
            this.seconds = 0;
            this.minutes++;
        }
        if (this.seconds < 10) {
            this.timeStats.changeText("0" + this.minutes + ":0" + this.seconds);
        } else {
            this.timeStats.changeText("0" + this.minutes + ":" + this.seconds);
        }
    }

    /**
     * Metóda vracia počet minút od začiatku hry.
     */
    public int getMinutes() {
        return this.minutes;
    }

    /**
     * Metóda sa stará o zmenu textu s finančným stavom hráča
     * @param player - inštancia hráča
     */
    public void changeMoneyStats(Player player) {
        this.moneyStats.changeText(player.getMoney() + "");
    }
}
