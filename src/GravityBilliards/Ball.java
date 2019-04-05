package GravityBilliards;

import java.awt.*;

public class Ball {

    public static final double Mass = 0.165;
    public static final double Diameter = 0.0615*2;     // TODO: Remove the 2
    public static int ballsCreated  = 0;
    public int Id;

    public Ball(double x, double y, double velocityX, double velocityY, Color color) {
        ballsCreated++;
        Id = ballsCreated;
        this.X=x;
        this.Y=y;
        this.VelocityX=velocityX;
        this.VelocityY=velocityY;
        this.Color=color;
    }

    public double X;
    public double Y;
    public double VelocityX;
    public double VelocityY;
    public Color Color;

    public double xByTime(double time) {
        return X+time*VelocityX;
    }

    public double yByTime(double time) {
        return Y+time*VelocityY;
    }

    public void setTime0(double time) {
        X =  xByTime(time);
        Y =  yByTime(time);
    }


}
