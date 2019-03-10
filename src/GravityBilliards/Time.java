package GravityBilliards;

class Time {

    public static double ToCollision(Ball ball1, Ball ball2){
        double dX0=ball2.X-ball1.X;
        double dY0=ball2.Y-ball1.Y;
        double dVx=ball2.VelocityX-ball1.VelocityX;
        double dVy=ball2.VelocityY-ball1.VelocityY;
        double p=2*(dX0*dVx+dY0*dVy)/(dVx*dVx+dVy*dVy);
        double q =(dX0*dX0+dY0*dY0-Ball.Diameter*Ball.Diameter)/(dVx*dVx+dVy*dVy);
        double inSqrt = p*p/4-q;
        if (inSqrt<0) return -1;        // Balls will not collide
        double time = -p/2-Math.sqrt(inSqrt);
        return time;
    }

    public static Double ToCollision(Ball ball, Wall wall) {
        double wallLessThanBall=0;
        if (wall.X!=null){ if (wall.X<ball.X) { wallLessThanBall=1; } else { wallLessThanBall=-1; } }
        if (wall.Y!=null){ if (wall.Y<ball.Y) { wallLessThanBall=1; } else { wallLessThanBall=-1; } }
        if (wall.X==null){     // Wall is in direction of gravity so is not moving towards or away from ball
            if (wallLessThanBall*ball.VelocityY>=0) return -1d;    // Ball moving away from mall
            return (wall.Y+wallLessThanBall*Ball.Diameter/2-ball.Y)/ball.VelocityY;
        }
        if (wall.accelerationX==0){     // Wall is standing still
            if (wallLessThanBall*ball.VelocityX>=0) return -1d;    // Ball moving away from mall
            return (wall.X+wallLessThanBall*Ball.Diameter/2-ball.X)/ball.VelocityX;
        }
        double va = ball.VelocityX/wall.accelerationX;
        double time = va-Math.sqrt(va*va-(2*(wall.X-ball.X)+wallLessThanBall*Ball.Diameter*Ball.Diameter)/wall.accelerationX);
        return time;
    }



}
