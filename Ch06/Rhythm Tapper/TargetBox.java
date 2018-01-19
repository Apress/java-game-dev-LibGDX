import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class TargetBox extends BaseActor
{
    public TargetBox(float x, float y, Stage s, String letter, Color color)
    {
        super(x,y,s);
        loadTexture("assets/box.png");
        setSize(64,64);

        // add a centered label containing letter with given color
        Label letterLabel = new Label(letter, BaseGame.labelStyle);
        letterLabel.setSize(64,64);
        letterLabel.setAlignment(Align.center);
        letterLabel.setColor( color );
        this.addActor(letterLabel);
    }

    public void pulse()
    {
        Action pulse = 
            Actions.sequence( 
                Actions.scaleTo(1.2f,1.2f, 0.05f), 
                Actions.scaleTo(1.0f,1.0f, 0.05f) );
        addAction(pulse);
    }
}