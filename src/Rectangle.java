
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;

/**
 * @author Avraham sikirov 318731478.
 * Class represent rectangle.
 */

public class Rectangle {
    private static final int NUM_OF_LINES = 4;
    private double width;
    private double height;
    private Color color;
    private Point upperLeft;


    /**
     * Create a new rectangle with location and width/height and color.
     *
     * @param upperLeft upperleft point.
     * @param width     width of the rectangle.
     * @param height    heightof the rectangle.
     * @param color     color of the rectangle.
     */

    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.color = color;
    }

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft upperleft point
     * @param width     width of the rectangle
     * @param height    heightof the rectangle
     */

    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;

        // randomize color.
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }

    /**
     * @param line line to check if intersect with.
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] arr = new Line[NUM_OF_LINES];
        this.linesOfRectangle(arr);
        List<Point> intersectionPoints = new ArrayList<>();
        for (int i = 0; i < NUM_OF_LINES; ++i) {

            Point temp = line.intersectionWith(arr[i]);
            if (temp != null) {
                intersectionPoints.add(temp);
            }
        }
        return intersectionPoints;
    }

    /**
     * @param arr array to assign in the lines of the rectangle.
     */
    public void linesOfRectangle(Line[] arr) {
       /* gui.geometry.Point upLeft = upperLeft;
        gui.geometry.Point upRight = new gui.geometry.Point(upperLeft.getX() + width, upperLeft.getY());
        gui.geometry.Point downLeft = new gui.geometry.Point(upperLeft.getX(), upperLeft.getY() + height);
        gui.geometry.Point downRight = new gui.geometry.Point(upperLeft.getX() + width, upperLeft.getY() + height);

        arr[0] = new gui.geometry.Line(upperLeft, upRight);
        arr[1] = new gui.geometry.Line(downLeft, downRight);
        arr[2] = new gui.geometry.Line(upperLeft, downLeft);
        arr[3] = new gui.geometry.Line(upRight, downRight);*/

        arr[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        arr[1] = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX()
                + width, upperLeft.getY() + height);
        arr[2] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        arr[3] = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX()
                + width, upperLeft.getY() + height);
    }

    /**
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * accessors.
     *
     * @return the color of the rectangle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessors.
     *
     * @param colorR color.
     */
    public void setColor(Color colorR) {
        this.color = colorR;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set upper left point.
     *
     * @param p new gui.geometry.Point to set the upper left.
     */

    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}