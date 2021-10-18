import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Level 2.
 */
public class Level2 implements LevelInformation {
    private static final int BLOCK_WIDTH = Zone.WIDTH / 16;
    private static final int BLOCK_HEIGHT = Zone.HEIGHT / 20;
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

    public Level2() {
        this.numOfBalls = 10;
        this.paddleSpeed = 5;
        this.paddleWidth = 400;
        this.numOfBlocksToRemove = Zone.WIDTH / BLOCK_WIDTH;
        this.levelName = "Level2";
        Rectangle rec = new Rectangle(new Point(0, 0), Zone.WIDTH, Zone.HEIGHT, Color.white);
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
        velocities.add(Velocity.fromAngleAndSpeed(-50, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-10, 5));
        velocities.add(Velocity.fromAngleAndSpeed(10, 5));
        velocities.add(Velocity.fromAngleAndSpeed(20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(50, 5));
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
        d.setColor(new Color(123, 95, 80));
        d.fillRectangle(80, 500, 50, 100);
        d.setColor(new Color(29, 102, 0));
        d.fillOval(65, 450, 80, 100);
        d.setColor(new Color(255, 0, 0));
        d.fillCircle(80, 470, 5);
        d.fillCircle(100, 475, 5);
        d.fillCircle(95, 520, 5);
        d.fillCircle(120, 500, 5);


        d.setColor(new Color(255, 243, 0));
        for (int i = 0; i < Zone.WIDTH; i = i + 10) {
            d.drawLine(100, 100, i, 219);
        }

        d.setColor(new Color(255, 230, 130));
        d.fillCircle(100, 100, 60);
        d.setColor(new Color(255, 224, 27));
        d.fillCircle(100, 100, 50);
        d.setColor(new Color(255, 243, 0));
        d.fillCircle(100, 100, 40);

    }

    /**
     * Creating blocks and adding them to the list.
     */
    public void createBlocks() {
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        int x = Zone.WIDTH - BLOCK_WIDTH;
        for (int j = 0; j <= numOfBlocksToRemove; ++j) {
            if (j % 2 == 0) {
                red = rand.nextFloat();
                green = rand.nextFloat();
                blue = rand.nextFloat();
            }
            Rectangle rec = new Rectangle(new Point(x, 220), BLOCK_WIDTH, BLOCK_HEIGHT);
            rec.setColor(new Color(red, green, blue));
            Block b = new Block(rec);
            blocks.add(b);
            x = x - 50;
        }
    }
}
