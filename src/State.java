import java.util.Iterator;
import java.util.TreeMap;

/**
 * Representation of the state of a single point in time, within a given Trial.
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class State extends SingleItemAbstract
{
    /**
     * A TreeMap representing all of the PointND objects associated with the
     * state.
     */
    private TreeMap<String, PointND> variables;

    /** Reference to the current trial that the State is contained in. */
    private Trial trial;

    /**
     * Default State Constructor creates a TreeMap of Strings to PointND objects
     */
    public State()
    {
        // Initializes the variables TreeMap.
        variables = new TreeMap<String, PointND>();
    }

    /**
     * @param trial
     *            represents the trial that this state is within.
     * @param fieldMapper
     *            represents the FieldMapper object relevant to the Trial.
     * @param values
     *            is the string of data from the file.
     */
    public State(Trial trial, FieldMapper fieldMapper, String values)
    {
        // Saves the trial to the trial variable.
        this.trial = trial;
        // Initializes the TreeMap.
        variables = new TreeMap<String, PointND>();
        // Creates a temporary array for String.split to use.

        // Splits the data into a String array.
        String[] tempArray = values.split(",");

        // Creates an iterator variable and sets it equal to the iterator in
        // fieldMapper.
        Iterator<String> itr = fieldMapper.iterator();

        // Non-enhanced for-loop to track index.
        while (itr.hasNext())
        {
            String tempVar = itr.next();
            variables.put(tempVar, fieldMapper.extractPointND(tempArray, tempVar));
        }

    }

    /**
     * Method that returns the Trial that this State belongs to.
     * 
     * @return current Trial
     */
    public Trial getTrial()
    {
        return this.trial;
    }

    /**
     * Returns the PointND that is relevant to the fieldName.
     * 
     * @return PointND relevant to the fieldName.
     * @param fieldName
     *            a string that represents the field name.
     */
    public PointND getPoint(String fieldName)
    {
        return variables.get(fieldName);
    }

    /**
     * Get the GeneralValue within the specified field and subfield of the
     * current state. Returns an invalid GeneralValue if does not exist.
     * 
     * @param fieldName
     *            is the state's field name*
     * @param subFieldName
     *            is the state's subfield name*
     * @return the GeneralValue with the given field and subfield if valid
     *         otherwise, return an invalid general value.
     */
    public GeneralValue getValue(String fieldName, String subFieldName)
    {
        if (variables.containsKey(fieldName) && variables.get(fieldName).getValue(subFieldName) != null)
        {
            return this.variables.get(fieldName).getValue(subFieldName);
        }
        // If the State does not contain either the given field or subfield,
        // return null.
        else
        {
            // Return an invalid GeneralValue, per specification.
            return new GeneralValue();
        }

    }

    /**
     * Used by the MultipleItemAbstract class
     * 
     * @return the current state
     * @param fieldName
     *            not directly used
     * @param subFieldName
     *            not directly used
     */
    @Override
    public State getMaxState(String fieldName, String subFieldName)
    {
        // Return this value, if it is valid.
        if (this.getValue(fieldName, subFieldName).isValid())
        {
            return this;
        }

        // Return an invalid state if the current one is not marked as valid.
        return new State();
    }

    /**
     * Used by the MultipleItemAbstract class
     * 
     * @return the current state
     * @param fieldName
     *            not directly used
     * @param subFieldName
     *            not directly used
     */
    @Override
    public State getMinState(String fieldName, String subFieldName)
    {
        // Return this value, if it is valid.
        if (this.getValue(fieldName, subFieldName).isValid())
        {
            return this;
        }

        // Return an invalid state if the current one is not marked as valid.
        return new State();
    }

    /**
     * Used by the MultipleItemAbstract class
     * 
     * @return the current state's GeneralValue
     * @param fieldName
     *            of the desired GeneralValue
     * @param subFieldName
     *            of the desired GeneralValue
     */
    @Override
    public GeneralValue getAverageValue(String fieldName, String subFieldName)
    {
        // Return this value, if it is valid.
        if (this.getValue(fieldName, subFieldName).isValid())
        {
            return this.getValue(fieldName, subFieldName);
        }

        // Return an invalid state if the current one is not marked as valid.
        return new GeneralValue();
    }

    /**
     * @return Iterator containing all of the keys in the variables map.
     */
    public Iterator<String> iterator()
    {
        return variables.keySet().iterator();
    }

    /**
     * Returns a string representation of a given state. This relies on the
     * PointND object's method.
     * 
     * @return a string representation of State.
     */
    public String toString()
    {
        // Saves an iterator instance
        Iterator<String> tempIterator = this.iterator();
        // String that is concatenated.
        String strConcat = "";

        while (tempIterator.hasNext())
        {
            String tempField = tempIterator.next();
            strConcat += tempField + " (" + this.getPoint(tempField).toString() + ")\n";
        }
        return strConcat;
    }

}
