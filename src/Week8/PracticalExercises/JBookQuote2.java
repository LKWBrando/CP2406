package Week8.PracticalExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JBookQuote2 {
    public static void main(String[] args){
        JFrame quoteFrame = new JFrame("This is a title");
        quoteFrame.setVisible(true);

        JPanel quotePanel = new JPanel();
        quoteFrame.add(quotePanel);

        JLabel quoteLabel = new JLabel("This in a opening sentence from my favorite book.");
        quotePanel.add(quoteLabel);
        quoteLabel.setVisible(true);

        JLabel bookLabel = new JLabel("The book title is: Hello World");
        quotePanel.add(bookLabel);
        bookLabel.setVisible(false);

        JButton quoteButton = new JButton("Click me!");
        quotePanel.add(quoteButton);

        quoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookLabel.setVisible(true);
            }
        });
    }
}
