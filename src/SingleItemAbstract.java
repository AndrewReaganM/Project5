
/**
 * Abstract class to define the State class TODO: Re-document
 * 
 * @author LukeBarnes
 * @author AndrewMassey
 * @version 10/17/17 Project 3
 */
public abstract class SingleItemAbstract
{

    /**
     * @param fieldName
     *            is the field name for the entire State
     * @param subFieldName
     *            is the specific x, y, or z subfield
     * @return the State containing the max values
     */
    public abstract State getMaxState(String fieldName, String subFieldName);

    /**
     * @param fieldName
     *            is the field name for the entire State
     * @param subFieldName
     *            is the specific x, y, or z subfield
     * @return the State containing the min values
     */
    public abstract State getMinState(String fieldName, String subFieldName);

    /**
     * @param fieldName
     *            is the field name for the GeneralValue
     * @param subFieldName
     *            is the specific x, y, or z subfield
     * @return the the average of all states in the form of a GeneralValue
     */
    public abstract GeneralValue getAverageValue(String fieldName, String subFieldName);

}
