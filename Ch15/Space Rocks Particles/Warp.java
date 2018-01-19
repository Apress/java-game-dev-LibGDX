import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Warp extends BaseActor
{
    public Warp(float x, float y, Stage s)
    {
        super(x,y,s);

        loadAnimationFromSheet("assets/warp.png", 4, 8, 0.05f, true);

        addAction( Actions.delay(1) );   
        addAction( Actions.after( Actions.fadeOut(0.5f) ) );   
        addAction( Actions.after( Actions.removeActor() ) );  
    }
}