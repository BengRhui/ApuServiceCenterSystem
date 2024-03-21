import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

// This class is used to manipulate assets (like images, rounded text box, etc.).
public class Asset implements MouseListener {
    
    // File path of the image is declared.
    private final static String filePath = "asset/";

    // Size of the frame is declared.
    private final static int frameWidth = 1250;
    private final static int frameHeight = 900;

    // Position of the frame is declared.
    private static int framePositionX;
    private static int framePositionY;

    // Declare font for the title and body text
    private static Font bodyFont;
    private static Font nameFont;
    private final static Font titleFont = new Font("Arial", Font.BOLD, 60);

    private final static Color transparentColour = new Color(255, 255, 255, 0);
    private final static Color lightBlue = new Color(104, 208, 255);
    private final static Color lighterBlue = new Color(208, 240, 255);

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

    private JLayeredPane button;
    private JPanel buttonFront;

    // Setters to set the coordinates of the frame
    public static void setFramePosition(double coordinateX, double coordinateY){
        framePositionX = (int) coordinateX;
        framePositionY = (int) coordinateY;
    }

    // Getters to retrieve the font
    public static Font getBodyFont(String type) {
        switch (type) {
            case "Plain":
                bodyFont = new Font("Arial", Font.PLAIN, 20);
                break;
            case "Bold":
                bodyFont = new Font("Arial", Font.BOLD, 20);
                break;
            case "Italic":
                bodyFont = new Font("Arial", Font.ITALIC, 20);
                break;
            default:
                System.out.println("Invalid specifier for getBodyFont (Only Plain, Bold and Italic)!");
        }
        return bodyFont;
    }

    public static Font getNameFont(String type) {
        switch (type) {
            case "Plain":
                nameFont = new Font("Arial", Font.PLAIN, 25);
                break;
            case "Italic":
                nameFont = new Font("Arial", Font.ITALIC, 25);
                break;
            case "Bold":
                nameFont = new Font("Arial", Font.BOLD, 25);
                break;
            default:
                System.out.println("Invalid specifier for getNameFont (only Plain, Italic and Bold)!");
        }
        return nameFont;
    }

    public static Font getTitleFont() {
        return titleFont;
    }

    // Getters to retrieve different colours
    public static Color getTransparentColour() {
        return transparentColour;
    }

    public static Color getLightBlue() {
        return lightBlue;
    }

