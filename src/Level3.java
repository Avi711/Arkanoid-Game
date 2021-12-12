import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Level 3.
 */
public class Level3 implements LevelInformation {
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

    public Level3() {
        this.numOfBalls = 2;
        this.paddleSpeed = 10;
        this.paddleWidth = 120;
        this.numOfBlocksToRemove = 57;
        this.levelName = "Level3";
        Rectangle rec = new Rectangle(new Point(0, 0), Zone.WIDTH, Zone.HEIGHT, new Color(0, 150, 0));
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
        velocities.add(Velocity.fromAngleAndSpeed(-30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
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
        d.setColor(new Color(29, 29, 29));
        d.fillRectangle(80, 400, 110, 200);

        d.setColor(new Color(68, 68, 68));
        d.fillRectangle(120, 340, 30, 60);

        d.setColor(new Color(102, 102, 102));
        d.fillRectangle(128, 160, 14, 180);

        d.setColor(new Color(255, 222, 94));
        d.fillCircle(134, 160, 18);
        d.setColor(new Color(255, 71, 71));
        d.fillCircle(134, 160, 13);
        d.setColor(new Color(255, 255, 255));
        d.fillCircle(134, 160, 5);


        d.setColor(new Color(255, 255, 255));
        for (int i = 410; i < 600; i = i + 40) {
            for (int j = 90; j < 185; j = j + 25) {
                d.fillRectangle(j, i, 15, 30);
            }
        }


    }

    /**
     * Creating blocks and adding them to the list.
     */
    public void createBlocks() {
        double numInRow = 12, numOfcolloms = 6;
        int x, y;
        y = 100;
        for (int i = 0; i < numOfcolloms; i = i + 1) {
            Random rand = new Random();
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            x = 750;
            for (int j = 0; j < numInRow; j = j + 1) {
                Rectangle rec = new Rectangle(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT);
                rec.setColor(new Color(red, green, blue));
                Block b = new Block(rec);
                blocks.add(b);
                x = x - 50;
            }
            --numInRow;
            y = y + 30;
        }
    }
}
