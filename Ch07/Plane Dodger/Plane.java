import com.badlogic.gdx.scenes.scene2d.Stage;

public class Plane extends BaseActor
{
    public Plane(float x, float y, Stage s)
    {
        super(x,y,s);
        String[] filenames = 
            {"assets/planeGreen0.png", "assets/planeGreen1.png",
                "assets/planeGreen2.png", "assets/planeGreen1.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);

        setMaxSpeed(800);
        setBoundaryPolygon(8);
    }

    public void act(float dt)
    {
        super.act(dt);

        // simulate force of gravity
        setAcceleration(800);
        accelerateAtAngle(270);
        applyPhysics(dt);

        // stop plane from passing through the ground
        for (BaseActor g : BaseActor.getList(this.getStage(), "Ground"))
        {
            if ( this.overlaps(g) )
            {
                setSpeed(0);
                preventOverlap(g);
            }
        }

        // stop plane from moving past top of screen
        if ( getY() + getHeight() > getWorldBounds().height )
        {
            setSpeed(0);
            boundToWorld();
        }

    }

    public void boost()
    {
        setSpeed(300);
        setMotionAngle(90);
    }   

}