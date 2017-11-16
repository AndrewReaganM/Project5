import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * State Test class
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class StateTest
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
     * Tests getTime for the State class.
     * 
     * @throws IOException
     *             for Trial.
     */
    @Test
    public void testGetTrial() throws IOException
    {
        // Test getTime against desired state time
        Assert.assertEquals("Tests the getTrial method in State: ", "data/subject_k2_w01.csv",
                inf.getItem(0).getItem(0).getMaxState("time", "scalar").getTrial().getFileName());
        Assert.assertEquals("Tests Get Average Value", 0.0,
                inf.getItem(0).getItem(0).getAverageValue("time", "scalar").getDoubleValue(), 0.001);
        Assert.assertEquals("Tests Get Average Value", 0.0,
                inf.getItem(0).getItem(0).getMinState("time", "scalar").getValue("time", "scalar").getDoubleValue(),
                0.001);
        Assert.assertEquals("Tests toString method of state: ", "left_ankle x = -0.081; y = 0.278; z = 0.009; \n"
                + "left_elbow x = 0.292; y = 0.117; z = 0.174; \n" + "left_foot x = 0.011; y = 0.320; z = -0.009; \n"
                + "left_knee x = -0.096; y = 0.142; z = -0.037; \n"
                + "left_shoulder x = 0.193; y = 0.038; z = 0.126; \n" + "left_wrist x = 0.248; y = 0.060; z = 0.248; \n"
                + "right_ankle x = -0.260; y = -0.098; z = -0.024; \n"
                + "right_elbow x = 0.165; y = -0.262; z = 0.066; \n"
                + "right_foot x = -0.361; y = -0.101; z = -0.041; \n"
                + "right_knee x = -0.116; y = -0.101; z = -0.038; \n"
                + "right_shoulder x = 0.182; y = -0.136; z = 0.021; \n"
                + "right_wrist x = 0.214; y = -0.292; z = -0.019; \n" + "robot_vel l = 0.005; r = -0.012; \n"
                + "sippc_action scalar = 0.000; \n" + "time scalar = 0.000; \n"
                + "upper_back x = 0.187; y = -0.049; z = 0.073; \n", inf.getItem(0).getItem(0).toString());
        State sta = new State();
        Assert.assertEquals("", sta.toString());
    }

}