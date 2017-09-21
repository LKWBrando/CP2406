package Week9.PracticalExercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JMovieFrame {
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Movie stuff");
        frame1.setSize(800,150);
        frame1.setVisible(true);

        JPanel northPanel = new JPanel();
        northPanel.setVisible(true);
        frame1.add(northPanel, BorderLayout.NORTH);

        JButton northButton = new JButton("North Movie");
        JLabel northLabel = new JLabel("Released on 1999, Staring AAA");
        northLabel.setVisible(false);
        northPanel.add(northButton);
        northPanel.add(northLabel);
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                northLabel.setVisible(true);
            }
        });

        JPanel westPanel = new JPanel();
        westPanel.setVisible(true);
        frame1.add(westPanel, BorderLayout.WEST);

        JButton westButton = new JButton("West Movie");
        JLabel westLabel = new JLabel("Released on 1998, Staring BBB");
        westLabel.setVisible(false);
        westPanel.add(westButton);
        westPanel.add(westLabel);
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                westLabel.setVisible(true);
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setVisible(true);
        frame1.add(centerPanel, BorderLayout.CENTER);

        JButton centerButton = new JButton("Center Movie");
        JLabel centerLabel = new JLabel("Released on 1997, Starting CCC");
        centerLabel.setVisible(false);
        centerPanel.add(centerButton);
        centerPanel.add(centerLabel);
        centerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerLabel.setVisible(true);
            }
        });

        JPanel eastPanel = new JPanel();
        eastPanel.setVisible(true);
        frame1.add(eastPanel, BorderLayout.EAST);

        JButton eastButton = new JButton("East Movie");
        JLabel eastLabel = new JLabel("Released on 1996, Staring DDD" );
        eastLabel.setVisible(false);
        eastPanel.add(eastButton);
        eastPanel.add(eastLabel);
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastLabel.setVisible(true);
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.setVisible(true);
        frame1.add(southPanel, BorderLayout.SOUTH);

        JButton southButton = new JButton("South Movie");
        JLabel southLabel = new JLabel("Released on 1995, Staring EEE");
        southLabel.setVisible(false);
        southPanel.add(southButton);
        southPanel.add(southLabel);
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                southLabel.setVisible(true);
            }
        });
    }

}
