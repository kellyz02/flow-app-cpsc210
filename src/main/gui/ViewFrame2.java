package gui;

import model.FlowTracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFrame2 extends JInternalFrame implements ActionListener {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 500;
    private JList monthList;
    private JLabel errorMessage;
    private JLabel daysInMonth;
    private JPanel loggedMonths;
    private JPanel loggedDays;
    private JPanel viewEntry;
    private JScrollPane scrollMonths;
    private JScrollPane scrollDays;
    private JSplitPane monthSplitPane;
    private JSplitPane daySplitPane;
    private DefaultListModel<String> monthObjectList;
    private FlowTracker flowTracker;

    public ViewFrame2(FlowTracker flowTracker) {
        super("View/Delete Previously Logged Days", false, true, false, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.flowTracker = flowTracker;
        createPanes();
        formatSplitPane();
        add(monthSplitPane);
        pack();
        setVisible(true);
        setResizable(false);
        pack();

    }

    public void addErrorMessage() {
        errorMessage = new JLabel();
        loggedMonths.add(errorMessage);
    }

    public void createPanes() {
        monthSplitPane = new JSplitPane();
        loggedMonths = new JPanel();
        daySplitPane = new JSplitPane();
        loggedDays = new JPanel();
        viewEntry = new JPanel();
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

        daySplitPane.setLeftComponent(scrollDays);
        daySplitPane.setRightComponent(viewEntry);
        monthSplitPane.setLeftComponent(scrollMonths);
        monthSplitPane.setRightComponent(daySplitPane);

    }

    public void selectMonth() {
        monthList.getSelectedValue();
    }

    public void actionPerformed(ActionEvent e) {
        // stub
    }
}
