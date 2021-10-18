import biuoop.DrawSurface;
import java.awt.Color;


/**
 * Level indicator on top of the screen.
 */
public class LevelIndicator implements Sprite {
    private String levelName;


    /**
     * Constructor.
     *
     * @param name Level name.
     */

    public LevelIndicator(String name) {
        this.levelName = name;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(Zone.WIDTH * 4 / 5, 23, "Level name: " + this.levelName, 18);

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
