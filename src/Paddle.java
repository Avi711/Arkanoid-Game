import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Avraham sikirov 318731478.
 * A paddle class which is in charge of the paddle in the game.
 * There are methods in the class that are responsible for moving the paddle according to the keystrokes,
 * which are responsible for calculating the collision points with the ball, and adding the paddle to the game.
 */

public class Paddle implements Sprite, Collidable {
    private static final int NUM_OF_LINES = 4;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Color color;
    private int speed;

    /**
     * Constuctor.
     *
     * @param rec      of the paddle.
     * @param keyboard keyboard sensor.
     */
    public Paddle(Rectangle rec, KeyboardSensor keyboard) {
        this.rec = rec;
        this.keyboard = keyboard;
        this.color = this.rec.getColor();
        this.speed = 5;
    }

    /**
     * Constuctor.
     *
     * @param rec      of the paddle.
     * @param keyboard keyboard sensor.
     * @param speed paddle speed.
     */
    public Paddle(Rectangle rec, KeyboardSensor keyboard, int speed) {
        this.rec = rec;
        this.keyboard = keyboard;
        this.color = this.rec.getColor();
        this.speed = speed;
    }

    /**
     * Move left the paddle.
     */
    public void moveLeft() {
        Point p = new Point(this.rec.getUpperLeft().getX() - speed, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
    }

    /**
     * Move right the paddle.
     */
    public void moveRight() {
        Point p = new Point(this.rec.getUpperLeft().getX() + speed, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight());
    }

    /**
     * Move paddle to middle of screen.
     * @param width paddle width.
     */
    public void moveMiddle(int width) {
        float paddleX = (float) width / 2 - (float) this.rec.getWidth() / 2;
        Point p = new Point(paddleX, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight(), this.color);
    }

    /**
     * Sprite.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) || keyboard.isPressed("a")) {
            if (this.rec.getUpperLeft().getX() > 0) {
                moveLeft();
            }
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) || keyboard.isPressed("d")) {
            if (this.rec.getUpperLeft().getX() + this.rec.getWidth() < Zone.WIDTH) {
                moveRight();
            }
        }
    }

    /**
     * @param d drawing the paddle on d.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * @return the collidable object (paddle in this case)
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Calculate the velocity after a collision with the paddle.
     *
     * @param collisionPoint  of the ball with the paddle.
     * @param currentVelocity of the ball.
     * @param hitter          hitter ball.
     * @return the new velocity after hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] arr = new Line[NUM_OF_LINES];
        rec.linesOfRectangle(arr);
        double part = this.rec.getWidth() / 5;
        double newSpeed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());
        Point upper = this.rec.getUpperLeft();
        Line region1 = new Line(upper.getX(), upper.getY(), upper.getX() + part, upper.getY());
        Line region2 = new Line(upper.getX() + part, upper.getY(), upper.getX() + 2 * part, upper.getY());
        Line region3 = new Line(upper.getX() + 2 * part, upper.getY(), upper.getX() + 3 * part, upper.getY());
        Line region4 = new Line(upper.getX() + 3 * part, upper.getY(), upper.getX() + 4 * part, upper.getY());
        Line region5 = new Line(upper.getX() + 4 * part, upper.getY(), upper.getX() + 5 * part, upper.getY());
        if (region1.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, newSpeed);
        }
        if (region2.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, newSpeed);
        }
        if (region3.isPointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (region4.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, newSpeed);

        }
        if (region5.isPointOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, newSpeed);
        }

        if (arr[2].isPointOnLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (arr[3].isPointOnLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * @param g the game which the paddle should be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addSprite(GameLevel g) {
    }
}