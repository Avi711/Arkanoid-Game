import java.util.LinkedList;
import java.util.List;

/**
 * @author Avraham sikirov 318731478
 * A class responsible for holding game environemnt,
 * which is a list of all the colludable objects.
 */

public class GameEnvironment {
    private List<Collidable> objects;


    /**
     * Constructor, initialize the collidable list.
     */

    public GameEnvironment() {
        this.objects = new LinkedList<Collidable>();
    }

    /**
     * @param c add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        objects.add(c);
    }

    /**
     * @param c remove from given collidable to the environment.
     */
    public void removeCollidable(Collidable c) {
        objects.remove(c);
    }

    /**
     * @return list of objects in the environment.
     */

    public List<Collidable> getObjects() {
        return objects;
    }

    /**
     * @param trajectory of the ball.
     * @return return the information about the closest collision that is going to occur.
     * if there isn't any collision, return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Double> distances = new LinkedList<Double>();
        List<CollisionInfo> info = new LinkedList<CollisionInfo>();
        for (int i = 0; i < objects.size(); ++i) {
            Point cPoint = trajectory.closestIntersectionToStartOfLine(objects.get(i).getCollisionRectangle());
            if (cPoint != null) {
                Double distance = cPoint.distance(trajectory.start());
                distances.add(distance);
                info.add(new CollisionInfo(cPoint, objects.get(i), distance));
            }
        }
        if (distances.size() == 0) {
            return null;
        }
        Double minD = distances.get(0);
        for (int i = 1; i < distances.size(); ++i) {
            if (distances.get(i) <= minD) {
                minD = distances.get(i);
            }
        }
        for (int i = 0; i <= info.size(); ++i) {
            if (info.get(i).getDistance().equals(minD)) {
                return info.get(i);
            }
        }
        return null;
    }
}