import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sparkle extends BaseActor
{
    public Sparkle(float x, float y, Stage s)
    {
       super(x,y,s);
        
       loadAnimationFromSheet("assets/sparkle.png", 8,8, 0.02f, false);
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        if ( isAnimationFinished() )
            remove();
    }
}