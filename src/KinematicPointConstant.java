import java.awt.Color;

/**
 * @author Andrew Massey
 * @author Luke Barnes
 *
 */
public class KinematicPointConstant extends KinematicPointAbstract
{
    /**  */
    private  PointND point;
    
    /**
     * @param color
     * @param width
     * @param x
     * @param y
     * @param z
     */
    public KinematicPointConstant(Color color, float width, double x, double y, double z)
    {
      //Call the super constructor
        super(color, width);
        
        //Instantiate the new PointND
        point = new PointND();
        //Add x
        point.add("x", new GeneralValue(x));
        //Add y
        point.add("y", new GeneralValue(y));
        //Add z
        point.add("z", new GeneralValue(z));
        
    }
    
    /**
     * @param state State is unused in this context.
     * @param screenSubfield the subfield of the given point requested.
     * @return a GeneralValue representing the point determined by the subfield.
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
        //Returns the subfield of the point. The point is guaranteed to exist due to the class structure.
        return point.getValue(screenSubfield);
    }
    
}
