import com.badlogic.gdx.scenes.scene2d.Stage;

public class Coin extends BaseActor
{
    public Coin(float x, float y, Stage s)
    { 
       super(x,y,s);
       loadAnimationFromSheet("assets/items/coin.png", 1, 6, 0.1f, true);
    }    
}