import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1250,900);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email");
        userLabel.setBounds(650, 300, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(650,325,250,40);
        panel.add(userText);
        //Border rotatedBorder = (Border) new RotatedLineBorder(Color.BLACK, 15);

        JLabel passwordLabel = new JLabel ("Password");
        passwordLabel.setBounds(650,370,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(650, 395, 250, 40);
        panel.add(passwordText);

        frame.setVisible(true);
    }
}