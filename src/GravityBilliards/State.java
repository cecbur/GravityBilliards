package GravityBilliards;

import java.util.List;

public class State
{
    public State(List<Ball> balls){

        Balls=balls;
    }

    public List<Ball> Balls;
    public double SecondsPassed = 0;

    public void forwardTime(double time) {
        for (Ball ball :Balls
        ) {
            ball.setTime0(time);
        }
        for (Wall wall :Table.walls.values()) {
            wall.setTime0(time);
        }
    }


}