import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;
import java.util.LinkedList;

/**
 * @author Avraham sikirov 318731478.
 * Main game.
 */
public class Ass6Game {
    /**
     * initialize and run the game.
     *
     * @param args args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Zone.WIDTH, Zone.HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, 60);
        List<LevelInformation> levelList = new LinkedList<LevelInformation>();

        if (args.length != 0) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i].toString().equals("1")) {
                    levelList.add(new Level1());
                }
                if (args[i].toString().equals("2")) {
                    levelList.add(new Level2());
                }
                if (args[i].toString().equals("3")) {
                    levelList.add(new Level3());
                }
                if (args[i].toString().equals("4")) {
                    levelList.add(new Level4());
                }
            }
        } else {

            levelList.add(new Level1());
            levelList.add(new Level2());
            levelList.add(new Level3());
            levelList.add(new Level4());
        }
        GameFlow game = new GameFlow(runner, keyboardSensor, gui);
        game.runLevels(levelList);
    }
}
