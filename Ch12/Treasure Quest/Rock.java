import com.badlogic.gdx.scenes.scene2d.Stage;

public class Rock extends Solid
{
    public Rock(float x, float y, Stage s)
    { 
        super(x,y,32,32,s);
        loadTexture("assets/rock.png");
        setBoundaryPolygon(8);
    }
}