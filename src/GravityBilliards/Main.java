package GravityBilliards;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(0,0,0,0, Color.black));
        balls.add(new Ball(0.5,0.48,1,1, Color.red));
        balls.add(new Ball(1,1,0,0, Color.blue));
        balls.add(new Ball(1,2,0.1,0.1, Color.green));


        State state = new State(balls);
        Drawer.Draw(state);
        double timeToCollision;
        while (Drawer.animationFrame<Settings.Frames) {

            var nextCollision=Collider.GetFirst(state.Balls);
            timeToCollision = nextCollision.T;
            Drawer.Animate(balls, timeToCollision);
            state.forwardTime(timeToCollision);
            if (nextCollision.B2!=null) Collider.Collide2(nextCollision.B1, nextCollision.B2);
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
