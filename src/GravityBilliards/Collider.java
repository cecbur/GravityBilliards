import java.util.*;


public class Collider
{
    private static class Collision
    {
        public final double T;
        public final Ball B1;
        public final Ball B2;

        public Collision(double t, Ball b1, Ball b2)
        {
            T = t;
            B1 = b1;
            B2 = b2;
        }
    }

    public static List<Collision> GetNextCollisions(List<Ball> balls)
    {
        var first = new ArrayList<Collision>();
        var collisions = GetAllCollision(balls);

        if (collisions.isEmpty())
        {
            return first;
        }

        collisions.sort(Comparator.comparingDouble(x -> x.T));
        var t = collisions.get(0).T;

         for(var c : collisions)
        {
            if (c.T == t)
            {
                first.add(c);
            }
        }

        return first;
    }

    private static List<Collision> GetAllCollision(List<Ball> balls)
    {
        var collistions = new ArrayList<Collision>();
        for (var b1 : balls)
        {
            for (var b2 : balls) {
                if (b1 == b2) {
                    continue;
                }

                var t = GetTimeUntilCollision(b1, b2);
                if (t!= null)
                {
                    collistions.add(new Collision(t, b1, b2));
                }
            }
        }
        return collistions;
    }

    private static Double GetTimeUntilCollision(Ball b1 , Ball b2)
    {
        var dx = b1.X - b2.X;
        var dy = b1.Y - b2.Y;
        var dvx = b1.VelocityX - b2.VelocityX;
        var dvy = b1.VelocityY - b2.VelocityY;

        var sumOfSquareVelocities = dvx * dvx + dvy * dvy;
        var sumOfSquareDistances = dx * dx + dy * dy;

        var p = 2 * (dx * dvx + dy * dvy) / sumOfSquareVelocities;
        var q = (sumOfSquareDistances - Ball.Diameter*Ball.Diameter) / sumOfSquareVelocities;
        var D = Math.pow(p/2 , 2) - q;

        if (D < 0)
        {
            return null;
        }

        return -p/2 - D;
    }
}
