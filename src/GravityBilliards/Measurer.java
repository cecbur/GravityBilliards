package GravityBilliards;

public class Measurer {
    public static double distance2D(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return distance;

    }

    public static double distance2D(Ball ball1, Ball ball2) {
        double distance = distance2D(ball1.X, ball1.Y, ball2.X, ball2.Y);
        return distance;
    }

    public static double distance2D(Ball ball, Wall wall) {
        if (wall.X!=null) return Math.abs(wall.X - ball.X);
        return Math.abs(wall.Y - ball.Y);
    }

    public static boolean sameDistance(Ball ball1, Ball ball2, double distance) {
        double balldistance =distance2D(ball1, ball2);
        return sameDistance(balldistance, distance);
    }

    public static boolean sameDistance(Ball ball, Wall wall, double distance) {
        return sameDistance(distance2D(ball, wall), distance);
    }

    public static boolean sameDistance(double distance1, double distance2) {
        double precision =10000;
        double d1=Math.round(distance1*precision)/precision;
        double d2=Math.round(distance2*precision)/precision;
        if (d1==d2) return true;
        else return false;
    }


}
