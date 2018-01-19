import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;

/**
 *  Designed for use in concert with 
 *      the DialogBox, SceneSegment, and Scene classes.
 */
public class TypewriterAction extends SetTextAction
{
    private float elapsedTime;
    private float charactersPerSecond;
    
    public TypewriterAction(String t)
    {  
        super(t);
        elapsedTime = 0;
        charactersPerSecond = 30;
    }
    
    public boolean act(float dt)
    {
        elapsedTime += dt;
        int numberOfCharacters = (int)(elapsedTime * charactersPerSecond);
        if (numberOfCharacters > textToDisplay.length())
            numberOfCharacters = textToDisplay.length();
        String partialText = textToDisplay.substring(0, numberOfCharacters);
        DialogBox db = (DialogBox)target;
        db.setText( partialText );
        // action is complete when all characters have been displayed
        return ( numberOfCharacters >= textToDisplay.length() );
    }
}