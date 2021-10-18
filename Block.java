import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author Avraham sikirov 318731478.
 * A block class which contains rectangel,.
 * A class gives expression to every inanimate object in the game.
 * There are different methods that return details on the object
 * and a method that draws it on the board
 */


public class Block implements Collidable, Sprite, HitNotifier {
    private static final int NUM_OF_LINES = 4;
    private Rectangle rec;
    private List<HitListener> hitListeners;

    /**
     * @param rec consturctor.
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        hitListeners = new ArrayList<>();
    }

    /**
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * @param collisionPoint  the hit point.
     * @param currentVelocity current velocity.
     * @param hitter          hitter ball.
     * @return new velocity after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] arr = new Line[NUM_OF_LINES];
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        rec.linesOfRectangle(arr);

        if (arr[0].isPointOnLine(collisionPoint) || arr[1].isPointOnLine(collisionPoint)) {
            v.setDy(-(currentVelocity.getDy()));
        }
        if (arr[2].isPointOnLine(collisionPoint) || arr[3].isPointOnLine(collisionPoint)) {
            v.setDx(-(currentVelocity.getDx()));
        }
        this.notifyHit(hitter);

        //if (this.rec.getWidth() < 100 && this.rec.getHeight() < 100) {
        //     this.rec.setUpperLeft(new gui.geometry.Point(-100, -100));
        //}
        return v;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface surface which the ball will be draw on.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rec.getColor());
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * time passed.
     */
    public void timePassed() {
    }

    /**
     * adding the block to a game.
     *
     * @param g the game the block should be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addSprite(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing the block to a game.
     *
     * @param game the game the block should be added to.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notify to listeners that there was a hit.
     *
     * @param hitter hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}