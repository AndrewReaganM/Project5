
/**
 * InvalidValueException creates a new RuntimeException This exception is thrown
 * when NaN is read from a data file
 * 
 * @author LukeBarnes
 * @author AndrewMassey
 * @version 10/9/17 Project 3
 */
public class InvalidValueException extends RuntimeException
{

    /**
     * Print out an invalid value message when exception is thrown
     * 
     * @param message
     *            the specific exception message
     */
    public InvalidValueException(String message)
    {
        System.out.println("Wow! An exception! The code would like to tell you this: " + message);
    }

}
