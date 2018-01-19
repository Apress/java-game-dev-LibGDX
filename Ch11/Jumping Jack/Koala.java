import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class Koala extends BaseActor  
{
    private Animation stand;
    private Animation walk;

    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    private float gravity;
    private float maxVerticalSpeed;

    private Animation jump;
    private float jumpSpeed;
    private BaseActor belowSensor;

    public Koala(float x, float y, Stage s)
    {
        super(x,y,s);

        stand = loadTexture( "assets/koala/stand.png" );

        String[] walkFileNames = 
            {"assets/koala/walk-1.png", "assets/koala/walk-2.png", 
                "assets/koala/walk-3.png", "assets/koala/walk-2.png"};

        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true);

        maxHorizontalSpeed = 100;
        walkAcceleration   = 200;
        walkDeceleration   = 200;
        gravity            = 700;
        maxVerticalSpeed   = 1000;

        setBoundaryPolygon(8);

        jump = loadTexture( "assets/koala/jump.png"  );        
        jumpSpeed = 450;

        // set up the below sensor
        belowSensor = new BaseActor(0,0, s);
        belowSensor.loadTexture("assets/white.png");
        belowSensor.setSize( this.getWidth() - 8, 8 );
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(false);
    }

    public void act(float dt)
    {
        super.act( dt );

        // get keyboard input
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            accelerationVec.add( -walkAcceleration, 0 );

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerationVec.add( walkAcceleration, 0 );

        // decelerate when not accelerating
        if ( !Gdx.input.isKeyPressed(Keys.RIGHT)
        && !Gdx.input.isKeyPressed(Keys.LEFT)  )
        {
            float decelerationAmount = walkDeceleration * dt;

            float walkDirection;

            if ( velocityVec.x > 0 )
                walkDirection = 1;
            else
                walkDirection = -1;

            float walkSpeed = Math.abs( velocityVec.x );

            walkSpeed -= decelerationAmount;

            if (walkSpeed < 0)
                walkSpeed = 0;

            velocityVec.x = walkSpeed * walkDirection;
        }

        // apply gravity
        accelerationVec.add(0, -gravity);

        velocityVec.add( accelerationVec.x * dt, accelerationVec.y * dt );

        velocityVec.x = MathUtils.clamp( velocityVec.x, 
            -maxHorizontalSpeed, maxHorizontalSpeed );

        moveBy( velocityVec.x * dt, velocityVec.y * dt );

        // reset acceleration
        accelerationVec.set(0,0);

        // move the below sensor below the koala
        belowSensor.setPosition( getX() + 4, getY() - 8 );

        // manage animations
        if ( this.isOnSolid() )
        {
            belowSensor.setColor( Color.GREEN );
            if ( velocityVec.x == 0 )
                setAnimation(stand);
            else
                setAnimation(walk);
        }
        else
        {
            belowSensor.setColor( Color.RED );
            setAnimation(jump);
        }

        if ( velocityVec.x > 0 ) // face right
            setScaleX(1);

        if ( velocityVec.x < 0 ) // face left
            setScaleX(-1);

        alignCamera();
        boundToWorld();
    }

    public boolean belowOverlaps(BaseActor actor)
    {
        return belowSensor.overlaps(actor);
    }

    public boolean isOnSolid()
    {
        for (BaseActor actor : BaseActor.getList( getStage(), "Solid" ))
        {
            Solid solid = (Solid)actor;
            if ( belowOverlaps(solid) && solid.isEnabled() )
                return true;
        }   

        return false;
    }

    public void jump()
    {
        velocityVec.y = jumpSpeed;
    }

    public boolean isFalling()
    {
        return (velocityVec.y < 0);
    }

    public void spring()
    {
        velocityVec.y = 1.5f * jumpSpeed;
    }    

    public boolean isJumping()
    {
        return (velocityVec.y > 0); 
    }

}