package gui;

import model.FlowDay;
import model.FlowMonth;
import model.FlowTracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFrame extends JFrame implements ActionListener {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 700;
    private JList monthList;
    private JList dayList;
    private JLabel errorMessage;
    private JLabel daysInMonth;
    private JTextArea viewedDay;
    private JPanel loggedMonths;
    private JPanel loggedDays;
    private JPanel viewEntry;
    private JScrollPane scrollMonths;
    private JScrollPane scrollDays;
    private JSplitPane monthSplitPane;
    private JSplitPane daySplitPane;

    private JButton viewMonthButton;
    private JButton viewDayButton;
    private JButton deleteDayButton;

    private DefaultListModel<String> monthObjectList;
    private DefaultListModel<String> dayObjectList;
    private FlowTracker flowTracker;
    private FlowMonth selectedMonth;

    public ViewFrame(FlowTracker flowTracker) {
        super("view/delete your previously tracked days");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 410));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.flowTracker = flowTracker;
        createPanes();
        createButtons();
        formatSplitPane();
        add(monthSplitPane);
        pack();
        setVisible(true);
        setResizable(false);
    }

    public void addErrorMessage() {
        errorMessage = new JLabel();
        loggedMonths.add(errorMessage);
    }

    public void createPanes() {
        monthSplitPane = new JSplitPane();
        monthSplitPane.setPreferredSize(new Dimension(1200, 400));
        loggedMonths = new JPanel();
        loggedMonths.setPreferredSize(new Dimension(100, 400));
//        loggedMonths.setLayout(new BoxLayout(loggedMonths, BoxLayout.PAGE_AXIS));
        daySplitPane = new JSplitPane();
        loggedDays = new JPanel();
        viewEntry = new JPanel();
        viewEntry.setLayout(new BoxLayout(viewEntry, BoxLayout.PAGE_AXIS));
        errorMessage = new JLabel();
        loggedMonths.add(errorMessage);
    }

    public void formatSplitPane() {
        monthObjectList = new DefaultListModel<>();
        if (flowTracker.getKeys().isEmpty()) {
            errorMessage.setText("you have not logged any days yet!");
        } else {
            for (String monthYear : flowTracker.getKeys()) {
                monthObjectList.addElement(monthYear);
            }
        }

        monthList = new JList(monthObjectList);
        monthList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        monthList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        monthList.setVisibleRowCount(-1);

        scrollMonths = new JScrollPane(monthList);

        daySplitPane.setLeftComponent(loggedDays);
        daySplitPane.setRightComponent(viewEntry);
        JLabel loggedDaysLabel = new JLabel("your entries in the selected month");
        loggedDays.add(loggedDaysLabel);
        JLabel entryLabel = new JLabel("the entry for the selected day");
        viewEntry.add(entryLabel);
        monthSplitPane.setLeftComponent(loggedMonths);
        monthSplitPane.setRightComponent(daySplitPane);

        setDayAndMonthPanel();

    }

    public void setDayAndMonthPanel() {
        viewedDay = new JTextArea();
        viewedDay.setSize(50, 100);
        viewEntry.add(viewedDay);

        loggedMonths.add(scrollMonths);
        loggedMonths.add(viewMonthButton);
        scrollDays = new JScrollPane();
    }

    public void selectMonth() {
        dayObjectList = new DefaultListModel<>();
        String selectedMonthName = monthList.getSelectedValue().toString();
        selectedMonth = flowTracker.getMonth(selectedMonthName);
        for (FlowDay fd : selectedMonth.getFlowDays()) {
            String dayName = fd.getDayName();
            dayObjectList.addElement(dayName);
        }
        dayList = new JList(dayObjectList);
        dayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        dayList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        dayList.setVisibleRowCount(-1);

        loggedDays.remove(scrollDays);
        scrollDays = new JScrollPane(dayList);
        loggedDays.add(scrollDays);
        loggedDays.add(viewDayButton);
        loggedDays.add(deleteDayButton);
        revalidate();
        repaint();
        refreshPanes();
    }

    public void createButtons() {
        viewDayButton = new JButton("view selected day");
        viewDayButton.setActionCommand("view day");
        viewDayButton.addActionListener(this);

        deleteDayButton = new JButton("delete selected day");
        deleteDayButton.setActionCommand("delete day");
        deleteDayButton.addActionListener(this);

        viewMonthButton = new JButton("view selected month");
        viewMonthButton.setActionCommand("view month");
        viewMonthButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("view month")) {
            selectMonth();
        } else if (e.getActionCommand().equals("view day")) {
            viewDay();
        } else if (e.getActionCommand().equals("delete day")) {
            int response = (JOptionPane.showConfirmDialog(null, "are you sure you want to delete this entry?",
                    "delete entry?", JOptionPane.YES_NO_OPTION));

            if (response == (JOptionPane.YES_OPTION)) {
                deleteDay();
                int i = dayList.getSelectedIndex();
                if (i != -1) {
                    dayObjectList.removeElementAt(i + 1);
                }
                refreshPanes();
                revalidate();
                repaint();
            }
        }
    }

    public void refreshPanes() {
        dayObjectList.clear();
        monthObjectList.clear();
        for (FlowDay fd : selectedMonth.getFlowDays()) {
            String dayName = fd.getDayName();
            dayObjectList.addElement(dayName);
        }
        for (String monthYear : flowTracker.getKeys()) {
            monthObjectList.addElement(monthYear);
        }
        loggedMonths.revalidate();
        loggedMonths.repaint();
        loggedDays.revalidate();
        loggedDays.repaint();

        viewEntry.repaint();
        viewEntry.revalidate();
    }


    public void viewDay() {
        String selectedDayName = dayList.getSelectedValue().toString();
        FlowDay selectedDay = selectedMonth.findFlowDay(selectedDayName);
        viewEntry.remove(viewedDay);
        printAttributes(selectedDay);
        viewEntry.add(viewedDay);
        revalidate();
        repaint();
    }

    public void deleteDay() {
        String selectedDayName = dayList.getSelectedValue().toString();
        FlowDay selectedDay = selectedMonth.findFlowDay(selectedDayName);
        selectedMonth.deleteFlowDay(selectedDay);
        if (flowTracker.emptyMonth(selectedMonth.getMonthName())) {
            flowTracker.deleteMonth(selectedMonth.getMonthName());
        }
        refreshPanes();
        revalidate();
        repaint();

        viewedDay = null;
        viewEntry.repaint();
        viewEntry.revalidate();
    }


    private void printAttributes(FlowDay currentDay) {
        String flow = "";
        String mood = "";
        String symptom = "";

        if (!currentDay.getFlow().equals("")) {
            flow = ", your flow was " + currentDay.getFlow();
        }
        if (!currentDay.getMood().equals("")) {
            mood = ". You were feeling " + currentDay.getMood();
        }
        if (!currentDay.getSymptom().equals("")) {
            symptom = ". You experienced " + currentDay.getSymptom();
        }

        String returnAttributes = "On " + currentDay.getDayName() + flow + mood + symptom + ".";
        viewedDay.setText(returnAttributes);
        revalidate();
        repaint();
    }


}
