import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * State Test class
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 10/10/17 Project2
 *
 */
public class TrialTest
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
     * Tests the default constructor for Trial.
     */
    @Test
    public void testGetters()
    {
        Assert.assertEquals("k2", inf.getItem(0).getInfant().getInfantID());
        Assert.assertEquals(1, inf.getItem(0).getWeek());
        Assert.assertEquals("Tests File Name getter:", "data/subject_k2_w01.csv", inf.getItem(0).getFileName());
        Assert.assertEquals("Tests trial size getter:", 15000, inf.getItem(0).getSize());
        Assert.assertEquals("Tests the Trial getter that returns a state", 0.248,
                inf.getItem(0).getItem(0).getValue("left_wrist", "x").getDoubleValue(), 0.001);
        Assert.assertEquals("Week 01", inf.getItem(0).getItem(0).getTrial().toString());
        Assert.assertEquals("scalar(0); ", inf.getItem(0).getFieldMapper().getField("time").toString());
    }

}