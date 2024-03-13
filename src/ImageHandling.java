import javax.swing.*;

// This class is used to manipulate images.
public class ImageHandling {

    // File path of the image is declared.
    private final static String filePath = "asset/";

    // This method aims to generate a JLabel that consists of the image at the original size.
    public static JLabel generateImage(String pictureName) {

        // Retrieve the image from directory.
        ImageIcon image = new ImageIcon(filePath + pictureName);

        // Declare a JLabel and insert the picture into the JLabel at its original size.
        JLabel imagePlaceholder = new JLabel();
        imagePlaceholder.setSize(image.getIconWidth(), image.getIconHeight());
        imagePlaceholder.setIcon(image);

        // Return the image as a JLabel
        return imagePlaceholder;

    }
}
