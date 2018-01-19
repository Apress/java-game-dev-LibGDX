public class Turtle extends ObjModel
{
    public Turtle(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        loadObjModel("assets/turtle.obj");
        setBasePolygon();
    }
}