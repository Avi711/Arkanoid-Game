import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Level 1.
 */

public class Level1 implements LevelInformation {
    private static final int BLOCK_WIDTH = Zone.WIDTH / 20;
    private static final int BLOCK_HEIGHT = BLOCK_WIDTH;

    private int numOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numOfBlocksToRemove;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;


    /**
     * Constructor.
     */
    public Level1() {
        this.numOfBalls = 1;
        this.paddleSpeed = 5;
        this.paddleWidth = 120;
        this.numOfBlocksToRemove = 1;
        this.levelName = "Level1";
        Rectangle rec = new Rectangle(new Point(0, 0), Zone.WIDTH, Zone.HEIGHT, Color.black);
        background = new Block(rec);
        blocks = new LinkedList<Block>();
        createBlocks();
    }


    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(0, -5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocksToRemove;
    }

    @Override
    public void backgroundDrawOn(DrawSurface d) {
        background.drawOn(d);
        d.setColor(Color.blue);
        d.drawLine(230, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_HEIGHT / 2, 370,
                (int) (Zone.HEIGHT * 2 / 6) + BLOCK_HEIGHT / 2);
        d.drawLine(430, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_HEIGHT / 2, 570,
                (int) (Zone.HEIGHT * 2 / 6) + BLOCK_HEIGHT / 2);
        d.drawLine(Zone.WIDTH / 2, 50, Zone.WIDTH / 2, 190);
        d.drawLine(Zone.WIDTH / 2, 250, Zone.WIDTH / 2, 390);
        d.drawCircle(Zone.WIDTH / 2, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_WIDTH / 2, 50);
        d.drawCircle(Zone.WIDTH / 2, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_WIDTH / 2, 90);
        d.drawCircle(Zone.WIDTH / 2, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_WIDTH / 2, 130);
        d.drawCircle(Zone.WIDTH / 2, (int) (Zone.HEIGHT * 2 / 6) + BLOCK_WIDTH / 2, 170);
    }

    /**
     * Creating blocks and adding them to the list.
     */
    public void createBlocks() {
        Point p = new Point((int) (Zone.WIDTH / 2) - (int) (BLOCK_WIDTH / 2), (int) (Zone.HEIGHT * 2 / 6));
        Rectangle rec1 = new Rectangle(p, BLOCK_WIDTH, BLOCK_HEIGHT, Color.red);
        Block b = new Block(rec1);
        blocks.add(b);
    }
}
