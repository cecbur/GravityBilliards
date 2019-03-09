package GravityBilliards;

public class Ball {

    public static final double Mass = 0.165;
    public static final double Diameter = 0.0615;
    public static int ballsCreated  = 0;
    public int Id;

    public Ball(double x, double y, double velocityX, double velocityY) {
        ballsCreated++;
        Id = ballsCreated;
        this.X=x;
        this.Y=y;
        this.VelocityX=velocityX;
        this.VelocityY=velocityY;
    }

    public double X;
    public double Y;
    public double VelocityX;
    public double VelocityY;
}
