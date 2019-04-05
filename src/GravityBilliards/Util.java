package GravityBilliards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    static double getTotalKineticEnergy(List<Ball> balls){
        double e=balls.stream().mapToDouble(x -> getKineticEnergy(x)).sum();
        return e;
    }

    static double getKineticEnergy(Ball ball){
        double vSquared=ball.VelocityX*ball.VelocityX+ball.VelocityY*ball.VelocityY;
        return Ball.Mass*vSquared/2;
    }

    static double[] getTotalMomentum(Ball ball1, Ball ball2){
        return getTotalMomentum(Arrays.asList(new Ball[] { ball1, ball2 }));
    }
    static double[] getTotalMomentum(List<Ball> balls){
        double x=0;
        double y=0;
        for (Ball ball: balls
        ) {
            double[] momentum=getMomentum(ball);
            x+=momentum[0];
            y+=momentum[1];
        }
        return new double[] {x, y};
    }

    static double[] getMomentum(Ball ball){
        double[] momentum= new double[] {ball.VelocityX*Ball.Mass, ball.VelocityY*Ball.Mass};
        return momentum;
    }

}
