import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

public class Main implements ActionListener, KeyListener {

    JPanel panel, backgroundPanel, linePanel;
    JFrame frame;
    JLabel label, technicianLabel,mainTitle;
    JLabel userLabel;
    JLabel userTextField;
    JLabel passwordLabel;
    JLabel success;
    JLabel userPasswordText;
    JLabel userTextlabel;
    JTextField textField;
    JPasswordField passwordText;
    JButton button;
    Color transparentColour = new Color(255,255,255,0);

    public Main(){
        panel = new JPanel();
        frame = new JFrame("Login Page");
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);

        label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());


        panel.setLayout(null);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        panel.setBackground(transparentColour);

        linePanel = new Asset().drawLine(backgroundPanel.getWidth()/5*2,0,backgroundPanel.getWidth()/5*2,backgroundPanel.getHeight(),3);

        technicianLabel = new Asset().generateImage("technician_Picture.jpg");
        technicianLabel.setBounds(3,3,linePanel.getX() - 3,linePanel.getHeight() - 6);

        mainTitle = new JLabel("<html>AHHASC<br>Login Page</html>");
        mainTitle.setFont(Asset.getTitleFont());
        mainTitle.setBounds(600,50,400,300);

        userLabel = new JLabel("Email");
        userLabel.setBounds(650, 290, 450, 70);
        userLabel.setFont(Asset.getNameFont("Bold"));

        textField = new Asset().generateTextField();
        textField.setBounds(650,350,450,60);
        textField.addKeyListener(this);

        userTextField = new JLabel("Input email here:");
        userTextField.setForeground(Color.GRAY);
        userTextField.setBounds(680, 360, 200, 40);
        userTextField.setVisible(true);
        userTextField.setFont(new Font("Arial",Font.PLAIN,20));

        userTextlabel = new Asset().generateImage("email_icon.png");
        userTextlabel.setBounds(660,350,10,40);
        userTextlabel.setVisible(true);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(650, 430, 200, 25);
        passwordLabel.setFont(Asset.getNameFont("Bold"));

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Arial",Font.PLAIN,30));
        passwordText.setBounds(650, 470, 450, 60);

        userPasswordText = new JLabel("Enter your password here:");
        userPasswordText.setForeground(Color.GRAY);
        userPasswordText.setBounds(670, 470,200,40);
        userPasswordText.setVisible(true);
        userPasswordText.setFont(new Font("Arial",Font.PLAIN,20));

        button = new JButton("Login");
        button.setBounds(650, 600, 450, 70);
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);

        panel.add(mainTitle);
        panel.add(userTextlabel);
        panel.add(userTextField);
        panel.add(userLabel);
        panel.add(textField);
        panel.add(userPasswordText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(button);
        panel.add(success);
        backgroundPanel.add(technicianLabel);
        backgroundPanel.add(linePanel);
        panel.add(backgroundPanel);




        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);
    }



    public static void main(String[] args){

        Main main = new Main();


    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!textField.getText().isEmpty()) {
            userTextField.setVisible(false);
        } else if (textField.getText().isEmpty()) {
            userTextField.setVisible(true);
        }

        if(!userPasswordText.getText().isEmpty()) {
            userPasswordText.setVisible(false);
        } else if (userPasswordText.getText().isEmpty()) {
            userPasswordText.setVisible(true);
        }

    }
}
