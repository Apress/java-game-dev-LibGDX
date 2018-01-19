import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy extends BaseActor
{
    public Enemy(float x, float y, Stage s)
    {
        super(x,y,s);
        String[] filenames = 
            {"assets/planeRed0.png", "assets/planeRed1.png",
                "assets/planeRed2.png", "assets/planeRed1.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);

        setSpeed(100);
        setMotionAngle(180);
        setBoundaryPolygon(8);
    }

    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
    }
}