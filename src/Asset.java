import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

// This class is used to manipulate assets (like images, rounded text box, etc.).
public class Asset {
    
    // File path of the image is declared.
    private final static String filePath = "asset/";

    // Size of the frame is declared.
    private final static int frameWidth = 1250;
    private final static int frameHeight = 900;

    // Position of the frame is declared.
    private static int framePositionX;
    private static int framePositionY;

    // Getters to obtain the width and height of the frame
    public static int getFrameHeight() {
        return frameHeight;
    }

    public static int getFrameWidth() {
        return frameWidth;
    }

    // Getters to obtain the position of the frame on the screen
    public static int getFramePositionX() {
        return framePositionX;
    }

    public static int getFramePositionY() {
        return framePositionY;
    }

    // Setters to set the coordinates of the frame
    public void setFramePositionX(double coordinateX, double coordinateY){
        framePositionX = (int) coordinateX;
        framePositionY = (int) coordinateY;
    }

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

    // This method generates a round profile picture for technicians and managers
    public JPanel generateRoundProfile(String filePath, int radius) {
        JPanel profilePic = new CircleProfilePicture(filePath, radius);
        profilePic.setSize(radius * 2, radius * 2);
        return profilePic;
    }
}

// This class is used to draw the rounded rectangle to be inserted into Java
class DrawRoundedRectangle extends JPanel {

    // Initializes the data of the DrawRoundedRectangle.class
    int width, height, roundedCorner, borderThickness;

    // Constructor to include the inputted strings as one of the data from the objects.
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

// This class is used to draw a circle-shaped profile picture.
class CircleProfilePicture extends JPanel {

    // Declare a file path that stores the profile pictures
    private final static String filePath = "asset/profilePicture/";

    // Declares the variables required
    int radius;
    ImageIcon picturePlaceholder;

    // Constructor to build a round profile picture
    CircleProfilePicture(String picturePath, int radius) {
        this.radius = radius;
        picturePlaceholder = new ImageIcon(filePath + picturePath);
    }

    // Paint component used to shape the picture into a circle
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setClip(new Ellipse2D.Double(0, 0, radius * 2, radius * 2));
        g2d.drawImage(picturePlaceholder.getImage(), 0, 0, null);
        g2d.drawOval(0, 0, radius * 2, radius * 2);
    }

}