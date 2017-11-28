import java.util.Iterator;
import java.util.TreeMap;

/**
 * Field class. Represents the set of subfields within a field and their
 * corresponding column indices
 * 
 * @author Andrew H. Fagg
 * @version 2017-10-14
 *
 */
public class Field implements Iterable<String>
{
    /**
     * The set of subfields and their indices
     */
    private TreeMap<String, Integer> subFields;

    /**
     * Constructor.
     * 
     * Creates an empty subfield list
     */
    public Field()
    {
        subFields = new TreeMap<String, Integer>();
    }

    /**
     * Add a new subfield to this field
     * 
     * @param subFieldName
     *            Name of the subfield
     * @param columnIndex
     *            Column index of the subfield
     */
    public void addSubField(String subFieldName, int columnIndex)
    {
        subFields.put(subFieldName, columnIndex);
    }

    /**
     * Given a subfield name, return the corresponding index
     * 
     * @param subFieldName
     *            Name of the subfield in question
     * @return The Integer index of the column, or null if there is no subfield
     *         by this name
     * 
     */
    public Integer getIndex(String subFieldName)
    {
        if (subFields.containsKey(subFieldName))
        {
            return subFields.get(subFieldName);
        }
        return null;
    }

    /**
     * String describing this subfields of this field
     * 
     * @return String in the format for each subfield: "subfield(index); "
     */
    public String toString()
    {
        String out = "";

        // Loop over every subfield name
        for (String s : this)
        {
            out = out + s + "(" + subFields.get(s) + "); ";
        }

        return out;

    }

    /**
     * Return the number of subfields
     * 
     * @return The number of subfields
     */
    public int size()
    {
        return subFields.size();
    }

    /**
     * Return the iterator for the subfields
     * 
     * @return Iterator over the Subfield keys
     */
    @Override
    public Iterator<String> iterator()
    {
        return subFields.keySet().iterator();
    }
}