/**
 * Abstract class to define the Trial class Extends the SingleItemAbstract Class
 * 
 * @author Luke Barnes
 * @author Andrew Massey
 * @version 11/11/17 Project 4
 */
public abstract class MultipleItemAbstract extends SingleItemAbstract
{

    /**
     * @return an int that represents a size.
     */
    public abstract int getSize();

    /**
     * @param index
     *            the index of a given item.
     * @return a SingleItemAbstract that represents an item.
     */
    public abstract SingleItemAbstract getItem(int index);

    /**
     * Return the maximum value of a specific field/subfield
     * 
     * @param fieldName
     *            The String name of the field of interest
     * @param subFieldName
     *            The String name of the subfield
     * 
     * @return The state that contains the maximum value
     */
    public State getMaxState(String fieldName, String subFieldName)
    {
        //If the code is looking for an empty string, change it to scalar (makes code work with old things).
        if (subFieldName.equalsIgnoreCase(""))
        {
            subFieldName = "scalar";
        }
        // Best value found so far
        GeneralValue maxValue = new GeneralValue();

        State maxState = new State();

        // Loop over all States
        for (int i = 0; i < this.getSize(); ++i)
        {
            // Extract the value of the specific dimension and time
            State s = this.getItem(i).getMaxState(fieldName, subFieldName);
            GeneralValue v = s.getValue(fieldName, subFieldName);

            // Is this one larger?
            if (v.isGreaterThan(maxValue))
            {
                // Yes - replace it
                maxValue = v;
                maxState = s;
            }
        }

        //
        return maxState;
    }

    /**
     * Calculate the minimum position value, returning a State.
     * 
     * @param fieldName
     *            the name of the field.
     * @param subFieldName
     *            the name of the subfield.
     * @return minimum position of type GeneralValue
     * 
     */
    public State getMinState(String fieldName, String subFieldName)
    {
      //If the code is looking for an empty string, change it to scalar (makes code work with old things).
        if (subFieldName.equalsIgnoreCase(""))
        {
            subFieldName = "scalar";
        }
     // Best value found so far
        GeneralValue minValue = new GeneralValue();

        State minState = new State();

        // Loop over all States
        for (int i = 0; i < this.getSize(); ++i)
        {
            // Extract the value of the specific dimension and time
            State s = this.getItem(i).getMinState(fieldName, subFieldName);
            GeneralValue v = s.getValue(fieldName, subFieldName);

            // Is this one larger?
            if (v.isLessThan(minValue))
            {
                // Yes - replace it
                minValue = v;
                minState = s;
            }
        }

        //
        return minState;
    }

    /**
     * Method that returns state holding the average value.
     * 
     * @return State of the average value dictated by fieldName and subFieldName
     * @param fieldName
     *            string representing the name of the field.
     * @param subFieldName
     *            string representing the name of the subfield.
     */
    public GeneralValue getAverageValue(String fieldName, String subFieldName)
    {
      //If the code is looking for an empty string, change it to scalar (makes code work with old things).
        if (subFieldName.equalsIgnoreCase(""))
        {
            subFieldName = "scalar";
        }
        
        //Tracks the total value
        Double val = 0.0;
        //Tracks the number of valid values that are being dealt with.
        int increment = 0;
        // Iterate through all of the states.
        for (int i = 0; i < this.getSize(); i++)
        {

            if (this.getItem(i).getAverageValue(fieldName, subFieldName).isValid())
            {
                val += this.getItem(i).getAverageValue(fieldName, subFieldName).getDoubleValue();
                increment++;
            }
        }
        if (increment == 0)
        {
            return new GeneralValue();
        }
        
        return new GeneralValue(val / (double) increment);
    }

}
