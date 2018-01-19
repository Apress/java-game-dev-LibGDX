import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;

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

        if (Controllers.getControllers().size > 0)
        {
            Controller gamepad = Controllers.getControllers().get(0);
            float xAxis =  gamepad.getAxis(XBoxGamepad.AXIS_LEFT_X);
            float yAxis = -gamepad.getAxis(XBoxGamepad.AXIS_LEFT_Y);

            Vector2 direction = new Vector2(xAxis, yAxis);
            float length = direction.len();
            float deadZone = 0.10f;
            if (length > deadZone)
            {
                setSpeed( length * 100 );
                setMotionAngle( direction.angle() );
            }
        }
        else
        {
            if (Gdx.input.isKeyPressed(Keys.LEFT)) 
                accelerateAtAngle(180);
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
                accelerateAtAngle(0);
            if (Gdx.input.isKeyPressed(Keys.UP)) 
                accelerateAtAngle(90);
            if (Gdx.input.isKeyPressed(Keys.DOWN)) 
                accelerateAtAngle(270);
        }

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );

        if ( getSpeed() > 0 )
            setRotation( getMotionAngle() );

        boundToWorld();

        alignCamera();
    }

}