import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher1
{
    public static void main () 
    {
        CubeDemo myProgram = new CubeDemo();
        LwjglApplication launcher = new LwjglApplication( 
            myProgram, "Cube Demo", 800, 600 );
    }
}