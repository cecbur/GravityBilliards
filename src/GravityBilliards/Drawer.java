package GravityBilliards;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;


public class Drawer
{

    public static Integer animationFrame =0;
    private static int framesPerSecond =7;
    // private static final String file ="c:\\slask\\GravityBilliards%s.png";     // Magnus' work computer
    private static final String file ="d:\\CB\\cbtempd\\GravityBilliards%s.png";     // Cecilia computer
    private static int imageHeight = 600;
    private static double tableLengthPartOfImageHeight = 0.9;
    private static int tableLength =(int)(tableLengthPartOfImageHeight*imageHeight);
    private static int tableWidth = (int)(Table.Width * (double)tableLength/Table.Length);
    private static int imageWidth = tableWidth+(imageHeight-tableLength);
    private static double pixelsPerMeter=tableLength/Table.Length;
    private static int ballDiameter = (int)(Ball.Diameter*pixelsPerMeter);
    private static int origoY=(imageHeight-tableLength)/2;
    private static int origoX=(imageWidth-tableWidth)/2;



    public static void Draw(State state) throws IOException {

        // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
        // into integer pixels
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        DrawTable(ig2);
        for (Ball ball :state.Balls) {
            ig2.setColor(ball.Color);
            int x = (int)((ball.X-ball.Diameter/2) * pixelsPerMeter+origoX);
            int y = (int)((ball.Y-ball.Diameter/2) * pixelsPerMeter+origoY);
            ig2.drawOval(x, y, ballDiameter, ballDiameter);
        }
        ImageIO.write(bi, "PNG", new File(String.format(file, "")));
    }

    public static void Animate(List<Ball> balls, double time) throws IOException {

        // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
        // into integer pixels

        Double t = 0d;
        do {
            animationFrame++;
            BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D ig2 = bi.createGraphics();
            DrawTable(ig2);
            for (Ball ball :balls) {
                DrawBall(ig2, ball, t);
            }
            ig2.drawString(t.toString(),0,0);
            ImageIO.write(bi, "PNG", new File(String.format(file, animationFrame.toString())));
            t+=1d/framesPerSecond;
        } while (t<time);
    }

    public static void DrawBall(Graphics2D ig2, Ball ball, double time) {
        ig2.setColor(ball.Color);
        int x = (int)((ball.xByTime(time)-ball.Diameter/2) * pixelsPerMeter+origoX);
        int y = (int)((ball.yByTime(time)-ball.Diameter/2) * pixelsPerMeter+origoY);
        ig2.fillOval(x, y, ballDiameter, ballDiameter);
    }


    public static void DrawTable(Graphics2D ig2) {

        ig2.setColor(Table.wallColor);

        for (Wall wall :Table.walls.values()) {
            ig2.drawLine((int)(wall.X1*pixelsPerMeter+origoX), (int)(wall.Y1*pixelsPerMeter+origoY), (int)(wall.X2*pixelsPerMeter+origoX), (int)(wall.Y2*pixelsPerMeter+origoY));
        }
    }


}