import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Avraham sikirov 318731478.
 * A department responsible for holding all the sprites in the game.
 */

public class SpriteCollection {
    private List<Sprite> sprites;


    /**
     * Constructor.
     */

    public SpriteCollection() {
        sprites = new LinkedList<>();
    }

    /**
     * @param s sprite to add to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * @param s sprite to remove from the sprite collection.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spp = new LinkedList<Sprite>(this.sprites);


        for (Sprite sprite : spp) {
            sprite.timePassed();
        }
    }

    /**
     * @param d call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {

        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}