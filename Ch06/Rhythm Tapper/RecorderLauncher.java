import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class RecorderLauncher
{
    public static void main (String[] args)
    {
        BaseGame myGame = new RecorderGame();
        LwjglApplication launcher = new LwjglApplication( myGame, "Recorder", 800, 600 );
    }
}