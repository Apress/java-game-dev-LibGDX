import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ground extends BaseActor
{
    public Ground(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/ground.png");
        setSpeed(100);
        setMotionAngle(180);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        
        // if moved completely past left edge of screen:
        //   shift right, past other instance.
        if ( getX() + getWidth() < 0 )
        {
            moveBy( 2 * getWidth(), 0 );
        }
    }
    
}