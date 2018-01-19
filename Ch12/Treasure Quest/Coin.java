import com.badlogic.gdx.scenes.scene2d.Stage;

public class Coin extends BaseActor
{
    public Coin(float x, float y, Stage s)
    { 
       super(x,y,s);
       loadTexture("assets/coin.png");
    }    
}