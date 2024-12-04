package background;
import background.fieldStuff.BasicField;
import background.fieldStuff.StartField;
import background.fieldStuff.AllowedDirection;
import background.fieldStuff.FieldType;
import background.fieldStuff.SteppableField;
import background.fieldStuff.TowerPlace;
import background.fieldStuff.TowerPheripheryField;
import background.fieldStuff.NonSteppableField;
import fri.shapesge.Obrazok;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Trieda Map sa stará o načítanie mapy zo súboru a jej vykreslenie na plátno.
 *
 * @author Tomáš Pytel
 * @version 1
 */
public class Map {
    private final BasicField[][] map;
    private final int height;
    private final int width;
    private final ArrayList<StartField> startFields;
    private final Obrazok mapImage;

    /**
     * Inicializujú sa atribúty, načíta sa mapa na základe textového súboru,
     * popridávajú sa referencie políčkam a zobrazí sa obrázok mapy.
     * @param pathToMap - relatívna cesta k súboru s mapou
     * @param mapImagePath - relatívna cesta k obrázku mapy
     * @throws IOException - vyhadzuje výnimku ak sa textový súbor s mapou nenašiel
     */
    public Map(String pathToMap, String mapImagePath) throws IOException {
        File file = new File(pathToMap);
        Scanner scanner = new Scanner(file);
        this.height = scanner.nextInt();
        this.width = scanner.nextInt();
        String spawnFieldOriantation = scanner.next();
        this.startFields = new ArrayList<>();
        this.map = new BasicField[this.height][this.width];
        scanner.nextLine();
        AllowedDirection allowedDirectionForStartField = this.getAllowedDirectionForStartField(spawnFieldOriantation);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                String pismeno = scanner.next();
                FieldType type = this.decideFieldType(pismeno);
                AllowedDirection allowedDirection = this.decideAllowedDirection(pismeno);
                BasicField field;
                if (type == FieldType.FINISH || type == FieldType.ROAD) {
                    field = new SteppableField(j * BasicField.IMAGE_WIDTH, i * BasicField.IMAGE_WIDTH, type, allowedDirection);
                } else if (type == FieldType.CENTER_OF_TOWER) {
                    field = new TowerPlace(j * BasicField.IMAGE_WIDTH, i * BasicField.IMAGE_WIDTH, type);
                } else if (type == FieldType.START) {
                    StartField startField = new StartField(j * BasicField.IMAGE_WIDTH, i * BasicField.IMAGE_WIDTH, type, allowedDirectionForStartField);
                    this.startFields.add(startField);
                    field = startField;
                } else if (type == FieldType.TOWER_PLACE) {
                    field = new TowerPheripheryField(j * BasicField.IMAGE_WIDTH, i * BasicField.IMAGE_WIDTH, type);
                } else {
                    field = new NonSteppableField(j * BasicField.IMAGE_WIDTH, i * BasicField.IMAGE_WIDTH, type);
                }
                this.map[i][j] = field;
            }
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        scanner.close();
        this.addReferenceToTowerPlace();
        this.mapImage = new Obrazok(mapImagePath, 0, 0);
        this.mapImage.zobraz();
    }
    private AllowedDirection decideAllowedDirection(String pismeno) {
        AllowedDirection allowedDirection = null;
        switch (pismeno) {
            case "N" -> {
                allowedDirection = AllowedDirection.NORTH;
            }
            case "S" -> {
                allowedDirection = AllowedDirection.SOUTH;
            }
            case "E" -> {
                allowedDirection = AllowedDirection.EAST;
            }
            case "W" -> {
                allowedDirection = AllowedDirection.WEST;
            }
            case "L" -> {
                allowedDirection = AllowedDirection.NORTH_WEST;
            }
            case "K" -> {
                allowedDirection = AllowedDirection.NORTH_EAST;
            }
            case "M" -> {
                allowedDirection = AllowedDirection.SOUTH_WEST;
            }
            case "J" -> {
                allowedDirection = AllowedDirection.SOUTH_EAST;
            }
        }
        return allowedDirection;
    }
    private FieldType decideFieldType(String pismeno) {
        FieldType type;
        switch (pismeno) {
            case "O" -> {
                type = FieldType.GRASS;
            }
            case "T" -> {
                type = FieldType.TOWER_PLACE;
            }
            case "F" -> {
                type = FieldType.FINISH;
            }
            case "C" -> {
                type = FieldType.CENTER_OF_TOWER;
            }
            case "R" -> {
                type = FieldType.START;
            }
            default -> {
                type = FieldType.ROAD;
            }
        }
        return type;
    }
    private AllowedDirection getAllowedDirectionForStartField(String spawnFieldOriantation) {
        AllowedDirection allowedDirectionForStartField = null;
        switch (spawnFieldOriantation) {
            case "W" -> {
                allowedDirectionForStartField = AllowedDirection.WEST;
            }
            case "E" -> {
                allowedDirectionForStartField = AllowedDirection.EAST;
            }
            case "S" -> {
                allowedDirectionForStartField = AllowedDirection.SOUTH;
            }
            case "N" -> {
                allowedDirectionForStartField = AllowedDirection.NORTH;
            }
        }
        return allowedDirectionForStartField;
    }
    private void addReferenceToTowerPlace() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.map[i][j] instanceof TowerPlace towerPlace) {
                    for (int k = i - 2; k <= i + 2 ; k++) {
                        for (int l = j - 2; l <= j + 2 ; l++) {
                            if (this.map[k][l] instanceof TowerPheripheryField towerPheripheryField) {
                                towerPheripheryField.setTowerPlace(towerPlace);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Metóda vracia políčko z mapy na základe čísla riadku a stĺpca
     * @param stlpec - číslo riadku
     * @param riadok - číslo stĺpca
     */
    public BasicField getField(int stlpec, int riadok) {
        return this.map[riadok][stlpec];
    }

    /**
     * Metóda vracia zoznam štartovacích políčok ako List
     */
    public List<StartField> getStartFields() {
        return Collections.unmodifiableList(this.startFields);
    }
    /**
     * Metóda vracia dvojrozmerné pole políčok, ktorých pozostáva mapa
     */
    public BasicField[][] getMap() {
        return this.map;
    }
}
