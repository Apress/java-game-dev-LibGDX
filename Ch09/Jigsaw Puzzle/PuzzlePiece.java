import com.badlogic.gdx.scenes.scene2d.Stage;

public class PuzzlePiece extends DragAndDropActor
{
    private int row;
    private int col;

    private PuzzleArea puzzleArea;

    public PuzzlePiece(float x, float y, Stage s)
    {
        super(x,y,s);
    }

    public void setRow(int r)
    {  row = r;  }

    public void setCol(int c)
    {  col = c;  }

    public int getRow()
    {  return row;  }

    public int getCol()
    {  return col;  }

    public void setPuzzleArea(PuzzleArea pa)
    {  puzzleArea = pa;  }

    public PuzzleArea getPuzzleArea()
    {  return puzzleArea;  }

    public void clearPuzzleArea()
    {  puzzleArea = null;  }
    
    public boolean hasPuzzleArea()
    {  return puzzleArea != null;  }

    // override method from DragAndDropActor class
    public void onDragStart()
    {
        if ( hasPuzzleArea() )
        {
            PuzzleArea pa = getPuzzleArea();
            pa.setTargetable(true);
            clearPuzzleArea();
        }
    }
    
    // override method from DragAndDropActor class
    public void onDrop()
    {
        if ( hasDropTarget() )
        {
            PuzzleArea pa = (PuzzleArea)getDropTarget();
            moveToActor(pa);
            setPuzzleArea(pa);
            pa.setTargetable(false);
        }
    }
    
    public boolean isCorrectlyPlaced()
    {  
        return hasPuzzleArea() 
        && this.getRow() == puzzleArea.getRow()
        && this.getCol() == puzzleArea.getCol();
    }
}