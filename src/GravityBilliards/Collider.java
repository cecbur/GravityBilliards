package GravityBilliards;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.atan;


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

    public static GetFirstReturnObj GetFirst(List<Ball> balls)
    {
        GetFirstReturnObj returnObj = new GetFirstReturnObj();
        for (Ball b1 : balls)
        {
            for (Ball b2 : balls) {
                if (b1 == b2) {
                    continue;
                }
                var t = Time.ToCollision(b1, b2);
                if (t>=0 && (returnObj.B1==null || returnObj.T>t))
                {
                    returnObj.T=t;
                    returnObj.B1=b1;
                    returnObj.B2=b2;
                    returnObj.W=null;
                }
            }
            for (Wall w : Table.walls.values()) {
                var t = Time.ToCollision(b1, w);
                if (t>=0 && (returnObj.B1==null || returnObj.T>t))
                {
                    returnObj.T=t;
                    returnObj.B1=b1;
                    returnObj.B2=null;
                    returnObj.W=w;
                }
            }
        }
        return returnObj;
    }
    public static class GetFirstReturnObj
    {
        public double T=-1;
        public Ball B1=null;
        public Ball B2=null;
        public Wall W=null;
    }

    private static List<Collision> GetAllCollision(List<Ball> balls)
    {
        var collistions = new ArrayList<Collision>();
        for (Ball b1 : balls)
        {
            for (Ball b2 : balls) {
                if (b1 == b2) {
                    continue;
                }

                var t = Time.ToCollision(b1, b2);
                if (t>0)
                {
                    collistions.add(new Collision(t, b1, b2));
                }
            }
        }
        return collistions;
    }


    // Updates the velocities of the two balls to velocities after collision
    static void Collide2(Ball ball1, Ball ball2)
    {
        // Set ball 1 as reference point
        var transform = GetTransformToChangeReferencePointToBall(ball1);
        transform.Apply(ball1);
        transform.Apply(ball2);

        var px = ball2.X; //Assume mass 1
        var py = ball2.Y;
        var phi = atan(ball2.Y / ball2.X); // Angel between the line connecting the balls and x axis

        // After collision
        var a = phi; // Resulting direction ball 1
        var b = a - PI/2;// Resulting direction ball 2
        var sa = Math.sin(a);
        var sb = Math.sin(b);
        var ca = Math.cos(a);
        var cb = Math.cos(b);
        var v2 = (sa * py - ca * px) / (sa * cb - sb * ca); // speed ball 2
        var v1 = (px - v2 * sb) / sa; // speed ball 1
        ball1.VelocityX = Math.sin(a)* v1;
        ball1.VelocityY = Math.cos(a)* v1;
        ball2.VelocityX = Math.sin(b)* v2;
        ball2.VelocityY = Math.cos(b)* v2;

        // Reverse transform
        transform.ApplyReverse(ball1);
        transform.ApplyReverse(ball2);
    }

    private static Transform GetTransformToChangeReferencePointToBall(Ball ball)
    {
        return new Transform(-ball.X, -ball.Y, -ball.VelocityX, -ball.VelocityY);
    }

    private static class Transform
    {
        double DeltaX;
        double DeltaY;
        double DeltaVx;
        double DeltaVy;

        Transform(double deltaX, double deltaY, double deltaVx, double deltaVy)
        {
            DeltaX = deltaX;
            DeltaY = deltaY;
            DeltaVx = deltaVx;
            DeltaVy = deltaVy;
        }

        public void Apply(Ball b)
        {
            b.X += DeltaX;
            b.Y += DeltaY;
            b.VelocityX += DeltaVx;
            b.VelocityY += DeltaVy;
        }

        public void ApplyReverse(Ball b)
        {
            b.X -= DeltaX;
            b.Y -= DeltaY;
            b.VelocityX -= DeltaVx;
            b.VelocityY -= DeltaVy;
        }
    }

    // Updates the velocities of the two balls to velocities after collision
    static void CollideX(Ball ball1 , Ball ball2)
    {
        // Verify that distance between balls is one ball diameter
        if (!Measurer.sameDistance(ball1, ball2, Ball.Diameter)) {
                throw new IllegalArgumentException("Balls don't touch!");
        }

        double energy0 = Util.getKineticEnergy(ball1)+Util.getKineticEnergy(ball2);
        var momentum0 = Util.getTotalMomentum(ball1, ball2);

        // change frame of reference so that ball 1 is standing still
        Ball b1pre = new Ball(ball1.X, ball1.Y, ball1.VelocityX-ball2.VelocityX, ball1.VelocityY-ball2.VelocityY, Color.black);
        Ball b2pre = new Ball(ball2.X, ball2.Y, 0, 0, Color.black);

        double energyPre = Util.getKineticEnergy(b1pre)+Util.getKineticEnergy(b2pre);
        var momentumPre = Util.getTotalMomentum(ball1, ball2);

        // Calculate angle of appoach for ball 1
        double approachAngle = atan(b1pre.VelocityY/b1pre.VelocityX);
        // Calculate angle between balls
        double ballAngle = atan((b2pre.Y-b1pre.Y)/(b2pre.X-b1pre.X));

        // Calculate speed after collision
        double b1SpeedPre=Math.sqrt(b1pre.VelocityX*b1pre.VelocityX+b1pre.VelocityY*b1pre.VelocityY);
        if(b1pre.X>b2pre.X) { b1SpeedPre = -b1SpeedPre; }
        double b2SpeedPost=b1SpeedPre*Math.cos(ballAngle-approachAngle);
        double b1SpeedPost=b1SpeedPre*Math.sin(ballAngle-approachAngle);
        Ball b1post = new Ball(b1pre.X, b1pre.Y, b1SpeedPost*Math.sin(ballAngle), -b1SpeedPost*Math.cos(ballAngle), Color.black);
        Ball b2post = new Ball(b2pre.X, b2pre.Y, b2SpeedPost*Math.cos(ballAngle), b2SpeedPost*Math.sin(ballAngle), Color.black);

        double energyPost = Util.getKineticEnergy(b1post)+Util.getKineticEnergy(b2post);
        var momentumPost = Util.getTotalMomentum(ball1, ball2);

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

        double energy1 = Util.getKineticEnergy(ball1)+Util.getKineticEnergy(ball2);
        var momentum1 = Util.getTotalMomentum(ball1, ball2);

        if (Math.abs(energy0-energy1)>0.00001){
            //throw new ("Kinetic energy before '"+energy0+"', Kinetic energy after '"+energy1+"'");
            System.out.println("Kinetic energy before '"+energy0+"', Kinetic energy after '"+energy1+"'");
            int i=1;
        }

    }

    // Updates the velocities of the ball to velocity after collision
    static void Collide(Ball ball , Wall wall)
    {
        // Verify that distance between ball and wall is one half ball diameter
        if (!Measurer.sameDistance(ball, wall, Ball.Diameter/2)) {
            throw new IllegalArgumentException("Ball doesn't touch wall! Ball is at Y='"+ball.Y+"' and wall is at Y='"+wall.Y+"' so distance between them is '"+Measurer.distance2D(ball, wall)+"' but should have been'"+Ball.Diameter/2+"'.");
        }

        if (wall.X!=null) ball.VelocityX=-ball.VelocityX;
        if (wall.Y!=null) ball.VelocityY=-ball.VelocityY;
    }



}
