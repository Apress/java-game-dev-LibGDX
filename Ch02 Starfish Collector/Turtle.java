import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Turtle extends ActorBeta
{
    public Turtle()
    {
        super();
    }
    
    public void act(float dt)
    {
        super.act(dt);
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
           this.moveBy(-1,0);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            this.moveBy(1,0);
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            this.moveBy(0,1);
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            this.moveBy(0,-1);
    }
}