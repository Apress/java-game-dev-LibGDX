import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public class MenuScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "assets/water.jpg" );
        ocean.setSize(800,600);

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture( "assets/starfish-collector.png" );

        TextButton startButton = new TextButton( "Start", BaseGame.textButtonStyle );

        startButton.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;

                StarfishGame.setActiveScreen( new LevelScreen() );
                return true;
            }
        );

        TextButton quitButton = new TextButton( "Quit", BaseGame.textButtonStyle );
        // quitButton.setPosition(500,150);
        // uiStage.addActor(quitButton);

        quitButton.addListener(
            (Event e) -> 
            { 
                if ( !isTouchDownEvent(e) )
                    return false;

                Gdx.app.exit();
                return true;
            }
        );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);

    }

    public void update(float dt)
    {

    }

    public boolean keyDown(int keyCode)
    {
        if (Gdx.input.isKeyPressed(Keys.ENTER)) 
            StarfishGame.setActiveScreen( new LevelScreen() );

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) 
            Gdx.app.exit();
        return false;
    }
}