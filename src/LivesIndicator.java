import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Lives indicator of each game.
 */
public class LivesIndicator implements Sprite {
    private Counter counter;


    /**
     * Constructor.
     *
     * @param count counter of the lives.
     */
    public LivesIndicator(Counter count) {
        this.counter = count;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(50, 23, "Lives: " + this.counter.getValue(), 18);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void addSprite(GameLevel g) {
    }
}
