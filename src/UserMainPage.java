import javax.swing.*;
import java.awt.*;

public class UserMainPage {

    static JFrame frame;
    JPanel leftPanel, rightPanel;

    public static void main(String[] args) {
        new UserMainPage();
    }

    public UserMainPage() {
        frame = new JFrame("Manager Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600, frame.getHeight()));
        leftPanel.setLayout(new BorderLayout());



        frame.setVisible(true);
    }

}
