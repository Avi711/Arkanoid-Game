import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation runner in charge of every frame.
 */

public class AnimationRunner implements Animation {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     *
     * @param gui    gui
     * @param frames frames per second.
     */

    public AnimationRunner(GUI gui, int frames) {
        this.gui = gui;
        this.framesPerSecond = frames;
    }

    /**
     * Loop which runs the animation.
     *
     * @param animation animation
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            Sleeper sleeper = new Sleeper();
            int millisecondsPerFrame = 1000 / framesPerSecond;
            while (!animation.shouldStop()) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();
                animation.doOneFrame(d);
                gui.show(d);
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}