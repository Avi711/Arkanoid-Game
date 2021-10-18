
/**
 * @author Avraham sikirov 318731478.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx the x coordinate of the point.
     * @param dy the y coordinate of the point.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Method which convert angle and speed to dx and dy velocity.
     *
     * @param angle of the velocity.
     * @param speed of the velocity.
     * @return velocity after applying angle and speed.
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double a = Math.toRadians(angle);
        double dx = Math.sin(a) * speed;
        double dy = -Math.cos(a) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * .
     *
     * @return the dx velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param newDx the new dx to set
     *              setting the dx velocity.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param newDy the new dy to set
     *              setting the dy coordinate.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p apply velocity on this point.
     * @return the new point after moving the ball.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}