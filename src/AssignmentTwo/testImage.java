package AssignmentTwo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class testImage {
    public static void main(String[] args) throws IOException {

        ArrayList<Card> cardDeck = new ArrayList<Card>();
        ArrayList<String> imagePathString = new ArrayList<String>();

        File[] imageFiles = new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images").listFiles();
        for (File fileName : imageFiles) {
            imagePathString.add(fileName.getName());
        }
        FileReader readFile = new FileReader(".\\card.txt");
        BufferedReader newFileLine = new BufferedReader(readFile);
        String nextLine;
        while ((nextLine = newFileLine.readLine()) != null) {
            String[] splitData = nextLine.split(",");
            try {
                MineralCard newCard = new MineralCard(splitData[0], Double.parseDouble(splitData[1]), Double.parseDouble(splitData[2]), splitData[3], splitData[4], splitData[5]);
                cardDeck.add(newCard);
            } catch (NumberFormatException error) {
            }
        }
        readFile.close();
        System.out.println(imagePathString);

        Map<Card, String> cardMap = new HashMap<Card, String>();

        for (int i = 0; i < cardDeck.size(); i++) {
            cardMap.put(cardDeck.get(i), imagePathString.get(i));
        }

        BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(0)));
        ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(200,330, Image.SCALE_SMOOTH));
        JButton newButton = new JButton(newIcon);
        newButton.setPreferredSize(new Dimension(200,330));
        JFrame newFrame = new JFrame("Test");

        newFrame.setSize(1920, 768);
        newFrame.setVisible(true);
        newFrame.add(newButton);
    }
}
