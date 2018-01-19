import com.badlogic.gdx.scenes.scene2d.Stage;

public class Arrow extends BaseActor
{
    public Arrow(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/arrow.png");
        setSpeed(400);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }
}