import com.badlogic.gdx.scenes.scene2d.Stage;

public class Card extends DragAndDropActor
{
    public static String[] rankNames = {"A", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static String[] suitNames = {"Clubs", "Hearts", "Spades", "Diamonds"};

    private int rankValue;
    private int suitValue;

    public Card(float x, float y, Stage s)
    {
        super(x,y,s);
    }

    public void setRankValue(int r)
    {  rankValue = r;  }

    public int getRankValue()
    {  return rankValue;  }

    public String getRankName()
    {  return rankNames[ getRankValue() ];  }

    public void setSuitValue(int s)
    {  suitValue = s;  }

    public int getSuitValue()
    {  return suitValue;  }

    public String getSuitName()
    {  return suitNames[ getSuitValue() ];  }        

    public void setRankSuitValues(int r, int s)
    {
        setRankValue(r);
        setSuitValue(s);
        String imageFileName = "assets/card" 
            + getSuitName() + getRankName() + ".png";
        loadTexture(imageFileName);
        setSize(80,100);
        setBoundaryRectangle();
    }

    public void onDrop()
    {
        if ( hasDropTarget() )
        {
            Pile pile = (Pile)getDropTarget();
            Card topCard = pile.getTopCard();

            if (this.getRankValue() == topCard.getRankValue() + 1
            &&  this.getSuitValue() == topCard.getSuitValue() )
            {
                moveToActor(pile);
                pile.addCard(this);
            }
            else
            {
                // avoid blocking view of pile when incorrect.
                moveToStart();
            }
        }
    }

    public void act(float dt)
    {
        super.act(dt);
        boundToWorld();
    }
}