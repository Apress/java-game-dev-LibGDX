import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Sphere extends BaseActor3D
{
    public Sphere(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        ModelBuilder modelBuilder = new ModelBuilder();
        Material mat = new Material();

        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal + Usage.TextureCoordinates;
        int r = 1;
        Model mod = modelBuilder.createSphere(r,r,r, 32,32, mat, usageCode);
        Vector3 pos = new Vector3(0,0,0);

        setModelInstance( new ModelInstance(mod, pos) );
    }
}