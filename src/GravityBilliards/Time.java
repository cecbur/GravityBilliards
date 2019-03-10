package GravityBilliards;

class Time {

    // TODO: Move method here
    public static double ToCollision(Ball ball1, Ball ball2){
        return Collider.GetTimeUntilCollision(ball1, ball2);
    }

    public static Double ToCollision(Ball ball, Wall wall) {
        double wallLessThanBall=0;
        if (wall.X!=null){ if (wall.X<ball.X) { wallLessThanBall=1; } else { wallLessThanBall=-1; } }
        if (wall.Y!=null){ if (wall.Y<ball.Y) { wallLessThanBall=1; } else { wallLessThanBall=-1; } }
        if (wall.X==null){     // Wall is in direction of gravity so is not moving towards or away from ball
            if (wallLessThanBall*ball.VelocityY>=0) return -1d;    // Ball moving away from mall
            return Math.abs(wall.Y+wallLessThanBall*Ball.Diameter/2-ball.Y)/ball.VelocityY;
        }
        if (wall.accelerationX==0){     // Wall is standing still
            if (wallLessThanBall*ball.VelocityX>=0) return -1d;    // Ball moving away from mall
            return Math.abs(wall.X+wallLessThanBall*Ball.Diameter/2-ball.X)/ball.VelocityX;
        }
        double va = ball.VelocityX/wall.accelerationX;
        double time = va-Math.sqrt(va*va-(2*(wall.X-ball.X)+wallLessThanBall*Ball.Diameter)/wall.accelerationX);
        return time;
    }



}
