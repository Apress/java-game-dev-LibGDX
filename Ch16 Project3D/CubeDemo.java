import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.math.Vector3;

public class CubeDemo implements ApplicationListener 
{
    public Environment environment;
    public PerspectiveCamera camera;

    public ModelBatch modelBatch;
    public ModelInstance boxInstance;

    public void create() 
    {
        environment = new Environment();
        environment.set( new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f) );

        DirectionalLight dLight = new DirectionalLight();
        Color     lightColor = new Color(0.75f, 0.75f, 0.75f, 1);
        Vector3  lightVector = new Vector3(-1.0f, -0.75f, -0.25f);
        dLight.set( lightColor, lightVector );
        environment.add( dLight ) ;

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far  = 300f;
        camera.update();

        ModelBuilder modelBuilder = new ModelBuilder();

        Material boxMaterial = new Material();
        boxMaterial.set( ColorAttribute.createDiffuse(Color.BLUE) );

        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal;

        Model boxModel = modelBuilder.createBox( 5f, 5f, 5f, boxMaterial, usageCode );
        boxInstance = new ModelInstance(boxModel);

        modelBatch = new ModelBatch();
    }

    public void render() 
    {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        modelBatch.begin(camera);
        modelBatch.render( boxInstance, environment );
        modelBatch.end();
    }

    public void dispose() {	}

    public void resize(int width, int height) {	}

    public void pause() {}

    public void resume() {}
}