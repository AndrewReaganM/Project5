
/**
 * This class stores general values that are typically found within Point3D
 * objects.
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class GeneralValue
{
    /** Is the value valid? */
    private boolean valid;

    /** Double value stored. */
    private double doubleValue;

    /**
     * Generic constructor, only used for weird cases where we need an invalid
     * data point.
     */
    public GeneralValue()
    {
        this.valid = false;
        this.doubleValue = 0.0;

    }

    /**
     * The constructor for a single dimensional value.
     * 
     * @param strg
     *            takes in a string.
     */
    public GeneralValue(String strg)
    {

        // If the string is "NaN", set false.
        if (strg.equalsIgnoreCase("NaN"))
        {
            this.valid = false;
        }

        // Otherwise, set true and set double.
        else
        {
            this.doubleValue = Double.parseDouble(strg);
            this.valid = true;
        }

    }

    /**
     * Constructs a GeneralValue instance using a double.
     * 
     * @param doubleValue
     *            in the form of a double or NaN
     */
    public GeneralValue(double doubleValue)
    {

        if (Double.isNaN(doubleValue))
        {
            this.valid = false;
        }

        else
        {
            this.doubleValue = doubleValue;
            this.valid = true;
        }

    }

    /**
     * Returns whether the object has valid data or not.
     * 
     * @return the validity of the value
     * 
     */
    public boolean isValid()
    {
        return this.valid;
    }

    /**
     * Returns the value of a given dimension.
     * 
     * @return doubleValue the value of a given dimension.
     */
    public double getDoubleValue()
    {
        if (this.valid)
        {
            return this.doubleValue;
        }
        else
        {
            throw new InvalidValueException("Not a valid value.");
        }

    }

    /**
     * If the data point is valid, returns the this.doubleValue as a string, to
     * three decimal places. Otherwise, returns the string "invalid".
     * 
     * @return a string containing GeneralValue data.
     */
    public String toString()
    {
        if (this.valid)
        {
            return String.format("%.3f", this.doubleValue);
        }
        else
        {
            return "invalid";
        }

    }

    /**
     * Compares the passed GeneralValue with the current one
     * 
     * @param value
     *            is a GeneralValue to be compared with the current one
     * @return true if the current value is less than the passed one return
     *         false otherwise
     */
    public boolean isLessThan(GeneralValue value)
    {
        // If they are both valid, run a normal comparator.
        if (this.isValid() && value.isValid())
        {
            return this.getDoubleValue() < value.getDoubleValue();
        }
        // If this is valid, return true, if this is not valid, return false.
        else
        {
            return this.isValid();
        }
    }

    /**
     * Compares the passed GeneralValue with the current one
     * 
     * @param value
     *            is a GeneralValue to be compared with the current one
     * @return true if the current value is greater than the passed one return
     *         false otherwise
     */
    public boolean isGreaterThan(GeneralValue value)
    {
        // If they are both valid, run a normal comparator.
        if (this.isValid() && value.isValid())
        {
            return this.getDoubleValue() > value.getDoubleValue();
        }
        // If this is valid, return true, if this is not valid, return false.
        else
        {
            return this.isValid();
        }

    }

}
