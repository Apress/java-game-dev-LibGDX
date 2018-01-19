import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Box extends BaseActor3D
{
    public Box(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new Material();
        
        int usageCode = Usage.Position + Usage.ColorPacked + Usage.Normal + Usage.TextureCoordinates;

        Model boxModel = modelBuilder.createBox(1,1,1, boxMaterial, usageCode);
        Vector3 position = new Vector3(0,0,0);

        setModelInstance( new ModelInstance(boxModel, position) );
    }
}