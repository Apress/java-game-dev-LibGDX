import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class LauncherBeta
{
    public static void main (String[] args)
    {
        // To start a LibGDX program, this method:
        // (1) creates an instance of the game
        // (2) creates a new application with game instance as argument
        Game myGame = new StarfishCollectorBeta();
        LwjglApplication launcher = new LwjglApplication( myGame, "Starfish Collector", 800, 600 );
    }
}
