import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StarfishCollectorBeta_Original extends Game
{
    private Turtle turtle;
    private ActorBeta starfish;
    private ActorBeta ocean;
    private ActorBeta winMessage;
    
    private Stage mainStage;
    
    private boolean win;
    
    public void create() 
    {        
        mainStage = new Stage();
        
        ocean = new ActorBeta();
        ocean.setTexture( new Texture( Gdx.files.internal("assets/water.jpg") )  );
        mainStage.addActor(ocean);
        
        starfish = new ActorBeta();
        starfish.setTexture( new Texture(Gdx.files.internal("assets/starfish.png")) );
        starfish.setPosition( 380,380 );
        mainStage.addActor( starfish );
        
        turtle = new Turtle();
        turtle.setTexture( new Texture(Gdx.files.internal("assets/turtle-1.png")) );
        turtle.setPosition( 20,20 );
        mainStage.addActor( turtle );
        
        winMessage = new ActorBeta();
        winMessage.setTexture( new Texture(Gdx.files.internal("assets/you-win.png")) );
        winMessage.setPosition( 180,180 );
        winMessage.setVisible( false );
        mainStage.addActor( winMessage );
        
        win = false;
    }

    public void render() 
    {        
        // check user input
        mainStage.act(1/60f);
        
        // check win condition: turtle must be overlapping starfish
        if (turtle.overlaps(starfish))
        {
            starfish.remove();
            winMessage.setVisible(true);
        }
        
        // clear screen
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            
        // draw graphics
        mainStage.draw();
    }
}