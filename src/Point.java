
/**
 * @author Avraham sikirov 318731478.
 * Class represent one point.
 */

public class Point {
    private static final double EPSILON = Math.pow(10, -2);
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other other point to calculate distance from this point.
     * @return distance.
     */
    //
    public double distance(Point other) {
        double add = (other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y);
        return Math.sqrt(add);
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other other point to check if equals to this point.
     * @return true if equals and false otherwise.
     */
    public boolean equals(Point other) {
        if (Math.abs(this.x - other.getX()) <= EPSILON && Math.abs(this.y - other.getY()) <= EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y coordinate.
     */
    public double getY() {
        return y;
    }
}