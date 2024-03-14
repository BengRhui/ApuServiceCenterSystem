import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static jdk.internal.org.jline.utils.InfoCmp.Capability.buttons;

public class Main implements ActionListener {
    


    public static void main(String[] args) {

        ImageIcon emailIcon = new ImageIcon("email_icon.png");
        ImageIcon passwordIcon = new ImageIcon("password_icon.png");
        

        JPanel Panel = new JPanel();
        Panel.setBackground(Color.blue);
        Panel.setBounds(0,0,500,900);

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Panel);
        frame.add(panel);


        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email");
        userLabel.setBounds(650, 300, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(650, 325, 250, 40);
        panel.add(userText);
        userText.setIcon(emailIcon);
//        Border rotatedBorder = (Border) new RotatedLineBorder(Color.BLACK, 15);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(650, 370, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(650, 395, 250, 40);
        panel.add(passwordText);

        JButton button = new JButton("Login");
        button.setBounds(650, 465, 250, 40);
        button.addActionListener(new Main());
        panel.add(button);

        JLabel success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);



        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
