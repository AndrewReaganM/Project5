import java.awt.Color;

/**
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 10-28-17 Project5
 *
 */
public class KinematicPointState extends KinematicPointAbstract
{
    /**
     * @param color
     * @param width
     * @param fieldName
     */
    public KinematicPointState(Color color, float width, String fieldName)
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
        
    }
}
