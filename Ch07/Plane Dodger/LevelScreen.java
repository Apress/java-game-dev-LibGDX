import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
public class LevelScreen extends BaseScreen
{
    Plane plane;

    float starTimer;
    float starSpawnInterval;

    int score;
    Label scoreLabel;

    float enemyTimer;
    float enemySpawnInterval;
    float enemySpeed;  

    boolean gameOver;
    BaseActor gameOverMessage;

    Music backgroundMusic;
    Sound sparkleSound;
    Sound explosionSound;

    public void initialize() 
    {        
        new Sky(0,0, mainStage);
        new Sky(800,0, mainStage);

        new Ground(0,0, mainStage);
        new Ground(800,0, mainStage);

        plane = new Plane(100, 500, mainStage);
        BaseActor.setWorldBounds(800,600);

        starTimer = 0;
        starSpawnInterval = 4;

        score = 0;
        scoreLabel = new Label( Integer.toString(score), BaseGame.labelStyle );
        uiTable.pad(10);
        uiTable.add(scoreLabel);
        uiTable.row();
        uiTable.add(gameOverMessage).expandY();

        enemyTimer = 0;
        enemySpeed = 100;
        enemySpawnInterval = 3;

        gameOver = false;
        gameOverMessage = new BaseActor(0,0,uiStage);
        gameOverMessage.loadTexture("assets/game-over.png");
        gameOverMessage.setVisible(false);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/Prelude-and-Action.mp3"));
        sparkleSound   = Gdx.audio.newSound(Gdx.files.internal("assets/sparkle.mp3"));
        explosionSound   = Gdx.audio.newSound(Gdx.files.internal("assets/explosion.wav"));

        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1.00f);
        backgroundMusic.play();
    }

    public void update(float dt)
    {
        if (gameOver)
            return;

        starTimer += dt;
        if (starTimer > starSpawnInterval)
        {
            new Star( 800, MathUtils.random(100,500), mainStage );
            starTimer = 0;
        }

        for (BaseActor star : BaseActor.getList(mainStage, "Star"))
        {
            if (plane.overlaps(star))
            {
                Sparkle sp = new Sparkle(0,0,mainStage);
                sp.centerAtActor(star);
                sparkleSound.play();
                star.remove();
                score++;
                scoreLabel.setText( Integer.toString(score) );
            }
        }

        enemyTimer += dt;
        if (enemyTimer > enemySpawnInterval)
        {
            Enemy enemy = new Enemy( 800, MathUtils.random(100,500), mainStage );
            enemy.setSpeed(enemySpeed);

            enemyTimer = 0;
            enemySpawnInterval -= 0.10f;
            enemySpeed += 10;

            if (enemySpawnInterval < 0.5f)
                enemySpawnInterval = 0.5f;

            if (enemySpeed > 400)
                enemySpeed = 400;
        }

        for (BaseActor enemy : BaseActor.getList(mainStage, "Enemy"))
        {
            if (plane.overlaps(enemy))
            {
                Explosion ex = new Explosion(0,0,mainStage);
                ex.centerAtActor(plane);
                ex.setScale(3);
                explosionSound.play();
                backgroundMusic.stop();
                plane.remove();
                gameOver = true;
                gameOverMessage.setVisible(true);
            }

            if (enemy.getX() + enemy.getWidth() < 0)
            {
                score++;
                scoreLabel.setText( Integer.toString(score) );
                enemy.remove();
            }
        }

    }

    public boolean keyDown(int keyCode)
    {
        if (keyCode == Keys.SPACE) 
            plane.boost();

        return true;
    }
}