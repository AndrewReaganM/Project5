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
     * Default constructor. Creates the constant kinematic points.
     * @param color Color of the given point.
     * @param width Width of the given point.
     * @param x value for dimension
     * @param y value for dimension
     * @param z value for dimension
     */
    public KinematicPointConstant(Color color, float width, double x, double y, double z)
    {
      //Call the super constructor
        super(color, width);
        
        //Create the new PointND.
        point = new PointND();
        //Add x
        point.add("x", new GeneralValue(x));
        //Add y
        point.add("y", new GeneralValue(y));
        //Add z
        point.add("z", new GeneralValue(z));
        
    }
    
    /**
     * @param state the State that the points are contained in.
     * @param screenSubfield the x, y, or z subfield.
     * @return a GeneralValue containing the point location.
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
    	return point.getValue(screenSubfield);
    }
    
}
