import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11-28-17 Project5
 *
 */
public abstract class KinematicPointAbstract
{
    /**  */
    private ArrayList<KinematicPointAbstract> children;
    /**  */
    private Color color;
    /**  */
    private BasicStroke stroke;
    /**  */
    private static double scale;

    /**
     * Constructor for the point.
     * 
     * @param color
     *            a Color object that defines the color.
     * @param width
     *            the width of the point
     */
    public KinematicPointAbstract(Color color, float width)
    {
        // Create the ArrayList
        children = new ArrayList<KinematicPointAbstract>();
        // Set the color variable.
        this.color = color;
        // Set the width of the stroke.
        stroke = new BasicStroke(width);

    }

    /**
     * Adds a passed in KinematicPointAbstract instance to an arraylist.
     * 
     * @param child
     *            KinematicPointAbstract instance to add to the arraylist.
     */
    public void addChild(KinematicPointAbstract child)
    {
        children.add(child);
    }

    /**
     * @param g
     *            graphics instance
     * @param state
     *            current state to be rendered
     * @param screenXSubfield
     * @param screenYSubfield
     */
    public void draw(Graphics2D g, State state, String screenXSubfield, String screenYSubfield)
    {
        // Grabs the GeneraValues representing the x and y values of this point.
        GeneralValue currentX = this.getScreenCoordinate(state, screenXSubfield);
        GeneralValue currentY = this.getScreenCoordinate(state, screenYSubfield);
        GeneralValue childX; // Declared outside of loop
        GeneralValue childY; // Declared outside of loop

        // Checks to make sure there are children
        if (!this.children.isEmpty())
        {
            // Iterates through each child.
            for (KinematicPointAbstract child : children)
            {
                // Grabs the GeneralValues for the child.
                childX = child.getScreenCoordinate(state, screenXSubfield);
                childY = child.getScreenCoordinate(state, screenYSubfield);

                // If all the GeneralValues are valid:
                if (currentX.isValid() && currentY.isValid() && childX.isValid() && childY.isValid())
                {
                    // Transform into pixel coordinates and draw a line between
                    // the two points.
                    // Use BasicStroke
                    g.setColor(color);
                    g.setStroke(stroke);
                    g.drawLine((int) (currentX.getDoubleValue() * scale), -(int) (currentY.getDoubleValue() * scale),
                            (int) (childX.getDoubleValue() * scale), -(int) (childY.getDoubleValue() * scale));
                }
                // TODO: Recursively draw.

            }

        }

    }

    /**
     * Sets the scale of the point.
     * 
     * @param scale
     *            double value representing the scale
     */
    public static void setScale(double scale)
    {
        // Set the scale of the point.
        KinematicPointAbstract.scale = scale;
    }

    /**
     * @param state
     *            Current state of the infant.
     * @param screenSubfield
     *            subfield of the screen
     * @return a GeneralValue object that contatins the current coordinate.
     */
    public abstract GeneralValue getScreenCoordinate(State state, String screenSubfield);
}
