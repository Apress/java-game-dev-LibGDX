import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new SpaceGame();
        LwjglApplication launcher = new LwjglApplication( myGame, "Space Rocks", 800, 600 );
    }
}