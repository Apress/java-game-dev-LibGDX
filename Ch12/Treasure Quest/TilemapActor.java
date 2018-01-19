import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Loads a Tiled map file (*.tmx) with a given filename
 *  and adds it to a stage (so that the tilemap automatically renders).
 */ 
public class TilemapActor extends Actor 
{
    // window dimensions
    public static int windowWidth  = 800;
    public static int windowHeight = 600;

    private TiledMap tiledMap;
    private OrthographicCamera tiledCamera;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;

    /**
     *  Loads a Tiled map file (*.tmx) with a given filename
     *  and adds it to a stage (so that the tilemap automatically renders).
     */
    public TilemapActor(String filename, Stage theStage)
    {
        // set up tile map, renderer, and camera
        tiledMap = new TmxMapLoader().load(filename);

        int tileWidth          = (int)tiledMap.getProperties().get("tilewidth");
        int tileHeight         = (int)tiledMap.getProperties().get("tileheight");
        int numTilesHorizontal = (int)tiledMap.getProperties().get("width");
        int numTilesVertical   = (int)tiledMap.getProperties().get("height");
        int mapWidth  = tileWidth  * numTilesHorizontal;
        int mapHeight = tileHeight * numTilesVertical;

        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        tiledMapRenderer.setBlending(true);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, windowWidth, windowHeight);
        tiledCamera.update();

        // by adding object to Stage, can be drawn automatically
        theStage.addActor(this);

        // in theory, a solid boundary should be placed around the edge of the screen, 
        //  but just in case, this map can be used to set boundaries
        BaseActor.setWorldBounds(mapWidth, mapHeight);
    }

    /**
     *  Search the map layers for Rectangle Objects that contain a property (key) called "name" with associated value propertyName;
     *  typically used to store non-actor information such as spawn point locations or dimensions of Solid objects.
     *  Retrieve data as object, then cast to desired type: for example, float w = (float)obj.getProperties().get("width").
     */
    public ArrayList<MapObject> getRectangleList(String propertyName)
    {
        ArrayList<MapObject> list = new ArrayList<MapObject>();

        for ( MapLayer layer : tiledMap.getLayers() )
        {
            for ( MapObject obj : layer.getObjects() )
            {
                if ( !(obj instanceof RectangleMapObject) )
                    continue;

                MapProperties props = obj.getProperties();

                if ( props.containsKey("name") && props.get("name").equals(propertyName) )
                    list.add(obj);
            }
        }
        return list;
    }

    /**
     *  Search the map layers for Tile Objects (tile-like elements of object layers)
     *  that contain a property (key) called "name" with associated value propertyName;
     *  typically used to store actor information and will be used to create instances. 
     */
    public ArrayList<MapObject> getTileList(String propertyName)
    {
        ArrayList<MapObject> list = new ArrayList<MapObject>();

        for ( MapLayer layer : tiledMap.getLayers() )
        {
            for ( MapObject obj : layer.getObjects() )
            {
                if ( !(obj instanceof TiledMapTileMapObject) )
                    continue;

                MapProperties props = obj.getProperties();

                // Default MapProperties are stored within associated Tile object
                // Instance-specific overrides are stored in MapObject

                TiledMapTileMapObject tmtmo = (TiledMapTileMapObject)obj;
                TiledMapTile t = tmtmo.getTile();
                MapProperties defaultProps = t.getProperties();

                if ( defaultProps.containsKey("name") && defaultProps.get("name").equals(propertyName) )
                    list.add(obj);

                // get list of default property keys
                Iterator<String> propertyKeys = defaultProps.getKeys();

                // iterate over keys; copy default values into props if needed
                while ( propertyKeys.hasNext() )
                {
                    String key = propertyKeys.next();

                    // check if value already exists; if not, create property with default value
                    if ( props.containsKey(key) )
                        continue;
                    else
                    {
                        Object value = defaultProps.get(key);
                        props.put( key, value );
                    }
                }
            }
        }
        return list;
    }

    /**
     *  Automatically called by Stage.
     */
    public void act(float dt)
    { 
        super.act( dt );
    }

    /**
     *   Automatically called by Stage; keeps the map camera position in
     *   sync with the stage camera, and renders the map to the screen.
     */
    public void draw(Batch batch, float parentAlpha) 
    {
        // adjust tilemap camera to stay in sync with main camera
        Camera mainCamera = getStage().getCamera();
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);

        // need the following code to force batch order,
        //  otherwise it is batched and rendered last
        batch.end();
        tiledMapRenderer.render();        
        batch.begin();
    }
}