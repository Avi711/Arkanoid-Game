/**
 * Counter class.
 */

public class Counter {
    private int counter;

    /**
     * Constructor.
     */

    public Counter() {
    }

    /**
     * add to current count.
     *
     * @param number number to increase.
     */
    void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract from current count.
     *
     * @param number number to decrease.
     */
    void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * @return counter.
     */
    int getValue() {
        return this.counter;
    }
}