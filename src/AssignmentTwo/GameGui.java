package AssignmentTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGui extends JFrame implements ActionListener{
    private JPanel header, body, chooseTrump;
    private JLabel footer;

    public GameGui(){
        setTitle("SuperTrump Card Game");
        setSize(1980,782);
        setLayout(new GridLayout(3,1));
        header = new JPanel(new GridLayout(1,2));
        body = new JPanel();
        chooseTrump = new JPanel(new GridLayout(1, 5));
        footer = new JLabel();

        add(header);
        add(body);
        add(footer);
    }

    public void actionPerformed(ActionEvent e){}
}
