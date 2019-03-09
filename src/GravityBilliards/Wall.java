package GravityBilliards;

public class Wall {

    public static final double Mass = 0.165;
    public static final double Diameter = 0.0615;
    public static int ballsCreated  = 0;
    public int Id;

    public Wall(double x1, double y1, double x2, double y2) {
        ballsCreated++;
        Id = ballsCreated;
        this.X1 =x1;
        this.Y1 =y1;
        this.X2 =x2;
        this.Y2 =y2;
        if (x1==x2){ X=x1; }
        if (y1==y2){ Y=y1; }
    }

    public double X1;
    public double Y1;
    public double X2;
    public double Y2;
    public Double X=null;
    public Double Y=null;
    public double accelerationX = 9.10938356 * Math.pow(10, 31) / Math.pow(105700*9.461*Math.pow(10, 15), 2);   // m/r2

    private double deltaXByTime(double time) {
        return Math.pow(time, 2) * accelerationX / 2;
    }

    public void setTime0(double time) {
        double delta = deltaXByTime(time);
        if (X!=null){
            X -= delta;
        }
        X1 -= delta;
        X2 -= delta;
    }

}