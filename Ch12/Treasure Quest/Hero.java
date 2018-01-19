import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class Hero extends BaseActor
{
    Animation north;
    Animation south;
    Animation east;
    Animation west;
    
    float facingAngle;

    public Hero(float x, float y, Stage s)
    {
        super(x,y,s); 

        String fileName = "assets/hero.png";
        int rows = 4;
        int cols = 4;
        Texture texture = new Texture(Gdx.files.internal(fileName), true);
        int frameWidth = texture.getWidth() / cols;
        int frameHeight = texture.getHeight() / rows;
        float frameDuration = 0.2f;

        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array<TextureRegion>();
        for (int c = 0; c < cols; c++)
            textureArray.add( temp[0][c] );
        south = new Animation(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);

        textureArray.clear();
        for (int c = 0; c < cols; c++)
            textureArray.add( temp[1][c] );
        west = new Animation(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);

        textureArray.clear();
        for (int c = 0; c < cols; c++)
            textureArray.add( temp[2][c] );
        east = new Animation(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);

        textureArray.clear();
        for (int c = 0; c < cols; c++)
            textureArray.add( temp[3][c] );
        north = new Animation(frameDuration, textureArray, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(south);
        facingAngle = 270;

        // set after animation established
        setBoundaryPolygon(8);

        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);

    }

    public void act(float dt)
    {
        super.act(dt);
        
        // pause animation when character not moving
        if ( getSpeed() == 0 )
            setAnimationPaused(true);
        else
        {
            setAnimationPaused(false);

            // set direction animation
            float angle = getMotionAngle();
            if (angle >= 45 && angle <= 135)
            {
                facingAngle = 90;
                setAnimation(north);
            }
            else if (angle > 135 && angle < 225)
            {
                facingAngle = 180;
                setAnimation(west);
            }
            else if (angle >= 225 && angle <= 315)
            {
                facingAngle = 270;
                setAnimation(south);
            }
            else
            {
                facingAngle = 0;
                setAnimation(east);
            }
        }

        alignCamera();
        boundToWorld();
        applyPhysics(dt);
    }

    public float getFacingAngle()
    {
        return facingAngle;
    }

}