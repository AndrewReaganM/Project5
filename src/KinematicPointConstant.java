import java.awt.Color;

/**
 * @author Andrew Massey
 * @author Luke Barnes
 *
 */
public class KinematicPointConstant extends KinematicPointAbstract
{
    /** Variable where the constant point is stored */
    private PointND point;

    /**
     * Default constructor. Creates the constant kinematic points.
     * 
     * @param color
     *            Color of the given point.
     * @param width
     *            Width of the given point.
     * @param x
     *            value for dimension
     * @param y
     *            value for dimension
     * @param z
     *            value for dimension
     */
    public KinematicPointConstant(Color color, float width, double x, double y, double z)
    {
        // Call the super constructor
        super(color, width);

        // Create the new PointND.
        point = new PointND();
        // Add x
        point.add("x", new GeneralValue(x));
        // Add y
        point.add("y", new GeneralValue(y));
        // Add z
        point.add("z", new GeneralValue(z));

    }

    /**
     * @param state
     *            State is unused in this context.
     * @param screenSubfield
     *            the subfield of the given point requested.
     * @return a GeneralValue representing the point determined by the subfield.
     */
    public GeneralValue getScreenCoordinate(State state, String screenSubfield)
    {
        // Returns the subfield of the point. The point is guaranteed to exist
        // due to the class structure.
        return point.getValue(screenSubfield);
    }

}
