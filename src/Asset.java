import javax.swing.*;
import java.awt.*;

// This class is used to manipulate assets (like images, rounded text box, etc.).
public class Asset {
    
    // File path of the image is declared.
    private final static String filePath = "asset/";

    // This method aims to generate a JLabel that consists of the image at the original size.
    public JLabel generateImage(String pictureName) {

        // Retrieve the image from directory.
        ImageIcon image = new ImageIcon(filePath + pictureName);

        // Declare a JLabel and insert the picture into the JLabel at its original size.
        JLabel imagePlaceholder = new JLabel();
        imagePlaceholder.setSize(image.getIconWidth(), image.getIconHeight());
        imagePlaceholder.setIcon(image);

        // Return the image as a JLabel
        return imagePlaceholder;

    }

    // This method aims to generate a JPanel that consists of a rounded rectangle border.
    public JPanel generateRoundedRectangle(int width, int height, int roundedCorner, int borderThickness) {

        // Declaring a new JPanel that is created from the DrawRoundedRectangle class
        JPanel panel = new DrawRoundedRectangle(width, height, roundedCorner, borderThickness);

        // Set the position of the panel based on the inputted width and height
        panel.setSize(width, height);

        // Return result as a panel
        return panel;
    }
}

// This class is used to draw the rounded rectangle to be inserted into Hava
class DrawRoundedRectangle extends JPanel {

    // Initializes the data of the DrawRoundedRectangle.class
    int width, height, roundedCorner, borderThickness;

    // Constructor to include the inputted strings as one of the data in daily lives.
    DrawRoundedRectangle(int width, int height, int roundedCorner, int borderThickness) {
        this.width = width;
        this.height = height;
        this.roundedCorner = roundedCorner;
        this.borderThickness = borderThickness;
    }

    // A method used to draw all sorts of paintings
    @Override
    public void paintComponent(Graphics g) {

        // Using Graphics2D for better picture quality
        Graphics2D g2d = (Graphics2D) g;

        // Set the colour of the text, but how?
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, width - 10, height - 10, roundedCorner, roundedCorner);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawRoundRect(5, 5, width - 10, height - 10, roundedCorner, roundedCorner);
    }
}

