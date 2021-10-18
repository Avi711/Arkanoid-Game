
/**
 * @author Avraham sikirov 318731478.
 * interface which represents every collidble object in the game, blcok and puddle.
 */

public interface Collidable {


    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  collision gui.geometry.Point.
     * @param currentVelocity collision velocity.
     * @param hitter          hitter ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}