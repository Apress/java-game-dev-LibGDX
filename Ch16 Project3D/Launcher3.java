import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher3
{
    public static void main ()
    {
        Game myGame = new StarfishCollector3DGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Starfish Collector 3D", 800, 600 );
    }
}