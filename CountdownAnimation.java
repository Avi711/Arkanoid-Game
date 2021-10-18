import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Count down animation in charge of the count down before every leve.
 */

public class CountdownAnimation implements Animation {
    private double second;
    private int count;
    private SpriteCollection screen;
    private boolean stop;
    private double start;
    private LevelInformation info;

    /**
     * Constructor.
     *
     * @param numOfSeconds seconds.
     * @param countFrom    counting form.
     * @param gameScreen   all sprites.
     * @param info         info of the level.
     */

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              LevelInformation info) {
        this.second = numOfSeconds;
        this.count = countFrom;
        this.screen = gameScreen;
        this.stop = false;
        this.start = second;
        this.info = info;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        info.backgroundDrawOn(d);
        screen.drawAllOn(d);
        if (count > 0) {
            d.setColor(Color.red);
            d.drawText(380, d.getHeight() / 2, String.valueOf(count), 80);
        }
        if (count == 0) {
            d.setColor(Color.green);
            d.drawText(380, d.getHeight() / 2, "Go!", 80);
        }
        if (start != second) {
            sleeper.sleepFor(600);
        }
        if (count < 0) {
            this.stop = true;
        }
        second = second - 1;
        count = count - 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
