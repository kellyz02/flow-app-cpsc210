package gui;

import model.FlowDay;
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

public class MainFrame2 extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
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


    public MainFrame2() throws FileNotFoundException, IOException {
        super("flow app");
        createFrame();
        createPanels();
        createButtons();
        flowTracker = new FlowTracker("my flow tracker");


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        flowTracker = new FlowTracker("My flow tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void createPanels() throws IOException {
        topPanel = new JPanel(new FlowLayout());
        add(topPanel);
        middlePanel = new JPanel(new FlowLayout());
        add(middlePanel);
        bottomPanel = new JPanel(new FlowLayout());
        add(bottomPanel);
        BufferedImage logo = ImageIO.read(new File("./smaller.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(50, 50, Image.SCALE_FAST)));
        topPanel.add(logoLabel);
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
        middlePanel.add(newEntry);
    }

    public void viewDeleteButton() {
//        viewDelete = new JButton(new ViewDeleteAction());
//        middlePanel.add(viewDelete);
        viewDelete.setActionCommand("view");
        viewDelete.addActionListener(this);
        middlePanel.add(viewDelete);
    }

    public void saveButton() {
        saveButton = new JButton(new SaveAction());
        bottomPanel.add(saveButton);
    }

    public void loadButton() {
        loadButton = new JButton(new LoadAction());
        bottomPanel.add(loadButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Entry")) {
            new EntryFrame2(flowTracker);
        } else if (e.getActionCommand().equals("view")) {
            new ViewFrame2(flowTracker);
        }
    }

//    private class ViewDeleteAction extends AbstractAction {
//
//        ViewDeleteAction() {
//            super("view/delete logged days");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            new ViewFrame2(flowTracker);
//
//        }
//    }

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
            new ImprovedFlowAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException e) {
            System.out.println("picture not found");
        }
    }
}