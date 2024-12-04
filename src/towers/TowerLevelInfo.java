package towers;

/**
 * Record TowerLevelInfo predstavuje dátové úložisko pre cenu vylepšenia,
 * zmeny dostrelu veže a palebnej sily veže pre jednotlivé úrovne veží
 * @param upgradeCost - cena vylepšenia na ďalšiu úroveň
 * @param firepowerUp - o koľko sa má zmeniť dostrel veže
 * @param rangeUp - o koľko sa má zmeniť palebná sila veže
 *
 * @author Tomáš Pytel
 * @version 1
 */
public record TowerLevelInfo(int upgradeCost, int firepowerUp, int rangeUp) { }