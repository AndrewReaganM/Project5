import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Graphical user interface for interacting with Infant data
 * 
 * @author CS2334, modified by Andrew Massey Luke Barnes
 * @version 11/13/17
 *
 */
public class InfantFrame extends JFrame
{
    /** Panel for selecting the station, statistic, and Year */
    private SelectionPanel selectionPanel;
    /** Panel for displaying statistic */
    private DataPanel dataPanel;
    /** Width of column 1 text fields in the data display */
    private final static int COLUMN_FIELD_WIDTH = 10;
    /** Infant that is currently loaded. */
    private Infant infant;

    ///////////////////////////////////////////////////////////////////
    /**
     * @author CS2334, modified by Andrew Massey Luke Barnes
     * @version 11/10/17
     * 
     *          Menu bar that provides file loading and program exit
     *          capabilities.
     */
    private class FileMenuBar extends JMenuBar
    {
        /** Menu on the menu bar */
        private JMenu menu;
        /** Exit menu option */
        private JMenuItem menuExit;
        /** Open menu option. */
        private JMenuItem menuOpen;
        /** Reference to a file chooser pop-up */
        private JFileChooser fileChooser;

        /**
         * Constructor: fully assemble the menu bar and attach the necessary
         * action listeners.
         */
        public FileMenuBar()
        {
            // Create the menu and add it to the menu bar
            menu = new JMenu("File");
            add(menu);

            // Create and add menu items
            menuOpen = new JMenuItem();
            menuOpen.setText("Open Configuration File");
            menuOpen.setName("MenuOpen");
            menuExit = new JMenuItem();
            menuExit.setText("Exit");

            menu.add(menuOpen);
            menu.add(menuExit);

            // Action listener for exit
            menuExit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            // Filter for the file chooser: we only want files ending in '.dat'
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Configuration Files", "dat");

            // Create the file chooser: the default is to use the data directory
            fileChooser = new JFileChooser(new File("./data"));
            fileChooser.setFileFilter(filter);

            // Action listener for file open
            menuOpen.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Ask for a file
                    int returnVal = fileChooser.showOpenDialog(menuOpen);
                    // Was a file specified?
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                        // Yes
                        // Extract the file that was selected
                        File file = fileChooser.getSelectedFile();
                        try
                        {
                            InfantFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                            // Save the components of the selected file
                            String directory = file.getParent();
                            String fname = file.getName();

                            fname = fname.substring(0, 2);

                            // Do the loading work: the first two
                            // characters are the Infant ID

                            // This calls the loadData method in InfantFrame
                            InfantFrame.this.loadData(directory, fname);

                            // Return the cursor to standard form
                            InfantFrame.this.setCursor(null);

                            // If we did not load any data then open a
                            // message dialogbox to indicate an error
                        }
                        catch (IOException e2)
                        {
                            // Catch IO errors
                            JOptionPane.showMessageDialog(fileChooser, "File load error");
                            // Return the cursor to standard form
                            InfantFrame.this.setCursor(null);
                        }

                    }

                }

            });

        }
    }

    ///////////////////////////////////////////////////////////////////
    /**
     * @author CS2334, modified by Andrew Massey Luke Barnes
     * @version 11/10/17
     * 
     *          Selection panel: contains JLists for the list of trials, the
     *          list of fieldNames and the list of subfieldNames. Note that the
     *          displayed subfieldNames is dependent on which field has been
     *          selected
     */
    private class SelectionPanel extends JPanel
    {
        /** Selection of available trials/weeks. */
        private JList<String> trialList;
        /** Selection of the field. */
        private JList<String> fieldList;
        /** Selection of the subfield. */
        private JList<String> subfieldList;

        /** List model for the trial list. */
        private DefaultListModel<String> trialListModel;
        /** List model for the field list. */
        private DefaultListModel<String> fieldListModel;
        /** List model for the subfield list. */
        private DefaultListModel<String> subfieldListModel;

        /** Scroll pane: trial list */
        private JScrollPane trialScroller;
        /** Scroll pane: field list */
        private JScrollPane fieldScroller;
        /** Scroll pane: subfield list */
        private JScrollPane subfieldScroller;

        /** Trial selection label */
        private JLabel trialLabel;
        /** Field selection label */
        private JLabel fieldLabel;
        /** Subfield selection label */
        private JLabel subfieldLabel;

        /** FieldMapper for one of the trials. */
        private FieldMapper fieldMapper;

        /**
         * Constructor
         * 
         * Creates a 3x2 grid of components with labels down the left column and
         * JList down the 2nd column
         */
        private SelectionPanel()
        {
            this.setPreferredSize(new Dimension(400, 200));
            ////////////////////////////
            // Create the JList for trial selection
            // List model contains the data to be displayed.
            trialListModel = new DefaultListModel<String>();
            // JList for trials
            trialList = new JList<String>(trialListModel);
            // Multiple items can be selected at once
            trialList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Vertically organized list with an arbitrary number of rows
            trialList.setVisibleRowCount(-1);
            trialList.setLayoutOrientation(JList.VERTICAL);
            // Scroll pane goes around the JList
            trialScroller = new JScrollPane(trialList);
            trialScroller.setSize(new Dimension(200, 75));

            /////////////////////////////////////
            // JList for field selection
            // Model is of Strings
            fieldListModel = new DefaultListModel<String>();
            // JList for fields
            fieldList = new JList<String>(fieldListModel);
            fieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            // Vertically organized list with an arbitrary number of rows.
            fieldList.setVisibleRowCount(-1);
            fieldList.setLayoutOrientation(JList.HORIZONTAL_WRAP); 
            // Scroll pane goes around the JList
            fieldScroller = new JScrollPane(fieldList);
            fieldScroller.setSize(new Dimension(300, 100));

            ////////////////////////////////////////
            // JList for Subfields
            subfieldListModel = new DefaultListModel<String>();
            // JList for subfields
            subfieldList = new JList<String>(subfieldListModel);
            subfieldList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            // Vertically organized list with an arbitrary number of rows.
            subfieldList.setVisibleRowCount(-1);
            subfieldList.setLayoutOrientation(JList.VERTICAL);
            // Scroll pane goes around new JList
            subfieldScroller = new JScrollPane(subfieldList);
            subfieldScroller.setSize(new Dimension(300, 100));

            ////////////////
            // Selection Listeners

            // Listener for trial list
            trialList.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    InfantFrame.this.selectionPanel.fieldList.clearSelection();
                    InfantFrame.this.selectionPanel.subfieldList.clearSelection();
                    InfantFrame.this.update();

                }
            });

            // Listener for field list
            fieldList.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    InfantFrame.this.selectionPanel.updateSubfieldSelections();
                }
            });

            // Listener for subfield list
            subfieldList.addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    InfantFrame.this.update();
                }
            });

            // Labels
            trialLabel = new JLabel("Trials");
            fieldLabel = new JLabel("Fields");
            subfieldLabel = new JLabel("Subfields");

            ///////////////
            // Layout
            this.setLayout(new GridBagLayout());
            Dimension panelSize = new Dimension(500, 600);
            this.setPreferredSize(panelSize);
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(10, 10, 10, 10);
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;

            // add trialLabel to the SelectionPanel
            c.gridx = 0;
            c.gridy = 0;
            this.add(trialLabel, c);
            // add trialList to the SelectionPanel
            c.gridx = 2;
            c.gridy = 0;
            this.add(trialScroller, c);
            // add fieldLabel to the SelectionPanel
            c.gridx = 0;
            c.gridy = 3;
            this.add(fieldLabel, c);
            // add fieldList to the SelectionPanel
            c.gridx = 2;
            c.gridy = 3;
            this.add(fieldScroller, c);
            // add subfieldLabel to the SelectionPanel
            c.gridx = 0;
            c.gridy = 6;
            this.add(subfieldLabel, c);
            // add subfieldList to the SelectionPanel
            c.gridx = 2;
            c.gridy = 6;
            this.add(subfieldScroller, c);

            // Background color of the panel
            this.setBackground(new Color(200, 250, 210));

            // ///////////////////////
            // Set the names of the key objects: don't change these
            this.trialList.setName("TrialList");
            this.fieldList.setName("FieldList");
            this.subfieldList.setName("SubfieldList");
            // ///////////////////////
        }

        /**
         * Update the entire selection panel based on the currently-loaded
         * infant.
         * 
         * This method is generally called when a new infant is loaded
         * 
         */
        private void updateSelections()
        {
            ////////////////
            // Trial list

            // Clear all elements
            this.trialListModel.clear();

            // Make an iterator for the trials
            Iterator<Trial> infantIterator = infant.iterator();

            while (infantIterator.hasNext())
            {
                trialListModel.addElement(infantIterator.next().toString());
            }

            /////////////////////
            // Field list
            this.fieldListModel.clear();

            // Check that infant size is greater than 0

            // Pull in the fieldMapper
            fieldMapper = infant.getItem(0).getFieldMapper();

            // Make an iterator for the fields
            Iterator<String> fieldIterator = fieldMapper.iterator();
            while (fieldIterator.hasNext())
            {
                fieldListModel.addElement(fieldIterator.next());
            }

            // Update the subfields
            this.updateSubfieldSelections();
            // System.out.println("SelectionPanel selections update");
        }

        /**
         * Update the subfield selection list based on the subfields available
         * for the currently selected field
         * 
         * 
         * This method is called any time there is a change to the selected
         * field.
         */
        private void updateSubfieldSelections()
        {
            // clear all elements
            this.subfieldList.clearSelection();
            this.subfieldListModel.clear();

            if (this.fieldList.getSelectedValue() != null
                    && fieldMapper.getField(this.fieldList.getSelectedValue()) != null)
            {
                String item = this.fieldList.getSelectedValue();
                Iterator<String> itr = InfantFrame.this.selectionPanel.fieldMapper.getField(item).iterator();

                // write while loop for iterator
                while (itr.hasNext())
                {
                    this.subfieldListModel.addElement(itr.next());
                }
            }

            // Tell the rest of the frame that it needs to update
            InfantFrame.this.update();
            // System.out.println("SelectionsPanel subfield update");
        }

    }

    ///////////////////////////////////////////////////////////////////
    /**
     * DataPanel: display selection information and statistics
     * 
     * @author CS2334, modified by Luke Barnes Andrew Massey
     * @version 11/10/17
     */

    private class DataPanel extends JPanel
    {
        // Labels
        private JLabel infantIDLabel = new JLabel("Infant ID:");
        private JLabel fieldNameLabel = new JLabel("Field:");
        private JLabel subfieldNameLabel = new JLabel("Subfield:");
        private JLabel maxLabel = new JLabel("Max:");
        private JLabel maxWeekLabel = new JLabel("on");
        private JLabel maxTimeLabel = new JLabel("at");
        private JLabel averageLabel = new JLabel("Average:");
        private JLabel minLabel = new JLabel("Min");
        private JLabel minWeekLabel = new JLabel("on");
        private JLabel minTimeLabel = new JLabel("at");

        // Fields
        private JTextField infantIDField = new JTextField(10);
        private JTextField fieldNameField = new JTextField(10);
        private JTextField subfieldNameField = new JTextField(10);
        private JTextField maxValueField = new JTextField(10);
        private JTextField maxWeekField = new JTextField(10);
        private JTextField maxTimeField = new JTextField(10);
        private JTextField minValueField = new JTextField(10);
        private JTextField minWeekField = new JTextField(10);
        private JTextField minTimeField = new JTextField(10);
        private JTextField averageValueField = new JTextField(10);

        /**
         * Constructor
         * 
         * Create and lay out the data display panel
         */
        private DataPanel()
        {
            // Background color of the panel
            this.setBackground(new Color(200, 200, 230));

            // Set the text fields to be non-editable
            infantIDField.setEditable(false);
            fieldNameField.setEditable(false);
            subfieldNameField.setEditable(false);
            maxValueField.setEditable(false);
            maxWeekField.setEditable(false);
            maxTimeField.setEditable(false);
            minValueField.setEditable(false);
            minWeekField.setEditable(false);
            minTimeField.setEditable(false);
            averageValueField.setEditable(false);

            /////////////
            // Initialize the DataPanel
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            // add infantIDLabel to the panel
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10, 10, 10, 10);
            this.add(infantIDLabel, c);
            // add infantIDField to the panel
            c.gridx = 1;
            c.gridy = 0;
            this.add(infantIDField, c);
            // add fieldNameLabel to the panel
            c.gridx = 0;
            c.gridy = 1;
            this.add(fieldNameLabel, c);
            // add fieldNameField to the panel
            c.gridx = 1;
            c.gridy = 1;
            this.add(fieldNameField, c);
            // add subfieldNameLabel to the panel
            c.gridx = 0;
            c.gridy = 2;
            this.add(subfieldNameLabel, c);
            // add subfieldNameField to the panel
            c.gridx = 1;
            c.gridy = 2;
            this.add(subfieldNameField, c);
            // add maxLabel to the panel
            c.gridx = 0;
            c.gridy = 3;
            this.add(maxLabel, c);
            // add maxValueField to the panel
            c.gridx = 1;
            c.gridy = 3;
            this.add(maxValueField, c);
            // add maxWeekLabel to the panel
            c.gridx = 2;
            c.gridy = 3;
            this.add(maxWeekLabel, c);
            // add maxWeekField to the panel
            c.gridx = 3;
            c.gridy = 3;
            this.add(maxWeekField, c);
            // add maxTimeLabel to the panel
            c.gridx = 4;
            c.gridy = 3;
            this.add(maxTimeLabel, c);
            // add maxTimeField to the panel
            c.gridx = 5;
            c.gridy = 3;
            this.add(maxTimeField, c);
            // add averageLabel to the panel
            c.gridx = 0;
            c.gridy = 4;
            this.add(averageLabel, c);
            // add averageValueField to the panel
            c.gridx = 1;
            c.gridy = 4;
            this.add(averageValueField, c);
            // add minLabel to the panel
            c.gridx = 0;
            c.gridy = 5;
            this.add(minLabel, c);
            // add minValueField to the panel
            c.gridx = 1;
            c.gridy = 5;
            this.add(minValueField, c);
            // add minWeekLabel to the panel
            c.gridx = 2;
            c.gridy = 5;
            this.add(minWeekLabel, c);
            // add minWeekField to the panel
            c.gridx = 3;
            c.gridy = 5;
            this.add(minWeekField, c);
            // add minTimeLabel to the panel
            c.gridx = 4;
            c.gridy = 5;
            this.add(minTimeLabel, c);
            // add minTimeField to the panel
            c.gridx = 5;
            c.gridy = 5;
            this.add(minTimeField, c);

            /////////////////////////////////////
            // Component names: DO NOT CHANGE THIS CODE

            infantIDField.setName("infantIDField");
            fieldNameField.setName("fieldNameField");
            subfieldNameField.setName("subfieldNameField");
            maxValueField.setName("maxValueField");
            maxWeekField.setName("maxWeekField");
            maxTimeField.setName("maxTimeField");
            minValueField.setName("minValueField");
            minWeekField.setName("minWeekField");
            minTimeField.setName("minTimeField");
            averageValueField.setName("averageValueField");
            /////////////////////////////////////
        }

        /**
         * Update the data display panel with new Strings
         * 
         * @param infantID
         *            Infant ID
         * @param fieldName
         *            Field name
         * @param subfieldName
         *            Subfield name
         * @param maxState
         *            Max value String
         * @param maxStateWeek
         *            Max value Week (Trial.toString())
         * @param maxStateTime
         *            Max value time
         * @param minState
         *            Min value String
         * @param minStateWeek
         *            Min value Week
         * @param minStateTime
         *            Min value time
         * @param average
         *            Average value
         */
        private void update(String infantID, String fieldName, String subfieldName, String maxState,
                String maxStateWeek, String maxStateTime, String minState, String minStateWeek, String minStateTime,
                String average)
        {
            this.infantIDField.setText(infantID);
            this.fieldNameField.setText(fieldName);
            this.subfieldNameField.setText(subfieldName);
            this.maxValueField.setText(maxState);
            this.maxWeekField.setText(maxStateWeek);
            this.maxTimeField.setText(maxStateTime);
            this.minValueField.setText(minState);
            this.minWeekField.setText(minStateWeek);
            this.minTimeField.setText(minStateTime);
            this.averageValueField.setText(average);
            // System.out.println("DataPanel update");
        }

    }

    ///////////////////////////////////////////////////////////////////
    // InfantFrame definition

    /**
     * InfantFrame constructor
     * 
     * Two frames are side-by-side: SelectionPanel on the left and DataPanel on
     * the right.
     */
    public InfantFrame()
    {
        super("Infant Explorer");

        /** Menu bar */
        FileMenuBar fileMenuBar;

        // Configure the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 800));

        // Menu bar
        fileMenuBar = new FileMenuBar();
        this.setJMenuBar(fileMenuBar);

        // GridBagLayout for the contents
        this.setLayout(new GridBagLayout());
        GridBagConstraints layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(10, 10, 10, 10);

        // Selection panel
        this.selectionPanel = new SelectionPanel();
        this.add(this.selectionPanel, layoutConst);

        // Data panel
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        this.dataPanel = new DataPanel();
        this.add(this.dataPanel, layoutConst);

        // Make the frame visible
        this.setVisible(true);

        // Compress the size of all objects
        this.pack();

    }

    /**
     * Load a new Infant.
     * 
     * This method is declared as "synchronized" to ensure that only one thread
     * is allowed to call it and other synchronized methods at once.
     * 
     * @param directory
     *            Directory in which the infant data files (CSV) are located
     * @param infantID
     *            ID of the infant to be loaded
     * @throws IOException
     *             Thrown if an error occurs while reading the file
     */
    public synchronized void loadData(String directory, String infantID) throws IOException
    {
        // Loads a new infant
        infant = new Infant(directory, infantID);
        // updates the selection panel to reflect the infant.
        selectionPanel.fieldMapper = infant.getItem(0).getFieldMapper();
        selectionPanel.updateSelections();
    }

    /**
     * Translate the selections made in the SelectionPanel into a set of Strings
     * to be displayed in the DataPanel.
     * 
     * This method is declared as "synchronized" to ensure that only one thread
     * is allowed to call it and other synchronized methods at once.
     * 
     * One of the challenges in implementing this method is that it can be
     * called at any time. In particular, the structures that it is referencing
     * may be in the process of being updated. So, we cannot assume that things
     * like "selected values" are not actually set to something interesting or
     * useful.
     * 
     */
    public synchronized void update()
    {
        // Which weeks have been selected?
        int[] indices = selectionPanel.trialList.getSelectedIndices();

        // Default values for the displayed Strings
        String infantID = "n/a";
        String maxStateString = "n/a";
        String maxStateWeekString = "n/a";
        String maxStateTimeString = "n/a";

        String minStateString = "n/a";
        String minStateWeekString = "n/a";
        String minStateTimeString = "n/a";

        String averageString = "n/a";

        String fieldName = "n/a";
        String subfieldName = "n/a";

        // Does the infant object exist and does it have at least one week?
        if (infant != null && infant.getSize() > 0)
        {
            // Create an Infant object with only the selected weeks
            Infant subInfant = new Infant(this.infant, indices);

            // Extract the infant ID
            infantID = infant.getInfantID();

            // Which field has been selected?
            fieldName = selectionPanel.fieldList.getSelectedValue();
            //System.out.println(fieldName);

            // Does this field exist and is it not empty
            if (fieldName != null && !fieldName.equals(""))
            {
                if (selectionPanel.subfieldList.getSelectedValue() != null)
                {
                    // Which subfield has been selected?
                    subfieldName = selectionPanel.subfieldList.getSelectedValue();
                    //System.out.println(subfieldName);
                    
                    // The dealings of the max value.
                    State maxState = subInfant.getMaxState(fieldName, subfieldName);
                    maxStateString = maxState.getValue(fieldName, subfieldName).toString();
                    maxStateWeekString = maxState.getTrial().toString();
                    maxStateTimeString = maxState.getValue("time", "scalar").toString();
                    
                    // The dealings of the min value.
                    State minState = subInfant.getMinState(fieldName, subfieldName);
                    minStateString = minState.getValue(fieldName, subfieldName).toString();
                    minStateWeekString = minState.getTrial().toString();
                    minStateTimeString = minState.getValue("time", "scalar").toString();
                    
                    // The dealings in regards to the average value.
                    averageString = subInfant.getAverageValue(fieldName, subfieldName).toString();
                }
            }
        }
        else
        {
            // Indicate no meaningful fieldName
            fieldName = "n/a";
        }

        // Tell the data panel to update
        this.dataPanel.update(infantID, fieldName, subfieldName, maxStateString, maxStateWeekString, maxStateTimeString,
                minStateString, minStateWeekString, minStateTimeString, averageString);
        this.repaint();
        // System.out.println("InfantFrame update");
    }

}
