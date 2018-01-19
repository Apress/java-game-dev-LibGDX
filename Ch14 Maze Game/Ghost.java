import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import java.util.ArrayList;

public class Ghost extends BaseActor
{    
    public float speed = 60; // pixels per second

    public Ghost(float x, float y, Stage s)
    {
        super(x,y,s); 
        loadAnimationFromSheet("assets/ghost.png", 1,3, 0.2f, true);
        setOpacity(0.8f);
    }

    public void findPath(Room startRoom, Room targetRoom)
    {
        Room currentRoom = startRoom;

        ArrayList<Room> roomList = new ArrayList<Room>();
        currentRoom.setPreviousRoom( null );
        currentRoom.setVisited( true );
        roomList.add(currentRoom);

        while (roomList.size() > 0)
        {
            currentRoom = roomList.remove(0); 
            for (Room nextRoom : currentRoom.unvisitedPathList())
            {
                nextRoom.setPreviousRoom( currentRoom );
                if (nextRoom == targetRoom)
                {
                    // target found!
                    roomList.clear();
                    break;
                }
                else
                {
                    nextRoom.setVisited( true );
                    roomList.add(nextRoom);
                }
            }
        }

        // create list of rooms corresponding to shortest path
        ArrayList<Room> pathRoomList = new ArrayList<Room>();
        currentRoom = targetRoom;
        
        while (currentRoom != null)
        {
            // add current room to beginning of list
            pathRoomList.add( 0, currentRoom );
            currentRoom = currentRoom.getPreviousRoom();
        }

        // only move along a few steps of the path;
        //   path will be recalculated these actions are complete.
        int maxStepCount = 2;
        
        // to remove the pause between steps, start loop index at 1
        //   but make ghost speed slower to compensate
        for (int i = 0; i < pathRoomList.size(); i++)
        {
            if (i == maxStepCount)
                break;
            Room nextRoom = pathRoomList.get(i);
            Action move = Actions.moveTo( nextRoom.getX(), nextRoom.getY(), 64/speed );
            addAction( move );
        }
    }
}