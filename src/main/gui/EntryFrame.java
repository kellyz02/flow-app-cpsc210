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
import java.util.Objects;
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
    private JRadioButton pms;
    private JRadioButton angry;
    private JRadioButton cramps;
    private JRadioButton fatigue;
    private JRadioButton headaches;
    private JRadioButton cravings;
    private JRadioButton none;
    private JRadioButton noFlow;
    private JPanel flowEntry;
    private JPanel moodEntry;
    private JPanel symptomEntry;
    private JPanel dateEntry;
    private JPanel helperPanel;
    private JPanel organizePanel;
    private JPanel finishEntryPanel;
    private JLabel flow;
    private JLabel date;
    private JLabel mood;
    private JLabel errorMessage;
    private JLabel symptom;
    private JLabel entryFrameLabel;
    private ButtonGroup fc;
    private ButtonGroup mc;
    private ButtonGroup sc;
    private JButton finishEntry;
    private FlowTracker flowTracker;
    private JPanel errorMessagePanel;

    public EntryFrame(FlowTracker flowTracker) {
        super("log a new entry!");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
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
        add(dateEntry);
        add(organizePanel);
        add(finishEntryPanel);
        add(errorMessagePanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void createEntryPanels() {
        dateEntry = new JPanel();
        dateEntry.setLayout(new FlowLayout(FlowLayout.CENTER));
        flowEntry = new JPanel();
        flowEntry.setLayout(new BoxLayout(flowEntry, BoxLayout.PAGE_AXIS));
        moodEntry = new JPanel();
        moodEntry.setLayout(new BoxLayout(moodEntry, BoxLayout.PAGE_AXIS));
        symptomEntry = new JPanel();
        symptomEntry.setLayout(new BoxLayout(symptomEntry, BoxLayout.PAGE_AXIS));
        finishEntryPanel = new JPanel();
        finishEntryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        errorMessagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        organizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        organizePanel.add(flowEntry);
        organizePanel.add(moodEntry);
        organizePanel.add(symptomEntry);
        errorMessage = new JLabel();
        errorMessagePanel.add(errorMessage);
    }

    public void labelPanel() {
        entryFrameLabel = new JLabel("please select an option for the");
        JLabel entryLabel = new JLabel("following fields to log your entry.");
        entryLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        entryFrameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        helperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(helperPanel);
        helperPanel.add(entryFrameLabel);
        helperPanel.add(entryLabel);
    }

    public void addEntryButtons() {
        flowEntry.add(flow);
        flowEntry.add(spotting);
        flowEntry.add(light);
        flowEntry.add(medium);
        flowEntry.add(heavy);
        flowEntry.add(noFlow);
        moodEntry.add(mood);
        moodEntry.add(sad);
        moodEntry.add(happy);
        moodEntry.add(angry);
        moodEntry.add(pms);
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
    }


    public void createField() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        dateField = new JTextField(dtf.format(localDate));
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setSize(10, 10);
    }

    public void entryLabels() {
        flow = new JLabel("flow: ");
        flow.setFont(new Font("Dialog", Font.BOLD, 14));
        date = new JLabel("date (DD/MM/YYYY): ");
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
        noFlow = new JRadioButton("none");
        noFlow.setActionCommand("none");
        fc = new ButtonGroup();
        fc.add(spotting);
        fc.add(light);
        fc.add(medium);
        fc.add(heavy);
        fc.add(noFlow);
    }

    public void moodChoices() {
        happy = new JRadioButton("happy");
        happy.setActionCommand("happy");
        sad = new JRadioButton("sad");
        sad.setActionCommand("sad");
        angry = new JRadioButton("angry");
        angry.setActionCommand("angry");
        unmotivated = new JRadioButton("unmotivated");
        unmotivated.setActionCommand("unmotivated");
        pms = new JRadioButton("PMS");
        pms.setActionCommand("PMS");
        mc = new ButtonGroup();
        mc.add(happy);
        mc.add(sad);
        mc.add(angry);
        mc.add(unmotivated);
        mc.add(pms);
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
                finishEntry(currentDay);
            } else {
                errorMessage.setText("date incorrectly formatted! please try again");
            }
        }
        add(errorMessage);
    }


    public void finishEntry(FlowDay currentDay) {
        ImageIcon finishIcon = new ImageIcon("filesicon3.png");
        String[] newOptions = new String[2];
        newOptions[0] = "log another entry";
        newOptions[1] = "return to home";
        int result = JOptionPane.showOptionDialog(this.getContentPane(),
                printAttributes(currentDay),
                "your entry has been successfully logged!", 0,
                JOptionPane.INFORMATION_MESSAGE, finishIcon, newOptions, null);
        switch (result) {
            case 0:
                mc.clearSelection();
                fc.clearSelection();
                sc.clearSelection();
                break;
            case 1:
                this.dispose();
            default:
        }
    }


    public void addAttributes(FlowDay flowDay) {
        processFlowCommand(flowDay);
        processMoodCommand(flowDay);
        processSymptomCommand(flowDay);
    }

    public void processFlowCommand(FlowDay flowDay) {
        if (fc.getSelection() != null) {
            String command = fc.getSelection().getActionCommand();
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
                case "none":
                    flowDay.enterFlow("none");
                    break;
            }
        } else {
            flowDay.enterFlow("");
        }

    }

    public void processMoodCommand(FlowDay flowDay) {
        if (mc.getSelection() != null) {
            String command = mc.getSelection().getActionCommand();
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
                case "PMS":
                    flowDay.enterMood("PMS");
                    break;
            }
        } else {
            flowDay.enterMood("");
        }

    }

    public void processSymptomCommand(FlowDay flowDay) {
        if (sc.getSelection() != null) {
            String command = sc.getSelection().getActionCommand();
            switch (command) {
                case "cramps":
                    flowDay.enterSymptom("cramps");
                    break;
                case "fatigue":
                    flowDay.enterSymptom("fatigue");
                    break;
                case "food cravings":
                    flowDay.enterSymptom("food cravings");
                    break;
                case "headaches":
                    flowDay.enterSymptom("headaches");
                    break;
                case "none":
                    flowDay.enterSymptom("no symptoms");
                    break;
            }
        } else {
            flowDay.enterSymptom("");
        }
    }

    private String printAttributes(FlowDay currentDay) {
        String flow = "";
        String mood = "";
        String symptom = "";

        if (!Objects.equals(currentDay.getFlow(), "")) {
            flow = ", your flow was " + currentDay.getFlow();
        }
        if (!Objects.equals(currentDay.getMood(), "")) {
            mood = ". You were feeling " + currentDay.getMood();
        }
        if (!Objects.equals(currentDay.getSymptom(), "")) {
            symptom = ". You experienced " + currentDay.getSymptom();
        }
        String returnAttributes = "On " + currentDay.getDayName() + flow + mood + symptom + ".";
        return returnAttributes;
    }
}
