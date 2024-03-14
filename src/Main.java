import jdk.internal.icu.text.UnicodeSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    private void initRegister() {

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

        JTextField txtUser = new JTextField("Email");
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("email_icon.png")));
        register.add(txtUser, "w 60%");
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
