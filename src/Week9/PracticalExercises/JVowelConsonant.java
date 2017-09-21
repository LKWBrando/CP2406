package Week9.PracticalExercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class JVowelConsonant extends JFrame implements ActionListener  {
    private JPanel p1,p2;
    private JLabel l1;
    String[] letterArray = {"A","B","C","D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ArrayList<JButton> letterAList= new ArrayList <JButton>();

    public JVowelConsonant(){
        setTitle("Vowels and Consonants");
        setSize(500,500);
        setLayout(new GridLayout(2,2));
        p1 = new JPanel(new GridLayout(2,2));
        p2 = new JPanel(new GridLayout(2,2));
        l1 = new JLabel();

        add(p1);
        add(p2);
        add(l1);

        for(int i = 0; i < letterArray.length; i++){
            JButton nextButton = new JButton(letterArray[i]);
            nextButton.addActionListener(this);
            letterAList.add(nextButton);
        }
        Collections.shuffle(letterAList);

        for (int i = 0; i<4; i++){
            p1.add(letterAList.remove(0));
        }
        for (int i = 0; i<4; i++){
            p2.add(letterAList.remove(0));
        }

    }

    public void actionPerformed (ActionEvent e){
        JButton clickedButton = ((JButton) e.getSource());
        if(clickedButton.getText().equals("A") || clickedButton.getText().equals("E") || clickedButton.getText().equals("I") ||clickedButton.getText().equals("O") || clickedButton.getText().equals("U")){
            l1.setText("You have selected a Vowel!");
        }
        else{
            l1.setText("You have selected a Consonant!");
        }

        JPanel selectedPanel = (JPanel) clickedButton.getParent();
        letterAList.add(clickedButton);
        selectedPanel.remove(clickedButton);
        selectedPanel.add(letterAList.remove(0));

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        JVowelConsonant demo1 = new JVowelConsonant();
        demo1.setVisible(true);
        demo1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

