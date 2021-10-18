import java.util.List;

/**
 * @author Avraham sikirov 318731478.
 * A line class which contains his points, his incline and his constant height.
 * There are different methods for setting and getting every field.
 * There are different methods to calculate the middle point of the line,
 * when the line starts and ends, and several methods
 * to calculate if there is an intersection.
 * point between two lines and methods which calculate them.
 */

public class Line {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double incline;
    private double height;

    /**
     * Constructor.
     *
     * @param start first point to create a line.
     * @param end   second point to create a line.
     */
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.x2 = end.getX();
        this.y1 = start.getY();
        this.y2 = end.getY();
        this.incline = (y2 - y1) / (x2 - x1);
        this.height = y1 - (incline * x1);
    }

    /**
     * Constructor.
     *
     * @param x1 first coordinate to create a line.
     * @param y1 second coordinate to create a line.
     * @param x2 third coordinate to create a line.
     * @param y2 forth coordinate to create a line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.incline = (y2 - y1) / (x2 - x1);
        this.height = y1 - (incline * x1);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        double add = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return Math.sqrt(add);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMiddle = (this.x1 + this.x2) / 2;
        double yMiddle = (this.y1 + this.y2) / 2;
        return new Point(xMiddle, yMiddle);
    }

    /**
     * @return the start point of the line.
     */
    public Point begining() {

        if (this.x1 != this.x2 && this.x1 < this.x2) {
            return new Point(x1, y1);
        } else if (y1 < y2) {
            return new Point(x1, y1);
        }
        return new Point(x2, y2);
    }

    /**
     * @return the end point of the line.
     */
    public Point ending() {
        Point begining = this.begining();
        if (x1 == begining.getX() && y1 == begining.getY()) {
            return new Point(x2, y2);
        }
        return new Point(x1, y1);
    }

    /**
     * @return line height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return line incline.
     */

    public double getIncline() {
        return this.incline;
    }


    /**
     * @return the start point of the line.
     */
    public Point start() {
        return new Point(x1, y1);
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return new Point(x2, y2);
    }

    /**
     * @return the point with the higher y value.
     */
    public Point bigY() {
        if (this.y1 >= this.y2) {
            return new Point(x1, y1);
        } else {
            return new Point(x2, y2);
        }
    }

    /**
     * @return return the point with the lower y value.
     */
    public Point smallY() {
        if (this.y1 <= this.y2) {
            return new Point(x1, y1);
        } else {
            return new Point(x2, y2);
        }
    }

    /**
     * @return return the point with the bigger x value.
     */
    public Point bigX() {
        if (this.x1 >= this.x2) {
            return new Point(x1, y1);
        } else {
            return new Point(x2, y2);
        }
    }

    /**
     * @return return the point with the lower x value.
     */
    public Point smallX() {
        if (this.x1 <= this.x2) {
            return new Point(x1, y1);
        } else {
            return new Point(x2, y2);
        }
    }

    /**
     * check if 2 lines are intersecting.
     *
     * @param other line to check if intersect with.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        if (isPartIntersecting(other)) {
            return true;
        }
        if (this.equals(other)) {
            return true;
        }
        if (Double.isInfinite(this.incline) && Double.isInfinite(other.incline)
                && this.x1 == other.x1 && this.parteq(other)) {
            return true;
        }
        if (Double.isInfinite(this.incline) && Double.isInfinite(this.incline)
                && this.x1 != other.x1) {
            return false;
        }
        if (this.incline == other.incline && parteq(other)) {
            return true;
        }
        return false;
    }

    /**
     * check if 2 lines are intersecting ones.
     *
     * @param other line to check if intersect with.
     * @return true if there is one intersection point, false otherwise.
     */
    public boolean isPartIntersecting(Line other) {
        // The lines are equal.
        if (this.equals(other)) {
            return false;
        }
        // check if all the points are the same.
        if (this.begining().equals(this.ending()) && other.begining().equals(other.ending())
                && this.begining().equals(other.begining())) {
            return true;
        }
        if (this.begining().equals(this.ending()) || other.begining().equals(other.ending())) {
            return other.isPointOnLine(this.begining()) || this.isPointOnLine(other.begining());
        }
        // Both line are plumb
        if ((Double.isInfinite(this.incline)
                && Double.isInfinite((other.incline)))
                && this.x1 == other.x1
                && !parteq(other) && commonY(other)) {
            return true;
        }
        // Both lines are horizontal


        if (this.incline == 0 && other.incline == 0 && this.y1 == other.y1 && !parteq(other) && commonX(other)
                && !isOneInOther(other)) {
            return true;
        }
        // One of the line plumb and the other horizontal
        if (isPerpendicular(this, other) != 0) {
            return true;
        }
        // Just one of the lines is plumb.
        if (onePlumbIntersect(other)) {
            return true;
        }
        // The same straight but also equal.
        if (this.incline == other.incline && this.height == other.height && this.parteq(other)) {
            return false;
        }
        // The inclines are equal.
        if (((this.incline == other.incline)
                && (this.begining().equals(other.ending()) || this.ending().equals(other.begining())))) {
            return true;
        }
        // Both line are not plumb.


        double intersectionX = (this.height - other.height) / (other.incline - this.incline);
        if (intersectionX <= this.bigX().getX()
                && intersectionX >= this.smallX().getX()
                && intersectionX <= other.bigX().getX()
                && intersectionX >= other.smallX().getX()
                && !Double.isInfinite(other.incline)
                && !Double.isInfinite(this.incline)) {
            return true;
        }

        if (isOneInOther(other)) {
            return false;
        }


        // If every test failed return false.
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect.
     *
     * @param other line to check where intersect with.
     * @return intersection point if there is one intersection point, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!isPartIntersecting(other)) {
            return null;
        }
        if (this.begining().equals(this.ending())) {
            return this.begining();
        }
        if (other.begining().equals(other.ending())) {
            return other.begining();
        }

        if ((Double.isInfinite(this.incline)
                && Double.isInfinite((other.incline)))
                || this.incline == other.incline) {
            return commonYPoint(other);
        }
        if ((Double.isInfinite(this.incline) && !Double.isInfinite(other.incline))) {
            return new Point(this.x1, this.x1 * other.incline + other.height);
        }
        if (Double.isInfinite(other.incline) && !Double.isInfinite(this.incline)) {
            return new Point(other.x1, other.x1 * this.incline + this.height);
        }
        // Calculate the intersection point by straight equations
        double intersectionX = (this.height - other.height) / (other.incline - this.incline);
        //  if (intersectionX <= ending().getX() && intersectionX >= begining().getX()) {
        double intersectionY = this.incline * intersectionX + this.height;
        return new Point(intersectionX, intersectionY);
    }

    /**
     * @param other line to check if equal to.
     * @return true is the lines are equal, false otherwise.
     */
    //
    public boolean equals(Line other) {
        if (this.begining().equals(other.begining()) && this.ending().equals(other.ending())) {
            return true;
        }
        return false;
    }

    /**
     * @param other line to check if partial overlap to.
     * @return true is there is a partial overlap between the lines.
     */
    public boolean parteq(Line other) {
        // Check for math in the x indexes.
        if (this.begining().getX() < other.begining().getX() && other.begining().getX() < this.ending().getX()) {
            return true;
        }
        if (this.begining().getX() > other.begining().getX() && other.ending().getX() > this.begining().getX()) {
            return true;
        }
        // check for match in the y indexes.
        if (this.smallY().getY() < other.smallY().getY() && other.smallY().getY() < this.bigY().getY()) {
            return true;
        }
        if (this.smallY().getY() > other.smallY().getY() && other.bigY().getY() > this.smallY().getY()) {
            return true;
        }
        return false;
    }

    /**
     * isPerpendicular -- return 1,2 if one of the
     * lines is plumb and the other horizontal.
     * and there is an intersection points between
     * them and 0 if there is not.
     *
     * @param l1 first line.
     * @param l2 second line.
     * @return true or false.
     */
    public int isPerpendicular(Line l1, Line l2) {
        if (Double.isInfinite(l1.incline) && l2.incline == 0
                && (l1.x1 < l2.bigX().getX()
                && l1.x1 > l2.smallX().getX()
                && l2.y1 < l1.bigY().getY()
                && l2.y1 > l1.smallY().getY())) {
            return 1;
        } else if (Double.isInfinite(l2.incline) && l1.incline == 0
                && (l2.x1 < l1.ending().getX()
                && l2.x1 > l1.begining().getX()
                && l1.y1 < l2.bigY().getY()
                && l1.y1 > l2.smallY().getY())) {
            return 2;
        }
        return 0;
    }

    /**
     * check for plumb line and non plumb line for intersection point.
     *
     * @param other first line
     * @return true if there is one intersection point.
     */
    public boolean onePlumbIntersect(Line other) {

        if (Double.isInfinite(this.incline)
                && !Double.isInfinite(other.incline)) {
            double y = this.x1 * other.incline + other.height;
            Point p = new Point(this.x1, y);
            if (other.isPointOnLine(p) && this.isPointOnLine(p)) {
                return true;
            }
        }
        if (Double.isInfinite(other.incline)
                && !Double.isInfinite(this.incline)) {
            double y = other.x1 * this.incline + this.height;
            Point p = new Point(other.x1, y);
            if (other.isPointOnLine(p) && this.isPointOnLine(p)) {
                return true;
            }
        }

        return false;
    }

    /**
     * check if point is on line.
     *
     * @param point first line
     * @return true if the point is on the line.
     */
    public boolean isPointOnLine(Point point) {
        // in case the line is plumb.
        if (Double.isInfinite(this.incline) && this.x1 == point.getX()
                && this.middle().distance(point) <= this.length() / 2) {
            return true;
        }
        // in any other case.
        double y = point.getX() * this.incline + height;
        Point newP = new Point(point.getX(), y);
        if (newP.equals(point) && this.middle().distance(point) <= this.length() / 2) {
            return true;
        }
        return false;
    }

    /**
     * @param other line to check if there is common y with.
     * @return true is there is common y at the edges.
     */
    public boolean commonY(Line other) {
        if (this.y1 == other.y1 || this.y1 == other.y2 || this.y2 == other.y1 || this.y2 == other.y2) {
            return true;
        }
        return false;
    }

    /**
     * @param other line to check if there is common y with.
     * @return common point.
     */
    public Point commonYPoint(Line other) {
        if (this.ending().equals(other.begining())) {
            return this.ending();
        } else {
            return this.begining();
        }
    }

    /**
     * @param other line to check if there is common x with.
     * @return true is there is common x at the edges.
     */
    public boolean commonX(Line other) {
        if (this.x1 == other.x1 || this.x1 == other.x2
                || this.x2 == other.x1 || this.x2 == other.x2) {
            return true;
        }
        return false;
    }

    /**
     * @param other line to check if is in one another.
     * @return true if one of the line is in the other false otherwise.
     */
    public boolean isOneInOther(Line other) {
        if (this.incline == other.incline && this.height == other.height) {

            if ((!Double.isInfinite(this.incline) && !Double.isInfinite(other.incline))
                    && (this.smallX().getX() >= other.smallX().getX()
                    && this.bigX().getX() <= other.bigX().getX())
                    || (other.smallX().getX() >= this.smallX().getX() && other.bigX().getX() <= this.bigX().getX())) {
                return true;
            }
            if ((this.x1 == other.x1) && (this.smallY().getY() >= other.smallY().getY()
                    && this.bigY().getY() <= other.bigY().getY())
                    || (other.smallY().getY() >= this.smallY().getY() && other.bigY().getY() <= this.bigY().getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param rect to check closet intesections.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> inter = rect.intersectionPoints(this);
        Point p = new Point(this.x1, this.y1);
        if (inter.size() != 0) {
            Point smallD = inter.get(0);
            for (int i = 1; i < inter.size(); ++i) {
                Point newP = inter.get(i);

                if (newP.distance(p) < smallD.distance(p)) {
                    smallD = newP;
                }
            }
            return smallD;
        }
        return null;
    }
}