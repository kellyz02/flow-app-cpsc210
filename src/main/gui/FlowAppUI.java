package gui;

import model.FlowTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FlowAppUI extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JButton newEntry;
    private JButton viewDelete;
    private FlowTracker flowTracker;
    private JPanel entryViewPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/flowTracker.json";


    public FlowAppUI() {
        super("flow app");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        flowTracker = new FlowTracker("My flow tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        createEntryViewPanel();
        entryButton();
        viewDeleteButton();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    public void entryButton() {
        newEntry = new JButton("log a new entry");
        newEntry.setActionCommand("New Entry");
        newEntry.addActionListener(this);
        entryViewPanel.add(newEntry);
    }

    public void viewDeleteButton() {
        viewDelete = new JButton("view/delete previously logged days");
        viewDelete.setActionCommand("view/delete");
        viewDelete.addActionListener(this);
        entryViewPanel.add(viewDelete);
    }

    public void createEntryViewPanel() {
        entryViewPanel = new JPanel(new FlowLayout());
        add(entryViewPanel);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("New Entry")) {
            new EntryUI();
        } else if (action.equals("view/delete")) {
            new ViewUI(flowTracker);
        }
    }

    // EFFECTS: saves the workroom to file
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
        new FlowAppUI();
    }
}
