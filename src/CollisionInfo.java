
/**
 * @author Avraham sikirov 318731478
 * A class responsible for holding collision information
 * Information for the point of collision,
 * for the object being collided with.
 * And the distance from the current point to the object.
 */


public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObj;
    private Double distance;


    /**
     * Constructor.
     *
     * @param collisionPoint first point to create a line.
     * @param collisionObj   second point to create a line.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObj) {
        this.collisionPoint = collisionPoint;
        this.collisionObj = collisionObj;
    }

    /**
     * Constructor.
     *
     * @param collisionPoint first point to create a line.
     * @param collisionObj   second point to create a line.
     * @param distance       distance between point to the colliison point.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObj, Double distance) {
        this.collisionPoint = collisionPoint;
        this.collisionObj = collisionObj;
        this.distance = distance;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObj;
    }

    /**
     * @return the distance from the start line.
     */
    public Double getDistance() {
        return distance;
    }
}
