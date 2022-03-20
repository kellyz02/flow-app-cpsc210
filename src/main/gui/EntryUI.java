package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntryUI extends JFrame implements ActionListener {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 300;
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
    private JLabel symptom;

    public EntryUI() {
        super("log a new entry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
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
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {

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
        dateEntry.add(dateField);
        dateField = new JTextField(dtf.format(localDate));
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
        ButtonGroup fc = new ButtonGroup();
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
        ButtonGroup mc = new ButtonGroup();
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
        ButtonGroup sc = new ButtonGroup();
        sc.add(cramps);
        sc.add(fatigue);
        sc.add(headaches);
        sc.add(cravings);
        sc.add(none);
    }


    public static void main(String[] args) {
        new EntryUI();
    }
}
