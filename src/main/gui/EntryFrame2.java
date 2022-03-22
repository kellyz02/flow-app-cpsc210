package gui;

import model.FlowDay;
import model.FlowTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EntryFrame2 extends JInternalFrame implements ActionListener {

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
    private JLabel flow;
    private JLabel date;
    private JLabel mood;
    private JLabel loggedDay;
    private JLabel symptom;
    private ButtonGroup fc;
    private ButtonGroup mc;
    private ButtonGroup sc;
    private JButton finishEntry;
    private FlowTracker flowTracker;

    public EntryFrame2(FlowTracker flowTracker) {
        super("log a new entry", false, true, false, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        JLabel entryFrameLabel = new JLabel("please fill in the fields for the new entry");
        getContentPane().add(entryFrameLabel, BorderLayout.CENTER);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.flowTracker = flowTracker;
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
        add(finishEntry);
        add(loggedDay);
    }

    public void createEntryPanels() {
        dateEntry = new JPanel(new FlowLayout());
        flowEntry = new JPanel(new FlowLayout());
        moodEntry = new JPanel(new FlowLayout());
        symptomEntry = new JPanel(new FlowLayout());
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
        finishEntry = new JButton("finish entry");
        finishEntry.setActionCommand("finish entry");
        finishEntry.addActionListener(this);
        loggedDay = new JLabel();
    }

    public void createField() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        dateField = new JTextField(dtf.format(localDate));
    }

    public void entryLabels() {
        flow = new JLabel("flow: ");
        date = new JLabel("date: ");
        mood = new JLabel("mood: ");
        symptom = new JLabel("symptom: ");
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
        }
    }

    private void printAttributes(FlowDay currentDay) {
        String returnAttributes = "On " + currentDay.getDayName() + ", your flow was " + currentDay.getFlow()
                + ". You were feeling " + currentDay.getMood() + ". You experienced "
                + currentDay.getSymptom() + ".";
        loggedDay.setText(returnAttributes);
    }
}