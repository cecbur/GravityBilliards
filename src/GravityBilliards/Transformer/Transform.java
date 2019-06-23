package GravityBilliards.Transformer;

import GravityBilliards.Ball;

public abstract class Transform {

    public abstract Ball Apply(Ball ball);

    public abstract Ball Unapply(Ball ball);





}