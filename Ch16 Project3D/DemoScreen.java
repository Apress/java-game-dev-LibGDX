import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class DemoScreen extends BaseScreen
{
    BaseActor3D player;

    public void initialize()
    {    
        Box screen = new Box(0,0,0, mainStage3D);
        screen.setScale(16, 12, 0.1f);
        screen.loadTexture("assets/starfish-collector.png");
        
        Box markerO = new Box(0,0,0, mainStage3D);
        markerO.setColor(Color.BROWN);
        markerO.loadTexture("assets/crate.jpg");
        
        Box markerX = new Box(5,0,0, mainStage3D);
        markerX.setColor(Color.RED);
        markerX.loadTexture("assets/crate.jpg");
        
        Box markerY = new Box(0,5,0, mainStage3D);
        markerY.setColor(Color.GREEN);
        markerY.loadTexture("assets/crate.jpg");
        
        Box markerZ = new Box(0,0,5, mainStage3D);
        markerZ.setColor(Color.BLUE);
        markerZ.loadTexture("assets/crate.jpg");
       
        player = new Sphere(0,1,8, mainStage3D);
        player.loadTexture("assets/sphere-pos-neg.png");
       
        mainStage3D.setCameraPosition(3,4,10);
        mainStage3D.setCameraDirection(0,0,0);
    }

    public void update(float dt)
    {    
        float speed = 3.0f;
        float rotateSpeed = 45.0f;
        
        if ( !(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) )
        {
            if ( Gdx.input.isKeyPressed(Keys.W) )
                player.moveForward( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.S) )
                player.moveForward( -speed * dt ); 
            if ( Gdx.input.isKeyPressed(Keys.A) )
                player.moveRight( -speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.D) )
                player.moveRight( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.Q) )
                player.turnBy( -rotateSpeed * dt );   
            if ( Gdx.input.isKeyPressed(Keys.E) )
                player.turnBy( rotateSpeed * dt );
            if ( Gdx.input.isKeyPressed(Keys.R) )
                player.moveUp( speed * dt );
            if ( Gdx.input.isKeyPressed(Keys.F) )
                player.moveUp( -speed * dt );
        }

        if ( Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT) )
        {
            if (Gdx.input.isKeyPressed(Keys.W)) 
                mainStage3D.moveCameraForward( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.S)) 
                mainStage3D.moveCameraForward( -speed * dt );
            if (Gdx.input.isKeyPressed(Keys.A)) 
                mainStage3D.moveCameraRight( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.D)) 
                mainStage3D.moveCameraRight( -speed * dt );

            if (Gdx.input.isKeyPressed(Keys.R)) 
                mainStage3D.moveCameraUp( speed * dt );
            if (Gdx.input.isKeyPressed(Keys.F)) 
                mainStage3D.moveCameraUp( -speed * dt );

            if (Gdx.input.isKeyPressed(Keys.Q)) 
                mainStage3D.turnCamera(-rotateSpeed * dt);
            if (Gdx.input.isKeyPressed(Keys.E)) 
                mainStage3D.turnCamera(rotateSpeed * dt);
            
            if (Gdx.input.isKeyPressed(Keys.T)) 
                mainStage3D.tiltCamera(-rotateSpeed * dt); 
            if (Gdx.input.isKeyPressed(Keys.G)) 
                mainStage3D.tiltCamera(rotateSpeed * dt);
        }
    }
}