import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class RhythmLauncher
{
    public static void main (String[] args) 
    {
        BaseGame myGame = new RhythmGame();
        LwjglApplication launcher = new LwjglApplication( myGame, "Rhythm Tapper", 800, 600 );
    }
}