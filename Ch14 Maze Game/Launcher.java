import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new MazeGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Maze Runman", 768, 700 );
    }
}