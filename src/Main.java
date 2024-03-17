import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements ActionListener, KeyListener {

    JPanel panel;
    JFrame frame;
    JLabel label, userLabel, userTextField, passwordLabel, success, userPasswordText, userTextlabel;
    JTextField textField;
    JPasswordField passwordText;
    JButton button;
    Color transparentColour = new Color(255,255,255,0);

    public Main(){
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());

        panel.setLayout(null);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        panel.setBackground(transparentColour);

        userLabel = new JLabel("Email");
        userLabel.setBounds(650, 300, 80, 25);

        textField = new JTextField();
        textField.setBounds(650,325,250,40);
        textField.addKeyListener(this);


        userTextField = new JLabel("Enter your email here:");
        userTextField.setForeground(Color.GRAY);
        userTextField.setBounds(675, 325, 200, 40);
        userTextField.setVisible(true);

<<<<<<< HEAD

        userTextlabel = new Asset().generateImage("email_icon.png");
        userTextlabel.setBounds(660,325,200,40);
        userTextlabel.setVisible(true);

        passwordLabel = new JLabel("Password");
        passwordLabel = new JLabel("Password");
=======
        JLabel passwordLabel = new JLabel("Password");

        passwordLabel = new JLabel("Password");

>>>>>>> c6d2a71b75e0746f4eeba638def7292be6b91160
        passwordLabel.setBounds(650, 370, 80, 25);

        passwordText = new JPasswordField();
        passwordText.setBounds(650, 395, 250, 40);

        userPasswordText = new JLabel("Enter your password here:");
        userPasswordText.setForeground(Color.GRAY);
        userPasswordText.setBounds(660, 395,200,40);
        userPasswordText.setVisible(true);

        button = new JButton("Login");
        button.setBounds(650, 465, 250, 40);
        button.addActionListener(this);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);

        panel.add(userTextField);
        panel.add(userLabel);
        panel.add(textField);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(button);
        panel.add(success);

        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);
    }

    public static void main(String[] args){

        new Main();


    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!userPasswordText.getText().isEmpty()) {
            userPasswordText.setVisible(false);
        } else if (userPasswordText.getText().isEmpty()) {
            userPasswordText.setVisible(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!textField.getText().isEmpty()) {
            userTextField.setVisible(false);
        } else if (textField.getText().isEmpty()) {
            userTextField.setVisible(true);
        }


    }
}
