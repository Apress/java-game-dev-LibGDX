import com.badlogic.gdx.scenes.scene2d.Stage;

public class Springboard extends BaseActor
{
    public Springboard(float x, float y, Stage s)
    { 
       super(x,y,s);
       loadAnimationFromSheet("assets/items/springboard.png", 1, 3, 0.2f, true);
    }    
}