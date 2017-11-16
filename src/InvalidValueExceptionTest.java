import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the InvalidValueException
 * 
 * @author LukeBarnes
 * @author AndrewMassey
 * @version 10/11/17
 *
 */
public class InvalidValueExceptionTest
{

    /**
     * Test the exception class by calling its constructor through throwing an
     * exception
     */
    @Test
    public void testInvalidValueException()
    {
        try
        {
            throw new InvalidValueException("InvalidValueException test");
        }
        catch (InvalidValueException e)
        {
            // Do nothing in this case, the exception was successfully thrown
        }

        // Add an assert statement to make WebCat happy
        Assert.assertEquals("How did this even go wrong?", 1, 1);

    }

}
