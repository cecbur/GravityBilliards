package GravityBilliards.Transformer;

import GravityBilliards.Ball;

public class RotationTransform extends Transform {
    private double theta;

    public RotationTransform(double theta) {
        this.theta = theta;
    }

    @Override
    public Ball Apply(Ball ball) {
        return Rotate(ball, theta);
    }

    @Override
    public Ball Unapply(Ball ball) {
        return Rotate(ball, -theta);
    }

    private Ball Rotate(Ball ball, double fi) {
        double[] newPos = Rotate(ball.X, ball.Y, fi);
        double[] newVelocity = Rotate(ball.VelocityX, ball.VelocityY, fi);
        return new Ball(newPos[0], newPos[1], newVelocity[0], newVelocity[1], ball.Color, ball.Id);
    }

    private double[] Rotate(double x, double y, double fi) {
        double xNew = Math.cos(fi) * x - Math.sin(fi) * y;
        double yNew = Math.sin(fi) * x + Math.cos(fi) * y;
        return new double[]{xNew, yNew};
    }
}
