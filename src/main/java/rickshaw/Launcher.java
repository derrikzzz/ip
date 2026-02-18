package rickshaw;

/**
 * A launcher class to workaround classpath issues.
 * This is needed for JavaFX to work properly when packaged as a fat JAR.
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
