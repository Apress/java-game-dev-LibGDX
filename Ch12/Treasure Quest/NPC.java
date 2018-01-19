import com.badlogic.gdx.scenes.scene2d.Stage;

public class NPC extends BaseActor
{
    // the text to be displayed
    private String text;
    
    // used to determine if dialog box text is currently being displayed
    private boolean viewing;

    // ID used for specific graphics
    //   and identifying NPCs with dynamic messages 
    private String ID;

    public NPC(float x, float y, Stage s)
    {
        super(x,y,s);
        text = " ";
        viewing = false;
    }

    public void setText(String t)
    {  text = t;  }

    public String getText()
    {  return text;  }

    public void setViewing(boolean v)
    {  viewing = v;  }

    public boolean isViewing()
    {  return viewing;  }  

    public void setID(String id)
    {  
        ID = id;  
        
        if ( ID.equals("Gatekeeper") )
            loadTexture("assets/npc-1.png");
        else if (ID.equals("Shopkeeper"))
            loadTexture("assets/npc-2.png");
        else // default image
            loadTexture("assets/npc-3.png");
    }

    public String getID()
    {  return ID;  }
}