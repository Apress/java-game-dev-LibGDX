import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class ObjModel extends BaseActor3D
{
    public ObjModel(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
    }
    
    public void loadObjModel(String fileName)
    {
        ObjLoader loader = new ObjLoader();
        Model objModel = loader.loadModel(Gdx.files.internal(fileName), true);
        setModelInstance( new ModelInstance(objModel) );
    }
}