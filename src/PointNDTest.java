import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test 3D Position class
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class PointNDTest
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
     * Tests the getValues method in Point3D
     * 
     * @throws IOException
     *             for Trial
     */
    @Test
    public void testGetValues() throws IOException
    {
        Assert.assertEquals("Test the size method of PointND", 3,
                inf.getItem(0).getItem(0).getPoint("left_shoulder").size());
    }

    /**
     * Tests the null value things!
     */
    @Test
    public void testNullValue()
    {
        PointND point = new PointND();

        Assert.assertEquals("Tests null getValue", "invalid", point.getValue("foobar").toString());
    }

}