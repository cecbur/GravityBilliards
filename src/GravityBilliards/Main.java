package GravityBilliards;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(0,0,0,0, Color.black));
        balls.add(new Ball(0.5,0.51,1,1, Color.red));
        balls.add(new Ball(1,1,0,0, Color.blue));


        State state = new State(balls);
        Drawer.Draw(state);
        double timeToCollision = Time.ToCollision(balls.get(1), balls.get(2));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(1), balls.get(2));
        /*
        timeToCollision = Time.ToCollision(balls.get(1), Table.walls.get(Table.WallNames.XMAX));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(1), Table.walls.get(Table.WallNames.XMAX));
        */
        timeToCollision = Time.ToCollision(balls.get(2), Table.walls.get(Table.WallNames.XMAX));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(2), Table.walls.get(Table.WallNames.XMAX));
        timeToCollision = Time.ToCollision(balls.get(2), Table.walls.get(Table.WallNames.XMIN));
        Drawer.Animate(balls, timeToCollision);
        state.forwardTime(timeToCollision);
        Collider.Collide(balls.get(2), Table.walls.get(Table.WallNames.XMIN));
        Drawer.Animate(balls, 1);
        /* TODO
        */
    }
}
