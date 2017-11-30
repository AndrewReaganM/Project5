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
    }
    
    /**
     * @param state
     * @param screenSubfield
     * @return
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
        InfantFrame.this
    }
    
}
