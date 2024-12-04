package gui;

import gameSetup.Game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Trieda MainMenu predstavuje prostredie pre používateľa, cez ktoré vie spustiť hru.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class MainMenu {
    private final JFrame window;
    private final JLabel background;
    private JPanel panel;
    private JButton startGameButton;

    /**
     * Vytvorí sa okno s tlačítkom pre začatie hry
     */
    public MainMenu() {
        this.window = new JFrame();
        this.window.setSize(913, 710);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //https://www.tabnine.com/code/java/methods/javax.swing.JFrame/setDefaultCloseOperation

        //Prebraté z https://www.youtube.com/watch?v=TQEEsR559QQ
        this.background = new JLabel("", new ImageIcon(".\\Pictures\\PanelImages\\MainMenu.png"), JLabel.LEADING);
        this.background.setBounds(0, 0, 900, 675);
        this.startGameButton.setIcon(new ImageIcon(".\\Pictures\\PanelImages\\StartButton.png"));
        //
        this.startGameButton.addActionListener(e -> {
            Game game = new Game();
            MainMenu.this.window.setVisible(false);
        });
        this.window.add(this.background);
        this.window.add(this.panel);
        this.window.setVisible(true);
    }
}