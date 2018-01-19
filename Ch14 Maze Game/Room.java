import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

public class Room extends BaseActor
{
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST  = 2;
    public static final int WEST  = 3;
    public static int[] directionArray = {NORTH, SOUTH, EAST, WEST};

    private Wall[] wallArray;
    private Room[] neighborArray; // some may be null!

    private boolean connected;  // used in maze generation
    private boolean visited;    // used in pathfinding
    private Room previousRoom;  // used in pathfinding

    public Room(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/dirt.png");

        float w = 64;
        float h = 64;
        setSize(w, h);
        
        // t = wall thickness in pixels
        float t = 6;

        wallArray = new Wall[4];
        wallArray[SOUTH] = new Wall(x,y, w,t, s);
        wallArray[WEST]  = new Wall(x,y, t,h, s);
        wallArray[NORTH] = new Wall(x,y+h-t, w,t, s);
        wallArray[EAST]  = new Wall(x+w-t,y, t,h, s);

        neighborArray = new Room[4];
        // contents initialized by Maze class

        connected = false;
        visited = false;
    }

    public void setNeighbor(int direction, Room neighbor)
    {  neighborArray[direction] = neighbor;  }

    public boolean hasNeighbor(int direction)
    {  return neighborArray[direction] != null;  }

    public Room getNeighbor(int direction)
    {  return neighborArray[direction];  }

    // check if wall in this direction still exists
    public boolean hasWall(int direction)
    {  return wallArray[direction].getStage() != null;  }

   
    public void removeWalls(int direction)
    {
        removeWallsBetween( neighborArray[direction] );
    }

    public void removeWallsBetween(Room other)
    {
        if (other == neighborArray[NORTH])
        {
            this.wallArray[NORTH].remove();
            other.wallArray[SOUTH].remove();
        }
        else if (other == neighborArray[SOUTH])
        {
            this.wallArray[SOUTH].remove();
            other.wallArray[NORTH].remove();
        }
        else if (other == neighborArray[EAST])
        {
            this.wallArray[EAST].remove();
            other.wallArray[WEST].remove();
        }
        else // (other == neighborArray[WEST])
        {
            this.wallArray[WEST].remove();
            other.wallArray[EAST].remove();
        }
    }

    public void setConnected(boolean b)
    {  connected = b;  }

    public boolean isConnected()
    {  return connected;  }
    
     public boolean hasUnconnectedNeighbor()
    {
        for (int direction : directionArray)
        {
            if ( hasNeighbor(direction) && !getNeighbor(direction).isConnected() )
                return true;
        }
        return false;
    }

    public Room getRandomUnconnectedNeighbor()
    {
        ArrayList<Integer> directionList = new ArrayList<Integer>();

        for (int direction : directionArray)
        {
            if ( hasNeighbor(direction) && !getNeighbor(direction).isConnected() )
                directionList.add(direction);
        }

        int directionIndex = (int)Math.floor( Math.random() * directionList.size() );
        int direction = directionList.get(directionIndex);
        return getNeighbor(direction);
    }

    public void setVisited(boolean b)
    {  visited = b;  }

    public boolean isVisited()
    {  return visited;  }
    
     public void setPreviousRoom(Room r)
    {  previousRoom = r;  }

    public Room getPreviousRoom()
    {  return previousRoom;  }
    
    // Used in pathfinding: locate accesible neighbors that have not yet been visited
    public ArrayList<Room> unvisitedPathList()
    {
        ArrayList<Room> list = new ArrayList<Room>();

        for (int direction : directionArray)
        {
            if ( hasNeighbor(direction) && !hasWall(direction) && !getNeighbor(direction).isVisited() )
                list.add( getNeighbor(direction) );
        }

        return list;
    }
}