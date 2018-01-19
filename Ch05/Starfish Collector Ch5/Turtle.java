import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Turtle extends BaseActor
{
    public Turtle(float x, float y, Stage s)
    {
        super(x,y,s);

        String[] filenames = 
            {"assets/turtle-1.png", "assets/turtle-2.png", "assets/turtle-3.png", 
                "assets/turtle-4.png", "assets/turtle-5.png", "assets/turtle-6.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);
        
        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);
        
        setBoundaryPolygon(8);
    }

    public void act(float dt)
    {
        super.act( dt );

        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            accelerateAtAngle(180);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerateAtAngle(0);
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            accelerateAtAngle(90);
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            accelerateAtAngle(270);

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );

        if ( getSpeed() > 0 )
            setRotation( getMotionAngle() );

        boundToWorld();

        alignCamera();
    }

}