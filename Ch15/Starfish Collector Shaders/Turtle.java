import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Turtle extends BaseActor
{
    String vertexShaderCode;
    String fragmentShaderCode;
    ShaderProgram shaderProgram;
    float time;
    
    public Turtle(float x, float y, Stage s)
    {
        super(x,y,s);

        String[] filenames = 
            {"assets/turtle-1.png", "assets/turtle-2.png", "assets/turtle-3.png", 
                "assets/turtle-4.png", "assets/turtle-5.png", "assets/turtle-6.png"};

        loadAnimationFromFiles(filenames, 0.1f, true);

        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);

        setBoundaryPolygon(8);

        vertexShaderCode   = Gdx.files.internal("assets/shaders/default.vs").readString();
        fragmentShaderCode = Gdx.files.internal("assets/shaders/default.fs").readString();
        shaderProgram = new ShaderProgram(vertexShaderCode, fragmentShaderCode);

        // to detect errors in GPU compilation
        if (!shaderProgram.isCompiled()) 
           System.out.println( "Shader compile error: " + shaderProgram.getLog() );

        time = 0;
    }

    public void act(float dt)
    {
        super.act( dt );

        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            accelerateAtAngle(180);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerateAtAngle(0);
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            accelerateAtAngle(90);
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            accelerateAtAngle(270);

        applyPhysics(dt);

        setAnimationPaused( !isMoving() );

        if ( getSpeed() > 0 )
            setRotation( getMotionAngle() );

        boundToWorld();

        alignCamera();

        // update time value to pass to shader
        time += dt;
    }

    public void draw(Batch batch, float parentAlpha) 
    {
        batch.setShader(shaderProgram);

        // note: if variable is not used in shader, it is removed when compiled
        //   and attempting to set its value creates an error.
        
        // shaderProgram.setUniformf("u_time", time);
        
        // shaderProgram.setUniformf("u_imageSize", new Vector2(getWidth(), getHeight()) );
        
        // shaderProgram.setUniformf("u_borderColor", Color.BLACK);
        // shaderProgram.setUniformf("u_borderSize", 0);

        // shaderProgram.setUniformi("u_blurRadius", 5);
        
        // shaderProgram.setUniformi("u_glowRadius", 5);
        
        // shaderProgram.setUniformf("u_amplitude",  new Vector2( 2,  3) );
        // shaderProgram.setUniformf("u_wavelength", new Vector2(17, 19) );
        // shaderProgram.setUniformf("u_velocity",   new Vector2(10, 11) );
        
        super.draw( batch, parentAlpha );

        batch.setShader(null);
    }
}