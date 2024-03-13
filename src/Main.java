import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.buttons;

public class Main implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;


    public static void main(String[] args) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("Email");
        userLabel.setBounds(650, 300, 80, 25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(650, 325, 250, 40);
        panel.add(userText);
//        Border rotatedBorder = (Border) new RotatedLineBorder(Color.BLACK, 15);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(650, 370, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(650, 395, 250, 40);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(650, 465, 250, 40);
        button.addActionListener(new Main());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + " " + password);

        if(user.equals("Beh") && password.equals("04")) {
            success.setText("Login Successful!");

        }

    }
}
