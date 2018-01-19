import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new PickupGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "52 Card Pickup", 800, 600 );
    }
}