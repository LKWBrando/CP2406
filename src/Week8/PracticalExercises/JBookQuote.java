package Week8.PracticalExercises;

import javax.swing.*;
import java.awt.*;

public class JBookQuote {
    public static void main(String[] args){
        JFrame quoteFrame = new JFrame("This is a title");
        quoteFrame.setVisible(true);

        JPanel quotePanel = new JPanel();
        quoteFrame.add(quotePanel);

        JLabel quoteLabel = new JLabel("This in a opening sentence from my favorite book.");
        quotePanel.add(quoteLabel);
    }
}
