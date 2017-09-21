package Week9.LectureExercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ex1Class extends JFrame implements ActionListener{
    private JPanel p1,p2,mp,p3;
    private JButton buttonLogin, buttonForget, buttonQuit;
    private JLabel labelId, labelPass, labelStatus;
    private JTextField loginTF, passTF;

    public ex1Class() {
        setTitle("title");
        setSize(500, 300);
        mp = new JPanel(new GridLayout(3, 1));
        setContentPane(mp);

        //Secondary Panels
        p1 = new JPanel(new GridLayout(2, 2));
        p2 = new JPanel(new GridLayout(1, 3));
        p3 = new JPanel();

        mp.add(p1);
        mp.add(p2);
        mp.add(p3);

        labelId = new JLabel("ID:");
        labelPass = new JLabel("Password:");
        loginTF = new JTextField(20);
        passTF = new JTextField(20);
        buttonLogin = new JButton("Login");
        buttonForget = new JButton("Forget");
        buttonQuit = new JButton("Quit");

        buttonLogin.addActionListener(this);
        buttonForget.addActionListener(this);
        buttonQuit.addActionListener(this);

        p1.add(labelId);
        p1.add(loginTF);
        p1.add(labelPass);
        p1.add(passTF);

        p2.add(buttonLogin);
        p2.add(buttonForget);
        p2.add(buttonQuit);

        //p3.add(labelStatus);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==buttonLogin){
            labelStatus.setText("HI");
        }else if(e.getSource()==buttonForget){

        }else if(e.getSource()==buttonQuit){

        }
    }
}
