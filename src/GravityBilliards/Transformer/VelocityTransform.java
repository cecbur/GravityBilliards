package GravityBilliards.Transformer;

import GravityBilliards.Ball;

public class VelocityTransform extends Transform {
    public double deltaX;
    public double deltaY;

    public VelocityTransform(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Ball Apply(Ball ball) {
        return new Ball(ball.X, ball.Y, ball.VelocityX + deltaX, ball.VelocityY + deltaY, ball.Color, ball.Id);
    }

    public Ball Unapply(Ball ball) {
        return new Ball(ball.X, ball.Y, ball.VelocityX - deltaX, ball.VelocityY - deltaY, ball.Color, ball.Id);
    }
}
