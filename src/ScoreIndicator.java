import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Score indicator which represents the score in the game.
 */

public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * Constructor.
     *
     * @param scoreCounter score counter.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;

    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillRectangle(0, 0, Zone.WIDTH, 30);
        surface.setColor(Color.black);
        surface.drawRectangle(0, 0, Zone.WIDTH, 30);
        surface.drawText(Zone.WIDTH * 7 / 16, 23, "Score: " + this.scoreCounter.getValue(), 18);
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