package gui;

import model.FlowDay;
import model.FlowTracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ViewUI extends JFrame implements ActionListener {
    private static final int HEIGHT = 400;
    private static final int WIDTH = 500;
    private JList monthList;
    private JLabel errorMessage;
    private JLabel daysInMonth;
    private JPanel panel;
    private JSplitPane splitPane;
    private DefaultListModel<String> monthObjectList;

    public ViewUI(FlowTracker flowTracker) {
        super("view previously logged months");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        addErrorMessage();
        monthsPanel(flowTracker);
        add(splitPane);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);



    }

    public void addErrorMessage() {
        errorMessage = new JLabel();
        add(errorMessage);
    }

    public void monthsPanel(FlowTracker flowTracker) {
        monthObjectList = new DefaultListModel<>();
        daysInMonth = new JLabel();
        if (flowTracker.getKeys().isEmpty()) {
            errorMessage.setText("you have not logged any days yet!");
        } else {
            for (String monthYear : flowTracker.getKeys()) {
                monthObjectList.addElement(monthYear);
            }
        }

        monthList = new JList(monthObjectList); //data has type Object[]
        monthList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        monthList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        monthList.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(monthList);
        listScroller.setPreferredSize(new Dimension(250, 80));
        splitPane.setLeftComponent(listScroller);
        panel.add(daysInMonth);
        splitPane.setRightComponent(panel);

    }

    public void actionPerformed(ActionEvent e) {
        // stub
    }

    public static void main(String[] args) {
        new ViewUI(new FlowTracker("my flow tracker"));
    }


}
