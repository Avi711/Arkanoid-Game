import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Ending screen.
 */

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private Counter lives;

    /**
     * Constructor.
     * @param k keyboard sensor.
     * @param score score counter.
     * @param lives lives counter.
     */

    public EndScreen(KeyboardSensor k, Counter score, Counter lives) {
        this.keyboard = k;
        this.stop = false;
        this.lives = lives;
        this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {

        if (lives.getValue() == 0) {
            d.setColor(Color.red);
            d.drawText(d.getWidth() / 4, d.getHeight() / 3, "You Lost", 80);
            d.setColor(Color.black);
            d.drawText(d.getWidth() / 4 + 4, d.getHeight() / 3 + 4, "You Lost", 80);
        } else {
            d.setColor(Color.green);
            d.drawText(d.getWidth() / 4, d.getHeight() / 3, "You Win", 80);
            d.setColor(Color.black);
            d.drawText(d.getWidth() / 4 + 5, d.getHeight() / 3 + 5, "You Win", 80);
        }

        d.setColor(Color.black);
        d.drawText(d.getWidth() / 4 + 5, (d.getHeight() / 3) * 2, "Final score:" + this.score.getValue(), 20);

        d.setColor(Color.blue);
        d.drawText(d.getWidth() / 4, (d.getHeight() / 4) * 3, "Press space to continue", 30);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 4 + 2, (d.getHeight() / 4) * 3 + 2, "Press space to continue", 30);


        //  if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}