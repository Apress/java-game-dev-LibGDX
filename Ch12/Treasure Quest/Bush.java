import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bush extends Solid
{
    public Bush(float x, float y, Stage s)
    { 
        super(x,y,32,32,s);
        loadTexture("assets/bush.png");
        setBoundaryPolygon(8);
    }
}