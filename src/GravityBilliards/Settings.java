package GravityBilliards;

public class Settings {
    public static int Seconds=60;
    public static int FramesPerSecond=10;
    public static int Frames=Seconds*FramesPerSecond;
//    double Acceleration = 9.10938356 * Math.pow(10, 31) / Math.pow(105700*9.461*Math.pow(10, 15), 2);   // m/r2 for electron on the other side of the milky way
    public static double Acceleration = 9.82;   // for earth
    // public static final String OutputFiles ="c:\\slask\\GravityBilliards%s.png";     // Magnus' work computer
    public static final String OutputFiles = "d:\\CB\\cbtempd\\GravityBilliards\\GravityBilliards%s.png";     // Cecilia computer

}
