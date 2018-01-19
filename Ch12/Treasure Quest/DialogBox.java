import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

public class DialogBox extends BaseActor
{
    private Label dialogLabel;
    private float padding = 16;

    /**
     *  Set initial position of actor and add to stage.
     */
    public DialogBox(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/dialog-translucent.png");

        dialogLabel = new Label(" ", BaseGame.labelStyle);
        dialogLabel.setWrap(true);
        dialogLabel.setAlignment( Align.topLeft );
        dialogLabel.setPosition( padding, padding );
        this.setDialogSize( getWidth(), getHeight() );
        this.addActor(dialogLabel);
    }

    /**
     *  Set dimensions of dialog box.
     */
    public void setDialogSize(float width, float height)
    {
        this.setSize(width, height);
        dialogLabel.setWidth( width - 2 * padding );
        dialogLabel.setHeight( height - 2 * padding );
    }
    
    /**
     *  Set text to be displayed in dialog box.
     */
    public void setText(String text)
    {  dialogLabel.setText(text);  }

    /**
     *  Set scaling factor of font used to render text.
     */
    public void setFontScale(float scale)
    {  dialogLabel.setFontScale(scale);  }

    /**
     *  Set color of font used to render text.
     */
    public void setFontColor(Color color)
    {  dialogLabel.setColor(color);  }

    /**
     *  Set color of background region in dialog box.
     */
    public void setBackgroundColor(Color color)
    {  this.setColor(color);  }
    
    /**
     *  Align text to top-left of dialog box area.
     */
    public void alignTopLeft()
    {  dialogLabel.setAlignment( Align.topLeft );  }

    /**
     *  Center text within the dialog box area.
     */
    public void alignCenter()
    {  dialogLabel.setAlignment( Align.center );  }
}