import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class, which extends MultipleItemAbstract, defines what an Infant is and
 * contains all of the trials associated with the infant.
 * 
 * @author Luke Barnes
 * @author Andrew Massey
 * @version 11/13/17 Project 4
 * 
 */
public class Infant extends MultipleItemAbstract
{
    /** Stores all of the trials for the infant. */
    private ArrayList<Trial> trialList;

    /** Stores the ID of the infant. */
    private String infantID;

    /** Defines the maximum number of trials that an infant can have. */
    static final private int MAX_WEEK = 16;

    /**
     * Infant Constructor
     * 
     * This method constructs an infant, taking in a folder directory, and the
     * ID of a given infant.
     * 
     * 
     * @param directory
     *            is a String from the directory file containing infant data.
     * @param infantID
     *            is an infant-specific ID.
     * @throws IOException
     *             when Infant cannot be created.
     */
    public Infant(String directory, String infantID) throws IOException
    {
        this.infantID = infantID;

        // Creates the ArrayList of Trials.
        trialList = new ArrayList<Trial>();

        Trial tempTrial;

        // Loops through all specific weeks.
        for (int i = 1; i <= MAX_WEEK; i++)
        {
            try
            {
                // Attempts to create a trial.
                tempTrial = new Trial(this, directory, infantID, i);
                // If trial is created, trial is added to the trialList.
                // This line is never executed if the file is not found.
                trialList.add(tempTrial);
            }

            // Catches the FileNotFound exception thrown in Trial.
            catch (FileNotFoundException e)
            {
                System.out.println("File not found!");
            }

        }

    }

    /**
     * Infant Constructor create a list of trials specific to the given infant
     * 
     * @param infant
     *            used in the trials
     * @param indices
     *            is an array of trial indices
     */
    public Infant(Infant infant, int[] indices)
    {
        // Creates an arrayList that just deals with the trials passed in as
        // indices.
        trialList = new ArrayList<Trial>();

        // Goes through the entire array
        for (int i : indices)
        {
            // Adds the trials from the existing Infant passed in, to the new
            // arraylist.
            trialList.add(infant.getItem(i));
        }
    }

    /**
     * Method that returns a Trial, given an integer index.
     * 
     * @param index
     *            of desired Trial
     * @return Trial at specified index
     */
    public Trial getItem(int index)
    {
        return trialList.get(index);
    }

    /**
     * Method that returns the size of the trial list.
     * 
     * @return the size of the Trial list
     */
    public int getSize()
    {
        return this.trialList.size();
    }

    /**
     * Method that returns the infant ID.
     * 
     * @return String representation of a specific infantID
     */
    public String getInfantID()
    {
        return this.infantID;
    }

    /**
     * @return an iterator for the infant specific trial list
     */
    public Iterator<Trial> iterator()
    {
        return trialList.iterator();
    }

}
