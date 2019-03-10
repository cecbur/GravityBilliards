package GravityBilliards;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Map;

public class Table {
    public static final double Width =  1.42;
    public static final double Length =  2.84;
    public static final Color wallColor = Color.BLACK;
    public static final Map<WallNames, Wall> walls = Map.of(
            WallNames.YMIN, new Wall(0,0, Width,0, true),
            WallNames.XMAX, new Wall(Width,0, Width, Length, false),
            WallNames.YMAX, new Wall(Width, Length, 0,Length, true),
            WallNames.XMIN, new Wall(0,Length, 0,0, false)
    );


    enum WallNames {
        YMIN,
        YMAX,
        XMIN,
        XMAX
    }


}
