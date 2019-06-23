package GravityBilliards.Transformer;

import GravityBilliards.Ball;

public class PositionTransform extends Transform {
    private double deltaX;
    private double deltaY;

    public PositionTransform(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Ball Apply(Ball ball) {
        return new Ball(ball.X + deltaX, ball.Y + deltaY, ball.VelocityX, ball.VelocityY, ball.Color, ball.Id);
    }

    public Ball Unapply(Ball ball) {
        return new Ball(ball.X - deltaX, ball.Y - deltaY, ball.VelocityX, ball.VelocityY, ball.Color, ball.Id);
    }
}