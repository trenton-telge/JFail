package com.eventhorizonwebdesign.jfail.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Trenton on 11/25/2015.
 */
public class ErrorFrame extends JFrame{
    public ErrorFrame(boolean fatal, String title, String message){
        JPanel panel = new JPanel(new BorderLayout());
        this.setContentPane(panel);
        this.setTitle(title);
        JLabel l = new JLabel(message, SwingConstants.CENTER);
        if (fatal){l.setForeground(Color.RED);}
        this.getContentPane().add(l, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 100));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.getContentPane().setVisible(true);
        this.setVisible(true);
    }
}
