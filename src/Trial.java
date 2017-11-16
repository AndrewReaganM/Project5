import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representation of a single trial
 * 
 * @author Andrew Massey
 * @author Luke Barnes
 * @version 11/13/17 Project 4
 *
 */
public class Trial extends MultipleItemAbstract
{
    /** Sequence of states. */
    private ArrayList<State> stateList;

    /** Week index. */
    private int week;

    /** File name that was loaded. */
    private String fileName;

    /** Infant Loaded */
    private Infant infant;

    /** FieldMapper that defines this trial */
    private FieldMapper fieldMapper;

    /**
     * The constructor for Trial.
     * 
     * This method reads in a given file, and stores the information in the
     * correct data structures. If the file does not exist, FileNotFound
     * exception is thrown and caught in Infant.
     * 
     * This method also creates the FieldMapper object that is responsible for
     * organizing the data.
     * 
     * @param infant
     *            infant object that this Trial belongs to.
     * @param infantDirectory
     *            the directory where the Infant data resides.
     * @param infantID
     *            unique ID for the infant, used to find relevant files.
     * @param week
     *            the given week for this Trial.
     * @throws IOException
     *             when the data cannot be properly loaded. FileNotFound
     *             exception is acceptable, and explicitly caught in Infant.
     */
    public Trial(Infant infant, String infantDirectory, String infantID, int week) throws IOException
    {
        this.week = week;
        this.infant = infant;

        // Compose the filename - ensuring the associated number has the correct
        // amount of digits
        // Formats the date correctly.
        String tempDate = String.format("%02d", this.week);

        // Creates the fileName.
        this.fileName = String.format("%s/subject_%s_w%s.csv", infantDirectory, infantID, tempDate);

        System.out.println("Attempting import: " + fileName);
        // Constructs the ArrayList of states.
        this.stateList = new ArrayList<State>();

        // Open the file
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        System.out.println("Importing...");
        String strg;

        // Pull header data.
        strg = br.readLine();

        // Creates the FieldMapper for this specific Trial.
        fieldMapper = new FieldMapper(strg.split(","));
        fieldMapper.size();

        // read first line of actual data
        strg = br.readLine();

        // Continues to read in until the value is null - no more data exists in
        // the file.
        while (strg != null)
        {
            stateList.add(new State(this, fieldMapper, strg));
            strg = br.readLine();
        }

        br.close();
        System.out.println("******Successfuly Imported!******");

    }

    /**
     * Returns the infant ID of the infant to which this Trial belongs.
     * 
     * @return the infantID of the trial.
     */
    public Infant getInfant()
    {
        return infant;
    }

    /**
     * Returns the week of the given Trial.
     * 
     * @return the week of the Trial.
     */
    public int getWeek()
    {
        return week;
    }

    /**
     * Returns the name of the file in the filesystem.
     * 
     * @return the fileName of the file associated with this week and infant.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Returns the number of elements in the ArrayList (starting at 1).
     * 
     * @return the size of the ArrayList.
     */
    public int getSize()
    {
        return this.stateList.size();
    }

    /**
     * Returns a state object, given a state ID (0 indexed).
     * 
     * @return a State
     * @param index
     *            takes in an index for a state in stateList.
     */
    public State getItem(int index)
    {
        return this.stateList.get(index);
    }

    /**
     * Returns the fieldmapper appropriate to this trial.
     * 
     * @return FieldMapper object defining the fieldmapper at hand.
     */
    public FieldMapper getFieldMapper()
    {
        return this.fieldMapper;
    }

    /**
     * Returns a string with the week number.
     * 
     * @return String containing the week that the data pertains to.
     */
    public String toString()
    {
        String tempDate = String.format("%02d", this.week);
        return "Week " + tempDate;
    }

}
