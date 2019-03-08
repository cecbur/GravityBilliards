import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(0,0,0,0));
        State state = new State(balls);
        Drawer.Draw(state);

    }
}
