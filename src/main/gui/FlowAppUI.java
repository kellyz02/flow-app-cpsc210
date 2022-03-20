package gui;

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

import static java.awt.Color.lightGray;

public class FlowAppUI extends JFrame implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private JButton newEntry;
    private JButton viewDelete;

    public FlowAppUI() {
        super("flow app");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        newEntry = new JButton("log a new entry");
        viewDelete = new JButton("view/delete previously logged days");
        newEntry.setActionCommand("New Entry");
        newEntry.addActionListener(this);
        newEntry.setActionCommand("view/delete");
        add(newEntry);
        add(viewDelete);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("New Entry")) {
            new EntryUI();
        } else if (action.equals("view/delete")) {
            new ViewUI();
        }
    }


    public static void main(String[] args) {
        new FlowAppUI();
    }
}
