package Week8.PracticalExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JHistoricalFacts {
    public static void main(String[] args) {
        JFrame frameOne = new JFrame("Historical Facts");
        frameOne.setVisible(true);

        JPanel newPanel = new JPanel();
        frameOne.add(newPanel);

        JLabel labelOne = new JLabel("This is fact one");
        labelOne.setVisible(false);

        JLabel labelTwo = new JLabel("This is fact two");
        labelTwo.setVisible(false);

        JLabel labelThree = new JLabel("This is fact three");
        labelThree.setVisible(false);

        JLabel labelFour = new JLabel("This is fact four");
        labelFour.setVisible(false);

        JLabel labelFive = new JLabel("This is fact five");
        labelFive.setVisible(false);

        JButton buttonOne = new JButton("Click me for a historical fact!");
        newPanel.add(buttonOne);

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPanel.removeAll();
                newPanel.add(buttonOne);
                Random randNumber = new Random();
                int userClick = randNumber.nextInt(5);
                switch(userClick){
                    case 1:
                        newPanel.add(labelOne);
                        labelOne.setVisible(true);
                        break;
                    case 2:
                        newPanel.add(labelTwo);
                        labelTwo.setVisible(true);
                        break;
                    case 3:
                        newPanel.add(labelThree);
                        labelThree.setVisible(true);
                        break;
                    case 4:
                        newPanel.add(labelFour);
                        labelFour.setVisible(true);
                        break;
                    case 5:
                        newPanel.add(labelFive);
                        labelFive.setVisible(true);
                        break;
                }
                newPanel.revalidate();
                newPanel.repaint();
            }
        });
    }

}
