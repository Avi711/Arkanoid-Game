import biuoop.DrawSurface;
import java.util.List;


/**
 * Level information interface. Contains all level settings.
 */
public interface LevelInformation {

    /**
     * @return Number of balls.
     */

    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     *
     * @return list of the ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * This number should be <= blocks.size();.
     *
     * @return number of block should be removed before the level finished.
     */
    int numberOfBlocksToRemove();

    /**
     * @param d surface to draw on.
     */
    void backgroundDrawOn(DrawSurface d);
}


