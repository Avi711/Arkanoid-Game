import biuoop.DrawSurface;

/**
 * Animation represent pages and animation in the game.
 */

public interface Animation {
    /**
     * move the game one frame.
     *
     * @param d surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true we game paused.
     */
    boolean shouldStop();
}