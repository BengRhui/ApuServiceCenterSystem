import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements ActionListener, KeyListener {

    public Main(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        Color transparentColour = new Color(255,255,255,0);
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());

        panel.setLayout(null);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        panel.setBackground(transparentColour);

        JLabel userLabel = new JLabel("Email");
        userLabel.setBounds(650, 300, 80, 25);
        panel.add(userLabel);

        JTextField textField = new JTextField();
        textField.setBounds(650,325,250,40);
        panel.add(textField);

        JLabel userTextField = new JLabel("Enter your email here:");
        userTextField.setForeground(Color.GRAY);
        userTextField.addKeyListener(this);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(650, 370, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(650, 395, 250, 40);
        panel.add(passwordText);

        JButton button = new JButton("Login");
        button.setBounds(650, 465, 250, 40);
        button.addActionListener(this);
        panel.add(button);

        JLabel success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
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
        if(textField.

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}