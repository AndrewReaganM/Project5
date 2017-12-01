import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * GeneralValue class test
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class GeneralValueTest
{
    /** sets up static Infant for testing */
    static Infant inf;

    /**
     * Sets up an Infant for testing.
     * 
     * @throws IOException
     *             when file loading goes wrong.
     */
    @BeforeClass
    public static void beforeClass() throws IOException
    {
        inf = new Infant("data", "k2");
    }

    /**
     * Tests the isValid method in GeneralValue
     * 
     * @throws IOException
     *             for Trial
     */
    @Test
    public void testIsValid() throws IOException
    {
        GeneralValue value = new GeneralValue();
        // Test the default generalValue constructor against the expected
        // isValid return
        Assert.assertEquals(false, value.isValid());

    }

    /**
     * Tests the Greater Than method.
     */
    @Test
    public void testGreaterThan()
    {
        Assert.assertEquals(299.98, inf.getMaxState("time", "").getValue("time", "").getDoubleValue(), 0.001);
    }

}
