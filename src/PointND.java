import java.util.Iterator;
import java.util.TreeMap;

/**
 * Point 3D class
 * 
 * Captures 3D position in Cartesian space. Individual coordinate values may or
 * may not be valid
 * 
 * @author Andrew H. Fagg
 * @version 2017-09-03
 *
 */
public class PointND implements Iterable<String>
{
    /** The values to be stored */
    private TreeMap<String, GeneralValue> values;

    /**
     * Constructor: starts with no values
     * 
     */
    public PointND()
    {
        values = new TreeMap<String, GeneralValue>();

    }

    /**
     * Add a Value to PointND
     * 
     * @param subFieldName
     *            The name of the subfield associated with value
     * @param value
     *            The value
     */
    public void add(String subFieldName, GeneralValue value)
    {
        values.put(subFieldName, value);
    }

    /**
     * Returns the specified coordinate value
     * 
     * @param subFieldName
     *            String subfield name
     * 
     * @return The value associated with the specified dimension
     */
    public GeneralValue getValue(String subFieldName)
    {
        GeneralValue value = values.get(subFieldName);
        if (value == null)
        {
            return new GeneralValue();
        }
        return value;
    }

    /**
     * Number of dimensions in the point
     * 
     * @return Number of dimensions in the point
     */
    public int size()
    {
        return values.size();
    }

    /**
     * String describing the 3D position
     * 
     * @return String in the format of: x,y,z
     */

    public String toString()
    {
        String out = "";
        for (String subFieldName : this)
        {
            out += subFieldName + " = " + values.get(subFieldName) + "; ";
        }

        return out;
    }

    /**
     * Iterate over the subfields
     * 
     * @return Iterator over the subfield names
     */
    @Override
    public Iterator<String> iterator()
    {
        return this.values.keySet().iterator();
    }
}