import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * Tests the FieldMapper class
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11-13-17
 *
 */
public class FieldMapperTest
{

    /**
     * Tests the extractPointND method.
     */
    @Test
    public void testExtractPointND()
    {
        String[] strArr = { "Time", "is_x", "is_y", "is_z" };

        FieldMapper fm = new FieldMapper(strArr);

        String[] strData = { "0.05", "1.2", "9.9" };

        Assert.assertEquals("Tests null fieldNames", null, fm.extractPointND(strData, "cats"));
    }

}
