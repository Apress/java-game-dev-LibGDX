import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new JigsawPuzzleGame(); 
        LwjglApplication launcher = new LwjglApplication( myGame, "Jigsaw Puzzle Game", 800, 600 );
    }
}