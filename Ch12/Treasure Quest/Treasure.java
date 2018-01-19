import com.badlogic.gdx.scenes.scene2d.Stage;

public class Treasure extends BaseActor
{
    public Treasure(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/treasure-chest.png");
    }
}