public class RecorderGame extends BaseGame
{
    public void create() 
    {        
        super.create();
        setActiveScreen( new RecorderScreen() );
    }
}