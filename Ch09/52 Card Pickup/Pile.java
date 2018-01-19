import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class Pile extends DropTargetActor
{
    private ArrayList<Card> cardList; 

    public Pile(float x, float y, Stage s)
    {
        super(x,y,s);
        cardList = new ArrayList<Card>();
        loadTexture("assets/pile.png");
        setSize(100,120);
        setBoundaryRectangle();
    }

    /**
     *  Add a Card object to the "top" of the pile.
     */
    public void addCard(Card c)
    {
        cardList.add(0, c);
    }

    /**
     *  Get "top" card in the pile (remains in pile).
     */
    public Card getTopCard()
    {
        return cardList.get(0);
    }

    /**
     *  Return the number of cards in this pile.
     */
    public int getSize()
    {
        return cardList.size();
    }
}