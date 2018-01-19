import com.badlogic.gdx.scenes.scene2d.Stage;

public class PuzzleArea extends DropTargetActor
{
    private int row;
    private int col;
    
    public PuzzleArea(float x, float y, Stage s)
    {
        super(x,y,s);
        loadTexture("assets/border.jpg");
    }
    
    public void setRow(int r)
    {  row = r;  }
    
    public void setCol(int c)
    {  col = c;  }
    
    public int getRow()
    {  return row;  }
    
    public int getCol()
    {  return col;  }
}