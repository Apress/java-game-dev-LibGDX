import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;

public abstract class BaseScreen implements Screen, InputProcessor
{
    /**
     *  The stage to which all game entities should be added.
     */
    protected Stage mainStage;
    
    /**
     *  The stage that contains the user interface table.
     */
    protected Stage uiStage;
    
    /**
     *  A table used to organize user interface elements.
     */
    protected Table uiTable;

    /**
     *  Initializes mainStage, uiStage, and uiTable.
     */
    public BaseScreen()
    {
        mainStage = new Stage();
        uiStage = new Stage();

        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        initialize();
    }

    /**
     *  Use to initialize game objects and arrange user interface elements.
     */
    public abstract void initialize();

    /**
     *  Process continuous input and update game logic. 
     *  This method is called 60 times per second (when possible).
     */
    public abstract void update(float dt);

    // Gameloop:
    // (1) process input (discrete handled by listener; continuous in update)
    // (2) update game logic
    // (3) render the graphics
    public void render(float dt) 
    {
        // limit amount of time that can pass while window is being dragged
        dt = Math.min(dt, 1/30f);
        
        // act methods
        uiStage.act(dt);
        mainStage.act(dt);

        // defined by user
        update(dt);

        // clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the graphics
        mainStage.draw();
        uiStage.draw();
    }

    // methods required by Screen interface
    public void resize(int width, int height) {  }

    public void pause()   {  }

    public void resume()  {  }

    public void dispose() {  }

    /**
     *  Called when this becomes the active screen in a Game.
     *  Set up InputMultiplexer here, in case screen is reactivated at a later time.
     */
    public void show()    
    {  
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
    }

    /**
     *  Called when this is no longer the active screen in a Game.
     *  Screen class and Stages no longer process input.
     *  Other InputProcessors must be removed manually.
     */
    public void hide()    
    {   
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
        im.removeProcessor(mainStage);
    }

    /**
     *  Use for checking if an event is a touch-down event.
     */
    public boolean isTouchDownEvent(Event e)
    {
        return (e instanceof InputEvent) && ((InputEvent)e).getType().equals(Type.touchDown);
    }

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode)
    {  return false;  }

    public boolean keyUp(int keycode)
    {  return false;  }

    public boolean keyTyped(char c) 
    {  return false;  }

    public boolean mouseMoved(int screenX, int screenY)
    {  return false;  }

    public boolean scrolled(int amount) 
    {  return false;  }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) 
    {  return false;  }

    public boolean touchDragged(int screenX, int screenY, int pointer) 
    {  return false;  }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) 
    {  return false;  }
}