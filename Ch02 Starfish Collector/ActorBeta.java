import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 *  Extend the Actor class to include graphics and collision detection.
 *  Actor class stores data such as position and rotation.
 */
public class ActorBeta extends Actor
{
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    
    public ActorBeta()
    {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
    }
    
    public void setTexture(Texture t)
    {
        textureRegion.setRegion(t);
        this.setSize( t.getWidth(), t.getHeight() );   
        rectangle.setSize(t.getWidth(), t.getHeight());
    }
    
    public Rectangle getRectangle()
    {
        rectangle.setPosition( this.getX(), this.getY() );
        return rectangle;
    }
    
    public boolean overlaps(ActorBeta other)
    {
        return this.getRectangle().overlaps( other.getRectangle() );
    }
    
    public void act(float dt)
    {
        super.act(dt);
    }
    
    public void draw(Batch batch, float parentAlpha) 
    {
        super.draw( batch, parentAlpha );
        
        Color c = getColor(); // used to apply tint color effect

        batch.setColor(c.r, c.g, c.b, c.a);

        if ( isVisible() )
            batch.draw( textureRegion, 
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }
}