import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class LevelScreen extends BaseScreen
{
    Maze maze;
    Hero hero;

    Ghost ghost;

    Label coinsLabel;
    Label messageLabel;

    Sound coinSound;
    Music windMusic;
    
    public void initialize() 
    {        
        BaseActor background = new BaseActor(0,0,mainStage);
        background.loadTexture("assets/white.png");
        background.setColor(Color.GRAY);
        background.setSize(768, 700);

        maze = new Maze(mainStage);

        hero = new Hero(0,0,mainStage);
        hero.centerAtActor( maze.getRoom(0,0) );

        ghost = new Ghost(0,0,mainStage);
        ghost.centerAtActor( maze.getRoom(11,9) );

        for (BaseActor room : BaseActor.getList(mainStage, "Room"))
        {
            Coin coin = new Coin(0,0,mainStage);
            coin.centerAtActor(room);
        }

        ghost.toFront();

        coinsLabel = new Label("Coins left:", BaseGame.labelStyle);
        coinsLabel.setColor( Color.GOLD );
        messageLabel = new Label("...", BaseGame.labelStyle);
        messageLabel.setFontScale(2);
        messageLabel.setVisible(false);

        uiTable.pad(10);
        uiTable.add(coinsLabel);
        uiTable.row();
        uiTable.add(messageLabel).expandY();

        coinSound  = Gdx.audio.newSound(Gdx.files.internal("assets/coin.wav"));

        windMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/wind.mp3"));
        windMusic.setLooping(true);
        windMusic.setVolume(0.1f);
        windMusic.play();
    }

    public void update(float dt)
    {
        for (BaseActor wall : BaseActor.getList(mainStage, "Wall"))
        {
            hero.preventOverlap(wall);
        }

        if (ghost.getActions().size == 0)
        {
            maze.resetRooms();
            ghost.findPath( maze.getRoom(ghost), maze.getRoom(hero) );
        }

        for (BaseActor coin : BaseActor.getList(mainStage, "Coin"))
        {
            if (hero.overlaps(coin))
            {
                coin.remove();
                coinSound.play(0.f);
            }
        }

        int coins = BaseActor.count(mainStage, "Coin");
        coinsLabel.setText("Coins left: " + coins);

        if (coins == 0)
        {
            ghost.remove();
            ghost.setPosition(-1000, -1000);
            ghost.clearActions();
            ghost.addAction( Actions.forever( Actions.delay(1) ) );
            messageLabel.setText("You win!");
            messageLabel.setColor(Color.GREEN);
            messageLabel.setVisible(true);
        }   

        if (hero.overlaps(ghost))
        {
            hero.remove();
            hero.setPosition(-1000, -1000);
            ghost.clearActions();
            ghost.addAction( Actions.forever( Actions.delay(1) ) );
            messageLabel.setText("Game Over");
            messageLabel.setColor(Color.RED);
            messageLabel.setVisible(true);
        }

        if ( !messageLabel.isVisible() )
        {
            float distance = new Vector2(hero.getX() - ghost.getX(), hero.getY() - ghost.getY() ).len();
            float volume = -(distance - 64)/(300 - 64) + 1;
            volume = MathUtils.clamp(volume, 0.10f, 1.00f);
            windMusic.setVolume(volume);
        }
    }

    public boolean keyDown(int keyCode)
    {
        if ( keyCode == Keys.R )
            BaseGame.setActiveScreen( new LevelScreen() );

        return false;
    }
}