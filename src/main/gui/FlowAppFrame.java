package gui;

import model.FlowTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class FlowAppFrame extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 700;
    private JButton newEntry;
    private JButton viewDelete;
    private JButton saveButton;
    private JButton loadButton;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

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
    }

    public void loadPreviousDays() {
        ImageIcon loadIcon = new ImageIcon("filesicon3.png");
//        Image loadImage = loadIcon.getImage();
//        Image newLoadImage = loadImage.getScaledInstance(150,100, Image.SCALE_SMOOTH);
//        loadIcon = new ImageIcon(newLoadImage);
        int result = JOptionPane.showConfirmDialog(null,
                "would you like to load your previously logged entries?", "welcome to flow app! first...",
                YES_NO_OPTION, INFORMATION_MESSAGE, loadIcon);
        switch (result) {
            case JOptionPane.YES_OPTION:
                try {
                    flowTracker = jsonReader.read();
                    System.out.println("Loaded " + flowTracker.getName() + " from " + JSON_STORE);
                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
                break;
            case JOptionPane.NO_OPTION:
            default:
        }
    }


    public void createPanels() throws IOException {
        topPanel = new JPanel(new FlowLayout());
        add(topPanel);
        middlePanel = new JPanel(new FlowLayout());
        add(middlePanel);
        bottomPanel = new JPanel(new FlowLayout());
        add(bottomPanel);
        BufferedImage logo = ImageIO.read(new File("300x300.png"));
//        BufferedImage logo200 = ImageIO.read(new File("200x200.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
//        JLabel logoLabel200 = new JLabel(new ImageIcon(logo200));
//        JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(200, 200, Image.SCALE_FAST)));
        topPanel.add(logoLabel);
//        topPanel.add(logoLabel200);
    }

    public void createFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    }

    public void createButtons() {
        entryButton();
        viewDeleteButton();
        saveButton();
        loadButton();
    }

    public void entryButton() {
        newEntry = new JButton("log a new entry");
        newEntry.setActionCommand("New Entry");
        newEntry.addActionListener(this);
        newEntry.setPreferredSize(new Dimension(200, 100));
        middlePanel.add(newEntry);
    }

    public void viewDeleteButton() {
        viewDelete = new JButton("view/delete previously logged days");
        viewDelete.setActionCommand("view");
        viewDelete.addActionListener(this);
        viewDelete.setPreferredSize(new Dimension(200, 100));
        middlePanel.add(viewDelete);
    }

    public void saveButton() {
        saveButton = new JButton(new SaveAction());
        saveButton.setPreferredSize(new Dimension(200, 100));
        bottomPanel.add(saveButton);
    }

    public void loadButton() {
        loadButton = new JButton(new LoadAction());
        loadButton.setPreferredSize(new Dimension(200, 100));
        bottomPanel.add(loadButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Entry")) {
            new EntryFrame(flowTracker);
        } else if (e.getActionCommand().equals("view")) {
            new ViewFrame(flowTracker);
        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("save logged days");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(flowTracker);
                jsonWriter.close();
                System.out.println("Saved " + flowTracker.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("load logged days");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                flowTracker = jsonReader.read();
                System.out.println("Loaded " + flowTracker.getName() + " from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
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