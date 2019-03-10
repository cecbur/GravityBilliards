package GravityBilliards;

import java.awt.*;
import java.util.*;
import java.util.List;


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
                if (t>0)
                {
                    collistions.add(new Collision(t, b1, b2));
                }
            }
        }
        return collistions;
    }

    // TODO: Move to Time
    public static double GetTimeUntilCollision(Ball b1 , Ball b2)
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

        /* TODO: Remove
        if (D < 0)
        {
            return null;
        }
        */
        return -p/2 - D;
    }

    // Updates the velocities of the two balls to velocities after collision
    static void Collide(Ball ball1 , Ball ball2)
    {
        /* TODO: kEEP
        // Verify that distance between balls is one ball diameter
        // if (!Measurer.sameDistance(ball1, ball2, Ball.Diameter)) {   // TODO: Keep
        if (Math.round(Measurer.distance2D(ball1.X, ball1.Y, ball2.X, ball2.Y)*10000)/10000!=Math.round(Ball.Diameter*10000)/10000) {
                throw new IllegalArgumentException("Balls don't touch!");
        }
        */

        // change frame of reference so that ball 1 is standing still
        Ball b1pre = new Ball(ball1.X, ball1.Y, ball1.VelocityX-ball2.VelocityX, ball1.VelocityY-ball2.VelocityY, Color.black);
        Ball b2pre = new Ball(ball2.X, ball2.Y, 0, 0, Color.black);

        // Calculate angle of appoach for ball 1
        double approachAngle = Math.atan(b1pre.VelocityX/b1pre.VelocityY);
        // Calculate angle between balls
        double ballAngle = Math.atan((b2pre.Y-b1pre.Y)/(b2pre.X-b1pre.X));

        // Calculate speed after collision
        double b1SpeedPre=Math.sqrt(b1pre.VelocityX*b1pre.VelocityX+b1pre.VelocityY*b1pre.VelocityY);
        double b2SpeedPost=b1SpeedPre*Math.cos(ballAngle-approachAngle);
        double b1SpeedPost=b1SpeedPre*Math.sin(ballAngle-approachAngle);
        Ball b1post = new Ball(b1pre.X, b1pre.Y, b1SpeedPost*Math.sin(ballAngle), -b1SpeedPost*Math.cos(ballAngle), Color.black);
        Ball b2post = new Ball(b2pre.X, b2pre.Y, b2SpeedPost*Math.cos(ballAngle), b2SpeedPost*Math.sin(ballAngle), Color.black);

        // Calculate delta V
        double b1dvx = b1post.VelocityX-b1pre.VelocityX;
        double b1dvy = b1post.VelocityY-b1pre.VelocityY;
        double b2dvx = b2post.VelocityX-b2pre.VelocityX;
        double b2dvy = b2post.VelocityY-b2pre.VelocityY;

        // Uppdate original balls
        ball1.VelocityX += b1dvx;
        ball1.VelocityY += b1dvy;
        ball2.VelocityX += b2dvx;
        ball2.VelocityY += b2dvy;
    }

    // Updates the velocities of the ball to velocity after collision
    static void Collide(Ball ball , Wall wall)
    {
        // Verify that distance between ball and wall is one half ball diameter
        if (!Measurer.sameDistance(ball, wall, Ball.Diameter/2)) {
            throw new IllegalArgumentException("Ball don't touch wall!");
        }

        if (wall.X!=null) ball.VelocityX=-ball.VelocityX;
        if (wall.Y!=null) ball.VelocityY=-ball.VelocityY;
    }
        /* TODO: Keep
            */



}
