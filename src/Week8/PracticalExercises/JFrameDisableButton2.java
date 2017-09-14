package Week8.PracticalExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameDisableButton2 {
    public static void main(String[] args) {

        JFrame frameOne = new JFrame();
        frameOne.setVisible(true);

        JPanel newPanel = new JPanel();
        frameOne.add(newPanel);

        JLabel labelOne = new JLabel("Thats enough!");
        newPanel.add(labelOne);
        labelOne.setVisible(false);

        JButton buttonOne = new JButton("Click me?");
        newPanel.add(buttonOne);

        buttonOne.addActionListener(new ActionListener() {
            int buttonCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonCount += 1;
                if (buttonCount == 8){
                newPanel.remove(buttonOne);
                labelOne.setVisible(true);
                }
            }
        });
    }
}
