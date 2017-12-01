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
     * @param color
     * @param width
     */
    public KinematicPointAbstract(Color color, float width)
    {
        this.color = color;
    }
    
    /**
     * @param child
     */
    public void addChild(KinematicPointAbstract child)
    {
        children.add(child);
    }
    
    /**
     * @param g
     * @param state
     * @param screenXSubfield
     * @param screenYSubfield
     */
    public void draw(Graphics2D g, State state, String screenXSubfield, String screenYSubfield)
    {
        //Implement Graphics
    }
    
    /**
     * Sets the scale variable defined above.
     * @param scale the current scale that needs to be set.
     */
    public static void setScale(double scale)
    {
        //Sets the static variable using this static method.
        KinematicPointAbstract.scale = scale;
    }
    
    /**
     * @param state Current state of the infant.
     * @param screenSubfield subfield of the screen
     * @return a GeneralValue object that contatins the current coordinate.
     */
    public abstract GeneralValue getScreenCoordinate(State state, String screenSubfield);
}
