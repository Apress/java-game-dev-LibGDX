import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new JumpingJackGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Jumping Jack", 800, 640 );
    }
}