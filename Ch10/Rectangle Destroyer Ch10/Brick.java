import com.badlogic.gdx.scenes.scene2d.Stage;

public class Brick extends BaseActor
{
    public Brick(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/brick-gray.png");
    }
}