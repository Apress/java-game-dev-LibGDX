import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class Starfish extends BaseActor
{
    public boolean collected;

    String vertexShader;
    String fragmentShader;
    ShaderProgram shaderProgram;
    float time;

    public Starfish(float x, float y, Stage s)
    {
        super(x,y,s);

        loadTexture("assets/starfish.png");

        rotateBy( MathUtils.random(360) );
        Action spin = Actions.rotateBy( MathUtils.random(20,40), 1);
        this.addAction( Actions.forever(spin) );   

        setBoundaryPolygon(8);

        collected = false;

        vertexShader = Gdx.files.internal("assets/shaders/default.vs").readString();
        fragmentShader = Gdx.files.internal("assets/shaders/glow-pulse.fs").readString();
        shaderProgram = new ShaderProgram(vertexShader,fragmentShader);

        // to detect errors in GPU compilation
        if (!shaderProgram.isCompiled()) 
            System.out.println( "Couldn't compile shader: " + shaderProgram.getLog() );

        time = 0;
    }

    public void act(float dt)
    {
        super.act( dt );
        time += dt;
    }

    public void draw(Batch batch, float parentAlpha) 
    {
        batch.setShader(shaderProgram);
        shaderProgram.setUniformf("u_time", time);
        shaderProgram.setUniformf("u_imageSize", new Vector2(getWidth(), getHeight()) );
        shaderProgram.setUniformi("u_glowRadius", 5);
        super.draw( batch, parentAlpha );
        batch.setShader(null);
    }

}