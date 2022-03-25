package gui;

import model.FlowTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

// Main frame for menstrual cycle tracker application
public class FlowAppFrame extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private JPanel middlePanel;
    private ImageIcon saveIcon;

    private FlowTracker flowTracker;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/flowTracker.json";

    // EFFECTS: runs the "FlowApp" application
    public FlowAppFrame() throws FileNotFoundException, IOException {
        super("welcome to flow app!");
        createFrame();
        createPanels();
        createButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        flowTracker = new FlowTracker("My flow tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadPreviousDays();
        saveLoggedDays();
    }

    // MODIFIES: this
    // EFFECTS: opens a JOptionPane to give user option to load or not load previously logged entries
    // SOURCE: https://www.tutorialspoint.com/what-are-the-different-types-of-joptionpane-dialogs-in-java#:~:text=The%20
    // JOptionPane%20is%20a%20subclass,the%20complexity%20of%20the%20code.
    public void loadPreviousDays() {
        ImageIcon loadIcon = new ImageIcon("filesIcon3.png");
        int result = JOptionPane.showConfirmDialog(null,
                "would you like to load your previously logged entries?", "welcome to flow app! first...",
                YES_NO_OPTION, INFORMATION_MESSAGE, loadIcon);
        switch (result) {
            case JOptionPane.YES_OPTION:
                loadFlowTracker();
                break;
            case JOptionPane.NO_OPTION:
            default:
        }
    }

    // MODIFIES: this
    // EFFECTS: opens a JOptionPane to give user option to save logged entries before exiting app
    // SOURCE: https://stackoverflow.com/questions/12210972/setdefaultcloseoperation-to-show-a-jframe-instead
    public void saveLoggedDays() {
        saveIcon = new ImageIcon("savefilesicon2.png");
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(null,
                        "before you leave, would you like to save your logged entries?",
                        "thank you for visiting!",
                        JOptionPane.YES_NO_CANCEL_OPTION, INFORMATION_MESSAGE, saveIcon);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        saveFlowTracker();
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                    default:
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: organizes the key panels for the main frame layout
    // SOURCE (for buffered image): https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    //https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html
    // SOURCE (for resizing icon): https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    public void createPanels() throws IOException {
        JPanel topPanel = new JPanel(new FlowLayout());
        add(topPanel);
        middlePanel = new JPanel(new FlowLayout());
        add(middlePanel);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        add(bottomPanel);
        BufferedImage logo200 = ImageIO.read(new File("200x200.png"));
        JLabel logoLabel200 = new JLabel(new ImageIcon(logo200));
        topPanel.add(logoLabel200);
    }

    // MODIFIES: this
    // EFFECTS: configures the main frame
    public void createFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: calls the methods used to create the JButtons
    public void createButtons() {
        entryButton();
        viewDeleteButton();
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the entryButton to the frame
    public void entryButton() {
        JButton newEntry = new JButton("log a new entry.");
        newEntry.setActionCommand("New Entry");
        newEntry.addActionListener(this);
        newEntry.setPreferredSize(new Dimension(175, 100));
        middlePanel.add(newEntry);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the viewDeleteButton to the frame
    public void viewDeleteButton() {
        JButton viewDelete = new JButton("<html><center>view/delete<br />previously logged days.</center></html>");
        viewDelete.setActionCommand("view");
        viewDelete.addActionListener(this);
        viewDelete.setPreferredSize(new Dimension(175, 100));
        middlePanel.add(viewDelete);
    }

    // EFFECTS: carries out the actions that correspond to pressing the buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Entry")) {
            new EntryFrame(flowTracker);
        } else if (e.getActionCommand().equals("view")) {
            new ViewFrame(flowTracker);
        }
    }

    // EFFECTS: saves the flowTracker to file
    private void saveFlowTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(flowTracker);
            jsonWriter.close();
            System.out.println("Saved " + flowTracker.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads flowTracker from file
    private void loadFlowTracker() {
        try {
            flowTracker = jsonReader.read();
            System.out.println("Loaded " + flowTracker.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    public static void main(String[] args) {
        try {
            new FlowAppFrame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException e) {
            System.out.println("picture not found");
        }
    }
}