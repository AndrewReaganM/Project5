import java.util.Iterator;
import java.util.TreeMap;

/**
 * This class captures the relationship between columns in a data file and
 * organization of the PointND structure.
 * 
 * The column headers in a data file determine the field and subfield of that
 * column. Column names come in one of two forms:
 * 
 * yyyyyy_z where yyyyyy is the field name and z is the subfield name. Subfield
 * names are necessarily one character and an underscore character must sit
 * between the field and subfield names
 * 
 * yyyyyy where yyyyyy is the field name and "" is the subfield name
 * 
 * This class represents a set of fields. Each field has a representation of the
 * set of subfields. Each field/subfield maps back to the column index where the
 * value can b drawn from.
 * 
 * @author Andrew H. Fagg
 * @version 2017-10-14
 *
 */
public class FieldMapper implements Iterable<String>
{
    /** Starting point for this field in the list. */
    private TreeMap<String, Field> fieldMap;

    /**
     * Constructor.
     * 
     * Creates a new field for each unique field. Places subfields within these
     * fields.
     * 
     * @param columnNames
     *            A list of column names
     */
    public FieldMapper(String[] columnNames)
    {
        this.fieldMap = new TreeMap<String, Field>();

        // Loop over all variables
        for (int i = 0; i < columnNames.length; ++i)
        {
            String fieldName = columnNames[i];
            String subFieldName = "scalar";

            if (fieldName.charAt(fieldName.length() - 2) == '_')
            {
                // Subfield is the last character
                subFieldName = fieldName.substring(fieldName.length() - 1, fieldName.length());
                // Strip off last 2 chars
                fieldName = fieldName.substring(0, fieldName.length() - 2);

            }

            // Find the field that corresponds to this name
            Field field = fieldMap.get(fieldName);
            if (field == null)
            {
                // There isn't one: create it and insert it
                field = new Field();
                fieldMap.put(fieldName, field);
            }
            field.addSubField(subFieldName, i);

            // System.out.println("Field:" + fieldName + "-" + subFieldName);
        }
    }

    /**
     * Return the field for the corresponding fieldName
     * 
     * @param fieldName
     *            Name of the field
     * 
     * @return Field that corresponds to fieldName; null if there is none
     */
    public Field getField(String fieldName)
    {
        return fieldMap.get(fieldName);
    }

    /**
     * Iterator over field names
     * 
     * @return Iterator over the field names
     */
    @Override
    public Iterator<String> iterator()
    {
        return fieldMap.keySet().iterator();
    }

    /**
     * Given a list of column values and the name of a field, return the
     * corresponding PointND
     * 
     * @param strgs
     *            Array of Strings, one corresponding to each column
     * @param fieldName
     *            Name of the field
     * 
     * @return Populated PointND object with the correct values; null if the
     *         fieldName does not exist
     */

    public PointND extractPointND(String[] strgs, String fieldName)
    {
        // Find the
        Field field = this.getField(fieldName);

        if (field == null)
        {
            // Punt: there is no field
            return null;
        }

        // New object to return
        PointND point = new PointND();

        // Loop over the subfields.
        for (String subFieldName : field)
        {
            // Extract the column value and insert it into the list
            point.add(subFieldName, new GeneralValue(strgs[field.getIndex(subFieldName)]));
        }

        return point;
    }

    /*
     * Return a String describing the FieldMapper object
     * 
     * @return String describing the mapper object
     */
    /*
     * public String toString() { String out = "";
     * 
     * // Loop over all of the fields for(String s: this) { out = out + s + " ("
     * + fieldMap.get(s).toString() + "),,\n"; }
     * 
     * return out; }
     */
    /**
     * Return the number of Fields
     * 
     * @return Return the number of fields
     */
    public int size()
    {
        return fieldMap.size();
    }

}