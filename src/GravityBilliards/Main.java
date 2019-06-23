package GravityBilliards;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(1,2,0,-1, Color.black));
        balls.add(new Ball(1-Ball.Diameter/2,1,0,0, Color.red));
        balls.add(new Ball(1+Ball.Diameter/2,1,0,0, Color.blue));
        balls.add(new Ball(1,1-Math.sqrt(0.75)*Ball.Diameter,0,0, Color.green));
        balls.add(new Ball(1-Ball.Diameter,1-Math.sqrt(0.75)*Ball.Diameter,0,0, Color.MAGENTA));
        balls.add(new Ball(1+Ball.Diameter,1-Math.sqrt(0.75)*Ball.Diameter,0,0, Color.ORANGE));


        State state = new State(balls);
        Drawer.Draw(state);
        double timeToCollision;
        while (Drawer.animationFrame<Settings.Frames) {

            var nextCollision=Collider.GetFirst(state.Balls);
            timeToCollision = nextCollision.T;
            Drawer.Animate(balls, timeToCollision);
            state.forwardTime(timeToCollision);
            if (nextCollision.B2!=null) Collider.Collide3(nextCollision.B1, nextCollision.B2);
            else Collider.Collide(nextCollision.B1, nextCollision.W);
        }
        /* TODO: Remove
        timeToCollision = Time.ToCollision(balls.get(2), Table.walls.get(Table.WallNames.XMAX));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(2), Table.walls.get(Table.WallNames.XMAX));
        timeToCollision = Time.ToCollision(balls.get(2), Table.walls.get(Table.WallNames.XMIN));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(2), Table.walls.get(Table.WallNames.XMIN));
        Drawer.Animate(balls, 1);
        */
    }
}
