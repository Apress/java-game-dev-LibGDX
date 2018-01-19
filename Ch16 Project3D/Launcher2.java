import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
public class Launcher2
{
    public static void main ()
    {
        MoveDemo myProgram = new MoveDemo(); 
        LwjglApplication launcher = new LwjglApplication( 
            myProgram, "Movement Demo", 800, 600 );
    }
}