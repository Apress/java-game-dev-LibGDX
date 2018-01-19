import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Item extends BaseActor
{
    public enum Type { PADDLE_EXPAND, PADDLE_SHRINK, 
                       BALL_SPEED_UP, BALL_SPEED_DOWN };

    private Type type;

    public Item(float x, float y, Stage s)
    {
        super(x,y,s);
        setRandomType();
        
        setSpeed(100);
        setMotionAngle(270);
        
        setSize(50,50);
        // update related data
        setOrigin(25,25);
        setBoundaryRectangle();
        
        setScale(0,0);
        addAction( Actions.scaleTo(1,1, 0.25f) );
    }

    public void setType(Type t)
    {   
        type = t;

        if (t == Type.PADDLE_EXPAND)
            loadTexture("assets/items/paddle-expand.png");
        else if (t == Type.PADDLE_SHRINK)
            loadTexture("assets/items/paddle-shrink.png");
        else if (t == Type.BALL_SPEED_UP)
            loadTexture("assets/items/ball-speed-up.png");
        else if (t == Type.BALL_SPEED_DOWN)
            loadTexture("assets/items/ball-speed-down.png");
        else
            loadTexture("assets/items/item-blank.png");        
    }
    
    public void setRandomType()
    {
        int randomIndex = MathUtils.random(0, Type.values().length - 1);
        Type randomType = Type.values()[randomIndex];
        setType(randomType);
    }

    public Type getType()
    {
        return type;
    }
    
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        
        if (getY() < -50)
            remove();
    }
}