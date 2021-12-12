import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Avraham sikirov 318731478
 * A class responsible for creating a game, innitialize the ball,
 * the object, the paddle and the interface.
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation info;
    private Paddle pud;


    /**
     * Constructor, initialize the sprites, and the game environment.
     * @param information lever information.
     * @param ks keyboard sensor.
     * @param ar animation runnner.
     * @param score score counter.
     * @param lives lives counter.
     */

    public GameLevel(LevelInformation information, KeyboardSensor ks, AnimationRunner ar,
                     Counter score, Counter lives) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        this.score = score;
        this.lives = lives;
        this.info = information;
        this.runner = ar;
        this.keyboard = ks;
    }

    @Override
    public boolean shouldStop() {

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "p", new PauseScreen(this.keyboard)));
            //this.runner.run(new PauseScreen(this.keyboard));
        }
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        info.backgroundDrawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.ballCounter.getValue() < 1) {
            this.running = false;
        }
        if (this.blockCounter.getValue() < 1) {
            score.increase(100);
            this.running = false;
        }
    }

    /**
     * @param c a collidable should added to the environment
     */

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s a collidable should added to the environment
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * @param c a collidable should removed from the environment
     */

    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @param s a collidable should removed from the environment
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * @return ball counter.
     */

    public Counter getBallCounter() {
        return this.ballCounter;
    }

    /**
     * @return block counter.
     */

    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // info.getBackground().addSprite(this);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(info.levelName());
        levelIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(lives);
        livesIndicator.addToGame(this);

        float paddleX = (float) Zone.WIDTH / 2 - (float) info.paddleWidth() / 2;
        //creating balls.
        initializeBall();


        // creating tha pad
        Point pad = new Point(paddleX, Zone.HEIGHT - 15);
        this.pud = new Paddle(new Rectangle(pad, info.paddleWidth(), 5, Color.ORANGE),
                keyboard, info.paddleSpeed());
        this.pud.addToGame(this);

        //creating the border
        createBorder(ballRemover);

        // creating the blocks.
        for (Block block : info.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrack);
            block.addToGame(this);
        }
        blockCounter.increase(info.numberOfBlocksToRemove());
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, sprites, info));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * continue the game -- start the animation loop.
     */
    public void continueGame() {
        initializeBall();
        this.pud.moveMiddle(Zone.WIDTH);
    }


    /**
     * Initialize ball in the game, information from game info.
     */
    public void initializeBall() {

        ballCounter.increase(info.numberOfBalls());
        float part = (float) info.paddleWidth();
        float paddleX = (float) Zone.WIDTH / 2 - (float) info.paddleWidth() / 2;
        //creating balls.
        int p = 1;
        for (Velocity velocity : info.initialBallVelocities()) {
            Point ballCenter = new Point(paddleX + part * (p) - part / 2, Zone.HEIGHT - 25);
            Ball ball = new Ball(ballCenter, 5, Color.white);
            ball.setEnvironment(environment);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            // ++p;
        }
    }

    /**
     * Creating the border.
     *
     * @param ballRemover ball remover (for the block which removes ball when they fall)
     */
    public void createBorder(BallRemover ballRemover) {

        // creating the border.
        Point p1 = new Point(0, 30);
        Rectangle rec1 = new Rectangle(p1, Zone.WIDTH, 2, Color.gray);
        Point p2 = new Point(-2, 0);
        Rectangle rec2 = new Rectangle(p2, 2, Zone.HEIGHT, Color.gray);
        Point p3 = new Point(Zone.WIDTH, 0);
        Rectangle rec3 = new Rectangle(p3, 2, Zone.HEIGHT, Color.gray);
        Point p4 = new Point(0, Zone.HEIGHT + 5);
        Rectangle rec4 = new Rectangle(p4, Zone.WIDTH, 2, Color.gray);
        Block b1 = new Block(rec1);
        Block b2 = new Block(rec2);
        Block b3 = new Block(rec3);
        Block b4 = new Block(rec4);
        //adding the border to the game.
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
        b4.addHitListener(ballRemover);
    }
}