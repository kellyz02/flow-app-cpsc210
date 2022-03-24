package gui;

import model.FlowDay;
import model.FlowTracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EntryFrame extends JFrame implements ActionListener {

    private JTextField dateField;
    private JRadioButton spotting;
    private JRadioButton light;
    private JRadioButton medium;
    private JRadioButton heavy;
    private JRadioButton happy;
    private JRadioButton sad;
    private JRadioButton unmotivated;
    private JRadioButton angry;
    private JRadioButton cramps;
    private JRadioButton fatigue;
    private JRadioButton headaches;
    private JRadioButton cravings;
    private JRadioButton none;
    private JPanel flowEntry;
    private JPanel moodEntry;
    private JPanel symptomEntry;
    private JPanel dateEntry;
    private JPanel helperPanel;
    private JPanel finishEntryPanel;
    private JLabel flow;
    private JLabel date;
    private JLabel mood;
    private JLabel loggedDay;
    private JLabel symptom;
    private JLabel entryFrameLabel;
    private ButtonGroup fc;
    private ButtonGroup mc;
    private ButtonGroup sc;
    private JButton finishEntry;
    private FlowTracker flowTracker;

    public EntryFrame(FlowTracker flowTracker) {
        super("log a new entry!");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        this.flowTracker = flowTracker;
        labelPanel();
        createEntryPanels();
        flowChoices();
        moodChoices();
        symptomChoices();
        entryLabels();
        addEntryButtons();
        pack();
        add(dateEntry);
        add(flowEntry);
        add(moodEntry);
        add(symptomEntry);
        add(finishEntryPanel);
        add(loggedDay);
        setVisible(true);
    }

    public void createEntryPanels() {
        dateEntry = new JPanel();
        dateEntry.setLayout(new BoxLayout(dateEntry, BoxLayout.PAGE_AXIS));
        flowEntry = new JPanel();
        flowEntry.setLayout(new BoxLayout(flowEntry, BoxLayout.PAGE_AXIS));
        moodEntry = new JPanel();
        moodEntry.setLayout(new BoxLayout(moodEntry, BoxLayout.PAGE_AXIS));
        symptomEntry = new JPanel();
        symptomEntry.setLayout(new BoxLayout(symptomEntry, BoxLayout.PAGE_AXIS));
        finishEntryPanel = new JPanel();
        finishEntryPanel.setLayout(new BoxLayout(finishEntryPanel, BoxLayout.PAGE_AXIS));
    }

    public void labelPanel() {
        entryFrameLabel = new JLabel("Please select options for the following fields to log your entry! :)");
        entryFrameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        helperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(helperPanel);
        helperPanel.add(entryFrameLabel);
    }

    public void addEntryButtons() {
        flowEntry.add(flow);
        flowEntry.add(spotting);
        flowEntry.add(light);
        flowEntry.add(medium);
        flowEntry.add(heavy);
        moodEntry.add(mood);
        moodEntry.add(sad);
        moodEntry.add(happy);
        moodEntry.add(angry);
        moodEntry.add(unmotivated);
        symptomEntry.add(symptom);
        symptomEntry.add(cramps);
        symptomEntry.add(fatigue);
        symptomEntry.add(headaches);
        symptomEntry.add(cravings);
        symptomEntry.add(none);
        dateEntry.add(date);
        createField();
        dateEntry.add(dateField);
        finishEntryPanel();
    }

    public void finishEntryPanel() {
        finishEntry = new JButton("finish entry");
        finishEntry.setActionCommand("finish entry");
        finishEntry.addActionListener(this);
        finishEntryPanel.add(finishEntry);
        loggedDay = new JLabel();
        finishEntryPanel.add(loggedDay);
    }


    public void createField() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        dateField = new JTextField(dtf.format(localDate));
    }

    public void entryLabels() {
        flow = new JLabel("flow: ");
        flow.setFont(new Font("Dialog", Font.BOLD, 14));
        date = new JLabel("date: ");
        date.setFont(new Font("Dialog", Font.BOLD, 14));
        mood = new JLabel("mood: ");
        mood.setFont(new Font("Dialog", Font.BOLD, 14));
        symptom = new JLabel("symptom: ");
        symptom.setFont(new Font("Dialog", Font.BOLD, 14));
    }


    public void flowChoices() {
        spotting = new JRadioButton("spotting");
        spotting.setActionCommand("spotting");
        light = new JRadioButton("light");
        light.setActionCommand("light");
        medium = new JRadioButton("medium");
        medium.setActionCommand("medium");
        heavy = new JRadioButton("heavy");
        heavy.setActionCommand("heavy");
        fc = new ButtonGroup();
        fc.add(spotting);
        fc.add(light);
        fc.add(medium);
        fc.add(heavy);
    }

    public void moodChoices() {
        happy = new JRadioButton("happy");
        happy.setActionCommand("happy");
        sad = new JRadioButton("sad");
        sad.setActionCommand("sad");
        angry = new JRadioButton("angry");
        angry.setActionCommand("angry");
        unmotivated = new JRadioButton("unmotivated");
        unmotivated.setActionCommand("angry");
        mc = new ButtonGroup();
        mc.add(happy);
        mc.add(sad);
        mc.add(angry);
        mc.add(unmotivated);
    }

    public void symptomChoices() {
        cramps = new JRadioButton("cramps");
        cramps.setActionCommand("cramps");
        fatigue = new JRadioButton("fatigue");
        fatigue.setActionCommand("fatigue");
        headaches = new JRadioButton("headaches");
        headaches.setActionCommand("headaches");
        cravings = new JRadioButton("food cravings");
        cravings.setActionCommand("food cravings");
        none = new JRadioButton("no symptoms");
        none.setActionCommand("none");
        sc = new ButtonGroup();
        sc.add(cramps);
        sc.add(fatigue);
        sc.add(headaches);
        sc.add(cravings);
        sc.add(none);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("finish entry")) {
            String dateNamePattern = "\\d\\d/\\d\\d\\/\\d\\d\\d\\d";
            String enteredDate = dateField.getText();
            if (Pattern.matches(dateNamePattern, enteredDate)) {
                String[] monthNameArray = enteredDate.split("/", 2);
                String monthName = monthNameArray[1];
                FlowDay currentDay = flowTracker.addEntry(enteredDate, monthName);
                addAttributes(currentDay);
                printAttributes(currentDay);
            } else {
                loggedDay.setText("date incorrectly formatted! please try again");
            }
        }
        add(loggedDay);
    }

    public void addAttributes(FlowDay flowDay) {
        processFlowCommand(fc.getSelection().getActionCommand(), flowDay);
        processMoodCommand(mc.getSelection().getActionCommand(), flowDay);
        processSymptomCommand(sc.getSelection().getActionCommand(),flowDay);
    }

    public void processFlowCommand(String command, FlowDay flowDay) {
        switch (command) {
            case "spotting":
                flowDay.enterFlow("spotting");
                break;
            case "light":
                flowDay.enterFlow("light");
                break;
            case "medium":
                flowDay.enterFlow("medium");
                break;
            case "heavy":
                flowDay.enterFlow("heavy");
                break;
            default:
                flowDay.enterFlow(null);
        }
    }

    public void processMoodCommand(String command, FlowDay flowDay) {
        switch (command) {
            case "happy":
                flowDay.enterMood("happy");
                break;
            case "sad":
                flowDay.enterMood("sad");
                break;
            case "angry":
                flowDay.enterMood("angry");
                break;
            case "unmotivated":
                flowDay.enterMood("unmotivated");
                break;
            default:
                flowDay.enterMood(null);
        }
    }

    public void processSymptomCommand(String command, FlowDay flowDay) {
        switch (command) {
            case "cramps":
                flowDay.enterSymptom("cramps");
                break;
            case "fatigue":
                flowDay.enterSymptom("fatigue");
                break;
            case "cravings":
                flowDay.enterSymptom("food cravings");
                break;
            case "headaches":
                flowDay.enterSymptom("headaches");
                break;
            case "none":
                flowDay.enterSymptom("no symptoms");
                break;
            default:
                flowDay.enterSymptom(null);
        }
    }

    private void printAttributes(FlowDay currentDay) {
        String flow = null;
        String mood = null;
        String symptom = null;

        if (currentDay.getFlow() != null) {
            flow = ", your flow was " + currentDay.getFlow();
        }
        if (currentDay.getMood() != null) {
            mood = ". You were feeling " + currentDay.getMood();
        }
        if (currentDay.getSymptom() != null) {
            symptom = ". You experienced " + currentDay.getSymptom();
        }
        String returnAttributes = "On " + currentDay.getDayName() + flow + mood + symptom + ".";
        loggedDay.setText(returnAttributes);
    }
}
