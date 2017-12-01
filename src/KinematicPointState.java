import java.awt.Color;

/**
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 10-28-17 Project5
 *
 */
public class KinematicPointState extends KinematicPointAbstract
{
	/** Stores the fieldname as a string */
	private String fieldName;
	
    /**
     * @param color
     * @param width
     * @param fieldName
     */
    public KinematicPointState(Color color, float width, String fieldName)
    {
        //Call the super constructor
        super(color, width);
        this.fieldName =  fieldName;
    }
    
    /**
     * @param state 
     * @param screenSubfield
     * @return
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
        return state.getValue(fieldName, screenSubfield);
    }
}
