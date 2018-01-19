import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;

import java.util.ArrayList;

public class BaseActor3D
{
    protected ModelInstance modelData;
    protected final Vector3 position;
    protected final Quaternion rotation;
    protected final Vector3 scale;
    protected Polygon boundingPolygon;
    protected Stage3D stage;
    
    public BaseActor3D(float x, float y, float z, Stage3D s)
    {
        modelData = null;
        position  = new Vector3(x,y,z);
        rotation  = new Quaternion();
        scale     = new Vector3(1,1,1);
        boundingPolygon = null;
        stage = s;
        s.addActor(this);
    }

    public void setModelInstance(ModelInstance m)
    {  modelData = m;  }

    public Matrix4 calculateTransform()
    {  return new Matrix4(position, rotation, scale);  }

    public void act(float dt)
    {  modelData.transform.set( calculateTransform() );  }

    public void draw(ModelBatch batch, Environment env)
    {  batch.render(modelData, env);  } 
    
    public void setColor(Color c)
    {  
        for (Material m : modelData.materials)
            m.set( ColorAttribute.createDiffuse(c) ); 
    }
    
    public void loadTexture(String fileName)
    {
        Texture tex = new Texture(Gdx.files.internal(fileName), true);
        tex.setFilter( TextureFilter.Linear, TextureFilter.Linear );
        
        for (Material m : modelData.materials)
            m.set( TextureAttribute.createDiffuse(tex) ); 
    }
    
    public Vector3 getPosition()
    {  return position;  }

    public void setPosition(Vector3 v)
    {  position.set(v);  }
    public void setPosition(float x, float y, float z)
    {  position.set(x,y,z);  }

    public void moveBy(Vector3 v)
    {  position.add(v);  }
    public void moveBy(float x, float y, float z)
    {  moveBy( new Vector3(x,y,z) );  }

    public void moveForward(float dist)
    {  moveBy( rotation.transform( new Vector3(0,0,-1) ).scl( dist ) );  }
    public void moveUp(float dist)
    {  moveBy( rotation.transform( new Vector3(0,1,0) ).scl( dist ) );  }
    public void moveRight(float dist)
    {  moveBy( rotation.transform( new Vector3(1,0,0) ).scl( dist ) );  }

    public float getTurnAngle()
    {   return rotation.getAngleAround(0,-1,0);  }
    public void setTurnAngle(float degrees)
    {  rotation.set( new Quaternion(Vector3.Y, degrees) );  }
    public void turnBy(float degrees)
    {  rotation.mul( new Quaternion(Vector3.Y, -degrees) );  }
    
    public void setScale(float x, float y, float z)
    {  scale.set(x,y,z);  }

    // 2D collision detection
    public void setBaseRectangle()
    {
        BoundingBox modelBounds = modelData.calculateBoundingBox( new BoundingBox() );
        Vector3 max = modelBounds.max;
        Vector3 min = modelBounds.min;

        float[] vertices = 
            {max.x, max.z, min.x, max.z, min.x, min.z, max.x, min.z};
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(0,0);
    }

    public void setBasePolygon()
    {
        BoundingBox modelBounds = modelData.calculateBoundingBox( new BoundingBox() );
        Vector3 max = modelBounds.max;
        Vector3 min = modelBounds.min;

        float a = 0.75f; // offset amount.
        float[] vertices = 
            {max.x,0, a*max.x,a*max.z, 0,max.z, a*min.x,a*max.z,
             min.x,0, a*min.x,a*min.z, 0,min.z, a*max.x,a*min.z };
        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(0,0);
    }

    public Polygon getBoundaryPolygon()
    {
        boundingPolygon.setPosition( position.x, position.z );
        boundingPolygon.setRotation( getTurnAngle() );
        boundingPolygon.setScale( scale.x, scale.z );
        return boundingPolygon;
    }

    public boolean overlaps(BaseActor3D other)
    {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) )
            return false;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        
        return Intersector.overlapConvexPolygons(poly1, poly2, mtv);
    }

    public void preventOverlap(BaseActor3D other)
    {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // initial test to improve performance
        if ( !poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()) )
            return;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if ( polygonOverlap )
            this.moveBy( mtv.normal.x * mtv.depth, 0, mtv.normal.y * mtv.depth );
    }

    public void remove()
    {
        stage.removeActor(this);
    }
    
    public static ArrayList<BaseActor3D> getList(Stage3D stage, String className)
    {
        ArrayList<BaseActor3D> list = new ArrayList<BaseActor3D>();

        Class theClass = null;
        try
        {  theClass = Class.forName(className);  }
        catch (Exception error)
        {  error.printStackTrace();  }

        for (BaseActor3D ba3d : stage.getActors())
        {
            if ( theClass.isInstance( ba3d ) )
                list.add(ba3d);
        }

        return list;
    }
    
    public static int count(Stage3D stage, String className)
    {
        return getList(stage, className).size();
    }

}
