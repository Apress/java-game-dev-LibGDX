import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;

public class Wall extends BaseActor
{
    public Wall(float x, float y, float width, float height, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/white-square.png");
        setSize(width, height);
        setColor( Color.GRAY );
        setBoundaryRectangle();
    }
}