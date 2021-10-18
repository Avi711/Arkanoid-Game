import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Level 4.
 */

public class Level4 implements LevelInformation {
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
    public Level4() {
        this.numOfBalls = 3;
        this.paddleSpeed = 10;
        this.paddleWidth = 120;
        this.numOfBlocksToRemove = 112;
        this.levelName = "Level4";
        Rectangle rec = new Rectangle(new Point(0, 0), Zone.WIDTH, Zone.HEIGHT, new Color(0, 206, 252));
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
        velocities.add(Velocity.fromAngleAndSpeed(-20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(20, 5));
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
        //  Point p = new Point(390,200);
        //   Rectangle rec1 = new Rectangle(p, 50,30,Color.black);
        //   Block b = new Block(rec1);
        //     blocks.add(b);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocksToRemove;
    }


    @Override
    public void backgroundDrawOn(DrawSurface d) {
        background.drawOn(d);
        d.setColor(new Color(239, 239, 239));
        for (int i = 520; i < 630; i = i + 10) {
            d.drawLine(i, 490, i + 10, 600);
        }

        d.setColor(new Color(161, 152, 152));
        d.fillCircle(600, 500, 30);
        d.setColor(new Color(167, 154, 154));
        d.fillCircle(570, 510, 30);
        d.setColor(new Color(156, 148, 148));
        d.fillCircle(570, 480, 30);
        d.setColor(new Color(141, 128, 128));
        d.fillCircle(540, 480, 30);


        d.setColor(new Color(239, 239, 239));
        for (int i = 120; i < 220; i = i + 6) {
            d.drawLine(i, 430, i + 20, 600);
        }

        d.setColor(new Color(161, 152, 152));
        d.fillCircle(200, 450, 35);
        d.setColor(new Color(167, 154, 154));
        d.fillCircle(170, 460, 35);
        d.setColor(new Color(156, 148, 148));
        d.fillCircle(170, 430, 35);
        d.setColor(new Color(167, 154, 154));
        d.fillCircle(140, 430, 35);


    }

    /**
     * Creating blocks and adding them to the list.
     */
    public void createBlocks() {
        double numInRow = 16, numOfcolloms = 7;
        int x, y;
        y = 100;
        for (int i = 0; i < numOfcolloms; i = i + 1) {
            Random rand = new Random();
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            x = Zone.WIDTH - BLOCK_WIDTH;
            for (int j = 0; j < numInRow; j = j + 1) {
                Rectangle rec = new Rectangle(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT);
                rec.setColor(new Color(red, green, blue));
                Block b = new Block(rec);
                blocks.add(b);
                x = x - BLOCK_WIDTH;
            }
            y = y + BLOCK_HEIGHT;
        }
    }
}
