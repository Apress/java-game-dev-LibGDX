import com.badlogic.gdx.scenes.scene2d.Stage;

public class Rock extends BaseActor
{
    public Rock(float x, float y, Stage s)
    { 
       super(x,y,s);
       loadTexture("assets/rock.png");  
       setBoundaryPolygon(8);
    }    
}