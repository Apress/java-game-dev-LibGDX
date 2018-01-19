import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class FallingBox extends BaseActor
{
    public FallingBox(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/box.png");
        setScale(0.75f, 0.75f);
    }

    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }

    public void flashOut()
    {
        float duration = 0.25f;
        Action flashOut = 
            Actions.parallel( 
                Actions.scaleTo(1.5f,1.5f, duration), 
                Actions.color(Color.WHITE, duration), 
                Actions.fadeOut(duration) );

        addAction( flashOut );
        addAction( Actions.after( Actions.removeActor() ) );   
    }
}