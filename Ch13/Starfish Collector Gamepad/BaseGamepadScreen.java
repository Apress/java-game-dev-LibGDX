import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public abstract class BaseGamepadScreen extends BaseScreen implements ControllerListener
{
    public BaseGamepadScreen()
    {
        super();
        Controllers.clearListeners();
        Controllers.addListener(this);
    }
    
    // methods required by ControllerListener interface
    //  enable discrete input processing

    public void connected(Controller controller) 
    {  }

    public void disconnected(Controller controller) 
    {  }

    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) 
    {  return false;  }

    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value)
    {  return false;  }

    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value)
    {  return false;  }

    public boolean povMoved(Controller controller, int povCode, PovDirection value)
    {  return false;  }

    public boolean axisMoved(Controller controller, int axisCode, float value)
    {  return false;  }

    public boolean buttonDown(Controller controller, int buttonCode)
    {  return false;  }

    public boolean buttonUp(Controller controller, int buttonCode)
    {  return false;  }
}