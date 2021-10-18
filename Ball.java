import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;

/**
 * @author Avraham sikirov 318731478.
 * A ball class which contains his middle point, radius, color and velocity.
 * There are different methods for setting and getting every field, and for moving
 * the ball on specific surface as well as drawing the ball.
 */

public class Ball implements Sprite {
    private static final int SIZE_OF_SCREEN = 500;
    private static final double EPSILON = Math.pow(10, -10);
    private int frameStart = 0;
    private int frameEnd = SIZE_OF_SCREEN;
    private Point p;
    private Velocity v;
    private int r;
    private final Color color;
    private GameEnvironment environment;

    /**
     * Constructor.
     *
     * @param center of the ball.
     * @param r      radius of the ball.
     * @param color  color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.p = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor (without color) will randomize color.
     *
     * @param center of the ball.
     * @param r      radius of the ball.
     */
    public Ball(Point center, int r) {
        this.p = center;
        this.r = r;
        // randomize color.
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }

    /**
     * Constructor.
     *
     * @param x     coordinate of the middle of the ball.
     * @param y     coordinate of the middle of the ball.
     * @param r     radius of the ball.
     * @param color color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        p = new Point(x, y);
        this.r = r;
        this.color = color;

    }

    /**
     * accessors.
     *
     * @return x coordinate of the middle of the ball.
     */
    public int getX() {
        return (int) p.getX();
    }

    /**
     * accessors.
     *
     * @return y coordinate of the middle of the ball.
     */
    public int getY() {
        return (int) p.getY();
    }

    /**
     * accessors.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return (int) this.r;
    }

    /**
     * accessors.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessors.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Set the velocity of the ball with velocity.
     *
     * @param vel the velocity to change to
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * Set the environment of the game the ball in.
     *
     * @param environmentG the environment to change to
     */
    public void setEnvironment(GameEnvironment environmentG) {
        this.environment = environmentG;
    }


    /**
     * Set the frame which the ball will move in.
     *
     * @param start the starting coordinate of the frame.
     * @param end   the ending coordinate of the frame.
     */
    public void setFrame(int start, int end) {
        this.frameStart = start;
        this.frameEnd = end;
    }

    /**
     * Set the velocity of the ball with dx and dy.
     *
     * @param dx the dx velocity.
     * @param dy the dy velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Moving the ball one step according to velocity.
     * while keeping it in the frame zone.
     */
    public void moveOneStep() {
        if (v == null) {
            return;
        }
        Velocity tempV;
        Line trajectory = new Line(this.p.getX(), this.p.getY(), this.p.getX()
                + v.getDx(), this.p.getY() + v.getDy());
        CollisionInfo info = environment.getClosestCollision(trajectory);

        if (info == null) {
            this.p = this.getVelocity().applyToPoint(p);
        } else {
            // in case info is not null and there is a collision.
            Line collisionLine = new Line(this.p.getX(), this.p.getY(), info.collisionPoint().getX(),
                    info.collisionPoint().getY());
            double ratio = 0.999 * (collisionLine.length() / trajectory.length());
            // setting the new point to slightly before collision.
            this.p = new Point(this.p.getX() + (this.v.getDx() * ratio), this.p.getY()
                    + (this.v.getDy() * ratio));
            Point colissionP = info.collisionPoint();
            tempV = info.collisionObject().hit(this, colissionP, this.v);
            //info.collisionObject().getCollisionRectangle().setColor(Color.RED);
            setVelocity(tempV.getDx(), tempV.getDy());
        }
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface surface which the ball will be draw on.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle((int) p.getX(), (int) p.getY(), r);
        surface.setColor(this.color);
        surface.fillCircle((int) p.getX(), (int) p.getY(), r);
    }

    /**
     * Time Passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param g the game we want to add the ball to.
     *          calling the addSprite method for this.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void addSprite(GameLevel g) {
    }


    /**
     * Remove ball from game.
     *
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}