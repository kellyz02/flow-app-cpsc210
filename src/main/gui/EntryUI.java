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

public class EntryUI extends JFrame implements ActionListener {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 500;
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
    private FlowTracker ft;
    private JButton finishEntry;

    public EntryUI() {
        super("log a new entry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        ft = new FlowTracker("my flow tracker");
        dateEntry = new JPanel(new FlowLayout());
        flowEntry = new JPanel(new FlowLayout());
        moodEntry = new JPanel(new FlowLayout());
        symptomEntry = new JPanel(new FlowLayout());
        flowChoices();
        moodChoices();
        symptomChoices();
        labels();
        addButtons();
        pack();
        add(dateEntry);
        add(flowEntry);
        add(moodEntry);
        add(symptomEntry);
        add(finishEntry);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }



    public void addButtons() {
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        dateField = new JTextField(dtf.format(localDate));
        dateEntry.add(dateField);
        finishEntry = new JButton("finish entry");
    }

    public void labels() {
        flow = new JLabel("flow: ");
        date = new JLabel("date: ");
        mood = new JLabel("mood: ");
        symptom = new JLabel("symptom: ");
    }


    public void flowChoices() {
        spotting = new JRadioButton("spotting");
        light = new JRadioButton("light");
        medium = new JRadioButton("medium");
        heavy = new JRadioButton("heavy");
        fc = new ButtonGroup();
        fc.add(spotting);
        fc.add(light);
        fc.add(medium);
        fc.add(heavy);
    }

    public void moodChoices() {
        happy = new JRadioButton("happy");
        sad = new JRadioButton("sad");
        angry = new JRadioButton("angry");
        unmotivated = new JRadioButton("unmotivated");
        mc = new ButtonGroup();
        mc.add(happy);
        mc.add(sad);
        mc.add(angry);
        mc.add(unmotivated);
    }

    public void symptomChoices() {
        cramps = new JRadioButton("cramps");
        fatigue = new JRadioButton("fatigue");
        headaches = new JRadioButton("headaches");
        cravings = new JRadioButton("food cravings");
        none = new JRadioButton("no symptoms");
        sc = new ButtonGroup();
        sc.add(cramps);
        sc.add(fatigue);
        sc.add(headaches);
        sc.add(cravings);
        sc.add(none);
    }

    public void actionPerformed(ActionEvent e) throws IncorrectDateException {
        if (e.getActionCommand().equals("finish entry")) {
            String dateNamePattern = "\\d\\d/\\d\\d\\/\\d\\d\\d\\d";
            String enteredDate = dateField.getText();
            if (Pattern.matches(dateNamePattern, enteredDate)) {
                String[] monthNameArray = enteredDate.split("/", 2);
                String monthName = monthNameArray[1];
                FlowDay currentDay = ft.addEntry(enteredDate, monthName);
                addAttributes(currentDay);
                printAttributes(currentDay);
                add(loggedDay);
            } else

        }

    }

    public void addAttributes(FlowDay flowDay) {
        processFlowCommand(fc.getSelection().getActionCommand(), flowDay);
        processMoodCommand(mc.getSelection().getActionCommand(), flowDay);
        processSymptomCommand(sc.getSelection().getActionCommand(),flowDay);
    }

    public void processFlowCommand(String command, FlowDay flowDay) {
        if (command.equals("spotting")) {
            flowDay.enterFlow("spotting");
        } else if (command.equals("light")) {
            flowDay.enterFlow("light");
        } else if (command.equals("medium")) {
            flowDay.enterFlow("medium");
        } else if (command.equals("heavy")) {
            flowDay.enterFlow("heavy");
        }
    }

    public void processMoodCommand(String command, FlowDay flowDay) {
        if (command.equals("happy")) {
            flowDay.enterFlow("happy");
        } else if (command.equals("sad")) {
            flowDay.enterFlow("sad");
        } else if (command.equals("angry")) {
            flowDay.enterFlow("angry");
        } else if (command.equals("unmotivated")) {
            flowDay.enterFlow("unmotivated");
        }
    }

    public void processSymptomCommand(String command, FlowDay flowDay) {
        if (command.equals("cramps")) {
            flowDay.enterSymptom("cramps");
        } else if (command.equals("fatigue")) {
            flowDay.enterSymptom("fatigue");
        } else if (command.equals("cravings")) {
            flowDay.enterSymptom("food cravings");
        } else if (command.equals("headaches")) {
            flowDay.enterSymptom("headaches");
        } else if (command.equals("none")) {
            flowDay.enterSymptom("no symptoms");
        }
    }

    private void printAttributes(FlowDay currentDay) {
        String returnAttributes = "On " + currentDay.getDayName() + ", your flow was " + currentDay.getFlow()
                        + ". You were feeling " + currentDay.getMood() + ". You experienced "
                        + currentDay.getSymptom() + ".";
        loggedDay = new JLabel(returnAttributes);
    }


    public static void main(String[] args) {
        new EntryUI();
    }
}
