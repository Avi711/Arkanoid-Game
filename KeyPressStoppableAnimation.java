import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * In charge of the waiting-for-key-press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor    keyboard sensor.
     * @param key       key.
     * @param animation animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
