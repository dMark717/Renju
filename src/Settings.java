import java.io.*;

/**
 * Settings
 * Ez az osztály tartalmazza a beállításokat
 */

public class Settings {
    // Difficulty levels
    // These are used like macros
    private static final int EASY = 1;
    private static final int MEDIUM = 10;
    private static final int HARD = 100;

    // Settings
    public static int difficulty = EASY;
    public static boolean multiPlayer = false;
    public static boolean gamemodeIsRenju = true;
    public static boolean stepBacksEnabled = true;
    
    //This is needed to prevent the settings from being loaded each time MainMenuGUI is called
    public static boolean settingsAreLoaded = false;

    // Convert the settings to strings

    /**
     * Nehézség szöveggé alakítása
     * @return A nehézség szöveggé alakítva
     */
    public static String difficultyToString() {
        if (difficulty == EASY) {
            return "Könnyű";
        } else if (difficulty == MEDIUM) {
            return "Közepes";
        } else if (difficulty == HARD) {
            return "Nehéz";
        }
        return "";
    }

    /**
     * Játék típusának szöveggé alakítása
     * @return A játék típusa szöveggé alakítva
     */

    public static String gameTypeToString() {
        if (gamemodeIsRenju) {
            return "Be";
        } else {
            return "Ki";
        }
    }

    /**
     * Játék módjának szöveggé alakítása
     * @return A játék módja szöveggé alakítva
     */

    public static String gameModeToString() {
        if (multiPlayer) {
            return "Többjátékos";
        } else {
            return "Egyjátékos";
        }
    }

    /**
     * Visszalépések szöveggé alakítása
     * @return A visszalépések szöveggé alakítva
     */

    public static String stepBacksToString() {
        if (stepBacksEnabled) {
            return "Be";
        } else {
            return "Ki";
        }
    }

    //Cycle the settings

    /**
     * Nehézség módosítása
     */
    public static void changeDifficulty() {
        if (difficulty == EASY) {
            difficulty = MEDIUM;
        } else if (difficulty == MEDIUM) {
            difficulty = HARD;
        } else if (difficulty == HARD) {
            difficulty = EASY;
        }
    }

    /**
     * Játék módjának módosítása
     */
    public static void changeGameMode() {
        if (multiPlayer) {
            multiPlayer = false;
        } else {
            multiPlayer = true;
        }
    }

    /**
     * Játék típusának módosítása
     */
    public static void changeGameType() {
        if (gamemodeIsRenju) {
            gamemodeIsRenju = false;
        } else {
            gamemodeIsRenju = true;
        }
    }

    /**
     * Visszalépések módosítása
     */
    public static void changeStepBacks() {
        if (stepBacksEnabled) {
            stepBacksEnabled = false;
        } else {
            stepBacksEnabled = true;
        }
    }

    // Save the settings to settings.txt

    /**
     * Beállítások mentése settings.txt-be
     */
    public static void saveSettings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("settings.txt"))) {
            writer.println("Difficulty=" + difficulty);
            writer.println("MultiPlayer=" + multiPlayer);
            writer.println("GameModeIsRenju=" + gamemodeIsRenju);
            writer.println("StepBacksEnabled=" + stepBacksEnabled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the settings from settings.txt

    /**
     * Beállítások betöltése settings.txt-ből
     */
    public static void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader("settings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts[0].equals("Difficulty")) {
                    difficulty = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("MultiPlayer")) {
                    multiPlayer = Boolean.parseBoolean(parts[1]);
                } else if (parts[0].equals("GameModeIsRenju")) {
                    gamemodeIsRenju = Boolean.parseBoolean(parts[1]);
                } else if (parts[0].equals("StepBacksEnabled")) {
                    stepBacksEnabled = Boolean.parseBoolean(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

