package GravityBilliards;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Drawer
{
    public static void Draw(State state) throws IOException {

        int height = 600;
        int tableLength =(int)(1*height);
        int tableWidth = (int)(Table.Width * (double)tableLength/Table.Length);
        int width = (int)((double)tableWidth/1);
        double pixelsPerMeter=tableLength/Table.Length;
        int ballDiameter = (int)(Ball.Diameter*pixelsPerMeter);


        // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
        // into integer pixels
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();
        ig2.setColor(Color.red);

        for (Ball ball :state.Balls) {
            int x = (int)((ball.X-ball.Diameter/2) * pixelsPerMeter);
            int y = (int)((ball.Y-ball.Diameter/2) * pixelsPerMeter);
            ig2.drawOval(x, y, ballDiameter, ballDiameter);
        }
        // String file ="c:\\slask\\GravityBilliards.png";     // Magnus' work computer
        String file ="d:\\CB\\cbtempd\\GravityBilliards.png";     // Cecilia work computer
        ImageIO.write(bi, "PNG", new File(file));

    }
}