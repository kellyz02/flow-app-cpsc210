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

public class FlowAppFrame extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private JButton newEntry;
    private JButton viewDelete;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private ImageIcon loadIcon;
    private ImageIcon saveIcon;

    private FlowTracker flowTracker;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/flowTracker.json";


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

    public void loadPreviousDays() {
        loadIcon = new ImageIcon("filesicon3.png");
//        Image loadImage = loadIcon.getImage();
//        Image newLoadImage = loadImage.getScaledInstance(150,100, Image.SCALE_SMOOTH);
//        loadIcon = new ImageIcon(newLoadImage);
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


    public void createPanels() throws IOException {
        topPanel = new JPanel(new FlowLayout());
        add(topPanel);
        middlePanel = new JPanel(new FlowLayout());
        add(middlePanel);
        bottomPanel = new JPanel(new FlowLayout());
        add(bottomPanel);
        BufferedImage logo = ImageIO.read(new File("300x300.png"));
        BufferedImage logo200 = ImageIO.read(new File("200x200.png"));
//        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        JLabel logoLabel200 = new JLabel(new ImageIcon(logo200));
//        JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(200, 200, Image.SCALE_FAST)));
//        topPanel.add(logoLabel);
        topPanel.add(logoLabel200);
    }

    public void createFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    }

    public void createButtons() {
        entryButton();
        viewDeleteButton();
    }

    public void entryButton() {
        newEntry = new JButton("log a new entry.");
        newEntry.setActionCommand("New Entry");
        newEntry.addActionListener(this);
        newEntry.setPreferredSize(new Dimension(175, 100));
        middlePanel.add(newEntry);
    }

    public void viewDeleteButton() {
        viewDelete = new JButton("<html><center>view/delete<br />previously logged days.</center></html>");
        viewDelete.setActionCommand("view");
        viewDelete.addActionListener(this);
        viewDelete.setPreferredSize(new Dimension(175, 100));
        middlePanel.add(viewDelete);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Entry")) {
            new EntryFrame(flowTracker);
        } else if (e.getActionCommand().equals("view")) {
            new ViewFrame(flowTracker);
        }
    }

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
    // EFFECTS: loads workroom from file
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