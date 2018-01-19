public class HomeworkGame extends BaseGame
{
    public void create() 
    {        
        super.create();
        setActiveScreen( new MenuScreen() );
    }
}