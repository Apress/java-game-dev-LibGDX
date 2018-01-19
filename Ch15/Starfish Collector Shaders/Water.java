import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Water extends BaseActor
{
    String vertexShader;
    String fragmentShader;
    ShaderProgram shaderProgram;
    float time;

    public Water(float x, float y, Stage s)
    { 
        super(x,y,s);
        loadTexture("assets/water-border.jpg");  
        setSize(1200,900);
        setWorldBounds(this);

        vertexShader = Gdx.files.internal("assets/shaders/default.vs").readString();
        fragmentShader = Gdx.files.internal("assets/shaders/wave.fs").readString();
        shaderProgram = new ShaderProgram(vertexShader,fragmentShader);

        // to detect errors in GPU compilation
        if (!shaderProgram.isCompiled()) 
            System.out.println( "Couldn't compile shader: " + shaderProgram.getLog() );

        time = 0;
    }    

    public void act(float dt)
    {
        super.act(dt);
        time += dt;
    }
    
    public void draw(Batch batch, float parentAlpha) 
    {
        batch.setShader(shaderProgram);
        shaderProgram.setUniformf("u_time", time);
        shaderProgram.setUniformf("u_imageSize",  new Vector2(getWidth(), getHeight()) );
        shaderProgram.setUniformf("u_amplitude",  new Vector2(2,3) );
        shaderProgram.setUniformf("u_wavelength", new Vector2(101, 103) );
        shaderProgram.setUniformf("u_velocity",   new Vector2(50, 51) );
        super.draw( batch, parentAlpha );
        batch.setShader(null);
    }
}