    public static Color getLighterBlue() {
        return lighterBlue;
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

    // This method aims to generate a JPanel that consists of a rounded rectangle filled with a colour.
    public JPanel generateFillRoundedRectangle(int width, int height, int roundedCorner, int borderThickness, Color color) {

        // Declaring a new JPanel that is created from the DrawRoundedRectangle class
        JPanel panel = new DrawFillRoundedRectangle(width, height, roundedCorner, borderThickness, color);

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

    // This method draws a line
    public JPanel drawLine(int startX, int startY, int endX, int endY, int strokeThickness) {

        // Calculate the width and height of the line
        int width = Math.abs(endX - startX), height = Math.abs(endY - startY);

        // Draw the line (which creates a panel) based on the width and height specified
        Line panel = new Line(width, height, strokeThickness);

        // Make the line visible if the line is vertical or horizontal
        if (width == 0) {
            width = strokeThickness;
        } else if (height == 0) {
            height = strokeThickness;
        }

        // Draw the line onto the frame
        panel.setLocation(Math.min(startX, endX), Math.min(startY, endY));
        panel.setSize(width, height);
        return panel;
    }

    // Generates a JLayeredPane (which acts like a button) where black shadows will appear
    public JLayeredPane generateButtonWithoutImage(String text, int width, int height) {

        button = new JLayeredPane();
        button.setSize(width + 3, height + 3);
        button.addMouseListener(this);

        buttonFront = new JPanel(null);
        buttonFront.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        buttonFront.setBounds(0, 3, width, height);
        buttonFront.setBackground(Color.WHITE);

        JLabel buttonText = new JLabel(text, JLabel.CENTER);
        buttonText.setBounds(0, 0, width, height);
        buttonText.setFont(Asset.getBodyFont("Bold"));
        buttonText.setVerticalTextPosition(JLabel.CENTER);

        buttonFront.add(buttonText);

        JPanel background = new JPanel();
        background.setBounds(0, 3, width, height);
        background.setBackground(Color.BLACK);
        background.setLocation(buttonFront.getX(), buttonFront.getY());

        button.add(buttonFront, JLayeredPane.PALETTE_LAYER);
        button.add(background, JLayeredPane.DEFAULT_LAYER);

        return button;
    }

    public JLayeredPane generateButtonWithImageTop(String text, String imagePath, int width, int height) {
        try {
            if (width < 30 || height < 30) {
                throw new UnsupportedOperationException();
            }
        } catch (UnsupportedOperationException ex) {
            System.out.println("Error at generating button with image. Width and height must be greater than 30.");
            System.exit(0);
        }

        button = new JLayeredPane();
        button.setSize(width + 3, height + 3);
        button.addMouseListener(this);

        buttonFront = new JPanel(null);
        buttonFront.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        buttonFront.setBounds(0, 3, width, height);
        buttonFront.setBackground(Color.WHITE);

        JLabel imagePlaceholder = new Asset().generateImage(imagePath);
        imagePlaceholder.setBounds(25, 30, width - 50, buttonFront.getHeight() * 3 / 4 - 20);

        JLabel buttonText = new JLabel(text, JLabel.CENTER);
        buttonText.setBounds(0, imagePlaceholder.getHeight() + 15, width, buttonFront.getHeight() - imagePlaceholder.getHeight());
        buttonText.setFont(Asset.getBodyFont("Bold"));
        buttonText.setVerticalTextPosition(JLabel.CENTER);

        buttonFront.add(imagePlaceholder);
        buttonFront.add(buttonText);

        JPanel background = new JPanel();
        background.setBounds(0, 3, width, height);
        background.setBackground(Color.BLACK);
        background.setLocation(buttonFront.getX(), buttonFront.getY());

        button.add(buttonFront, JLayeredPane.PALETTE_LAYER);
        button.add(background, JLayeredPane.DEFAULT_LAYER);

        return button;
    }

    public JLayeredPane generateButtonWithImageLeft(String text, String imagePath, int width, int height) {
        button = new JLayeredPane();
        button.setSize(width + 3, height + 3);
        button.addMouseListener(this);

        buttonFront = new JPanel();
        buttonFront.setLayout(null);
        buttonFront.setBounds(0, 3, width, height);
        buttonFront.setBackground(Color.WHITE);
        buttonFront.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        JLabel imagePlaceholder = new Asset().generateImage(imagePath);
        imagePlaceholder.setBounds(15, 15, height - 30, height - 30);

        JLabel textPlaceholder = new JLabel(text);
        textPlaceholder.setSize(buttonFront.getWidth() - imagePlaceholder.getWidth() - 30, buttonFront.getHeight());
        textPlaceholder.setLocation( imagePlaceholder.getWidth() + 45, (buttonFront.getHeight() - textPlaceholder.getHeight()) / 2);
        textPlaceholder.setFont(Asset.getNameFont("Bold"));

        buttonFront.add(imagePlaceholder);
        buttonFront.add(textPlaceholder);

        JPanel background = new JPanel();
        background.setBounds(0, 3, width, height);
        background.setBackground(Color.BLACK);

        button.add(buttonFront, JLayeredPane.PALETTE_LAYER);
        button.add(background, JLayeredPane.DEFAULT_LAYER);
        return button;
    }

    public JTextField generateTextField() {
        JTextField field = new JTextField();
        field.setFont(Asset.getBodyFont("Plain"));
        field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 15, 5, 5)));
        return field;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        buttonFront.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonFront.setLocation(buttonFront.getX() + 3, buttonFront.getY() - 3);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        buttonFront.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        buttonFront.setLocation(buttonFront.getX() - 3, buttonFront.getY() + 3);
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

        // Set the colour of the rounded rectangle
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, width - 10, height - 10, roundedCorner, roundedCorner);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.drawRoundRect(5, 5, width - 10, height - 10, roundedCorner, roundedCorner);
    }
}

// This class is used to draw the rounded rectangle filled with a colour
class DrawFillRoundedRectangle extends JPanel {

    // Initializes the data of the DrawRoundedRectangle.class
    int width, height, roundedCorner, borderThickness;
    Color color;

    // Constructor to include the inputted strings as one of the data from the objects.
    DrawFillRoundedRectangle(int width, int height, int roundedCorner, int borderThickness, Color color) {
        this.width = width;
        this.height = height;
        this.roundedCorner = roundedCorner;
        this.borderThickness = borderThickness;
        this.color = color;
    }

    // A method used to draw all sorts of paintings
    @Override
    public void paintComponent(Graphics g) {

        // Using Graphics2D for better picture quality
        Graphics2D g2d = (Graphics2D) g;

        // Set the colour of the rounded rectangle
        g2d.setColor(color);
        g2d.fillRoundRect(5, 5, width - 10, height - 10, roundedCorner, roundedCorner);
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
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval(0, 0, radius * 2, radius * 2);
    }

}

// This class is used to draw a line
class Line extends JPanel {

    // Declare variables for the starting and ending point
    int width, height, stroke;

    // Constructor to assign values to the declared variables
    Line(int width, int height, int stroke) {
        this.width = width;
        this.height = height;
        this.stroke = stroke;
    }

    // Paint method to draw lines based on the values in the declared variables
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawLine(0, 0, width, height);
    }
}