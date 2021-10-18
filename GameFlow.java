import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Game flow in charge of the flow of the game, change levels and end screen.
 */

public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;

    /**
     * Constructor.
     *
     * @param ar  Animation runner
     * @param ks  Keyboard sensor
     * @param gui Gui.
     */

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;

    }

    /**
     * In charge of changing levels and end screens.
     *
     * @param levels lists of levels in the game.
     */

    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        Counter lives = new Counter();
        lives.increase(3);
        int i = levels.size();
        for (LevelInformation levelInfo : levels) {
            --i;
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives);
            level.initialize();

            while (lives.getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.run();
                if (level.getBallCounter().getValue() < 1) {
                    lives.decrease(1);
                    level.continueGame();
                }
            }
            if (lives.getValue() == 0 || (level.getBlockCounter().getValue() == 0 && i == 0)) {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "p",
                        new EndScreen(keyboardSensor, score, lives)));
                //  animationRunner.run(new EndScreen(keyboardSensor, score, lives));
                gui.close();
                break;
            }
            if (level.getBallCounter().getValue() == 0) {
                break;
            }

        }
    }
}
