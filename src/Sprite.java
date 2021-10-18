import biuoop.DrawSurface;

/**
 * .
 *
 * @author Avraham sikirov 318731478.
 * interface which represent all objects in the game.
 */

public interface Sprite {
    /**
     * .
     *
     * @param d draw the sprite to the screen.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * @param g add to game.
     */
    void addToGame(GameLevel g);


    /**
     * adding block to the game as a sprite.
     *
     * @param g the game the block should be added to as a sprite.
     */
    void addSprite(GameLevel g);
}
