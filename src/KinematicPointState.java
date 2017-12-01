import java.awt.Color;

/**
 * This class stores point data from states. It extends KinematicPointAbstract.
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
     * Default constructor for KinematicPointState
     * @param color passes in the color of the line to be eventually drawn.
     * @param width the width of the line to be drawn
     * @param fieldName the name of the field represented by this point.
     */
    public KinematicPointState(Color color, float width, String fieldName)
    {
        // Call the super constructor
        super(color, width);
        this.fieldName = fieldName;
    }

    /**
     * Method that returns a GeneralValue representing the point requested.
     * @param state
     * @param screenSubfield
     * @return a GeneralValue object representing the point requested.
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
        return state.getValue(fieldName, screenSubfield);
    }
}
