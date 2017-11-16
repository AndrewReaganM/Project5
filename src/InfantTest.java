import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Luke Barnes
 * @author Andrew Massey
 * @version 11/13/2017 Project 4
 *
 */
public class InfantTest
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
     * Tests the infant Getters
     * 
     * @throws IOException
     *             when Infant cannot be created.
     */
    @Test
    public void infantGetTest() throws IOException
    {
        Assert.assertEquals("Returns the correct array size", 4, inf.getSize());
        Assert.assertEquals("Returns the correct infantID", "k2", inf.getInfantID());
    }

    /**
     * Tests the getDoubleValue method.
     * 
     * @throws IOException
     *             when Infant cannot be created.
     */
    @Test
    public void infantGetItemTest() throws IOException
    {
        Assert.assertEquals("Returns the correct data", 0.248297,
                inf.getItem(0).getItem(0).getPoint("left_wrist").getValue("x").getDoubleValue(), 0.001);
    }

    /**
     * Tests the get minimum state method.
     */
    @Test
    public void testGetMinState()
    {
        Assert.assertEquals("Returns the minimum of a field", -0.045396,
                inf.getMinState("left_wrist", "x").getValue("left_wrist", "x").getDoubleValue(), 0.001);
    }

    /**
     * Tests the average value method.
     */
    @Test
    public void testGetAverageValue()
    {
        Assert.assertEquals("Returns the average value of a field", 149.99,
                inf.getAverageValue("time", "scalar").getDoubleValue(), 0.001);
    }
}
