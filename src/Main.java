import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class Main implements ActionListener, KeyListener, ComponentListener {

    JPanel backgroundPanel, linePanel;
    JFrame frame;
    JLabel backgroundImage, technicianLabel,mainTitle, otherS, passwordImageLabel;
    JLabel emailLabel, userTextField, passwordLabel, success, userPasswordText, userTextLabel;
    JTextField textField;
    JPasswordField passwordText;
    JButton button;
    JLayeredPane passwordLayered, userLayered;

    public Main(){
        frame = new JFrame("Login Page");
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);

        backgroundImage = new Asset().generateImage("background_1.jpg");

        linePanel = new Asset().drawLine(backgroundPanel.getWidth() / 5 * 2,0,backgroundPanel.getWidth() / 5 * 2, 2000,3);

        technicianLabel = new Asset().generateImage("technician_Picture.jpg");

        mainTitle = new JLabel("<html>AHHASC<br>Login Page</html>");
        mainTitle.setFont(Asset.getTitleFont());

        emailLabel = new JLabel("Email");
        emailLabel.setFont(Asset.getNameFont("Bold"));

        userTextLabel = new Asset().generateImage("email_icon123.png");
        userTextLabel.setBounds(10,15,60,60);
        userTextLabel.setVisible(true);



        textField = new JTextField();
        textField.setBounds(0,0,450,60);
//        textField.setBorder(null);
//        textField.setBackground(Asset.getTransparentColour());
        textField.addKeyListener(this);

        userTextField = new JLabel("Input email here:");
        userTextField.setForeground(Color.GRAY);
        userTextField.setBounds(55, 15, 200, 40);
        userTextField.setVisible(true);
        userTextField.setFont(new Font("Arial",Font.PLAIN,20));

//        user = new Asset().generateRoundedRectangle(450,70,15,1);
//        user.setLocation(0,0);


        userLayered = new JLayeredPane();
        userLayered.setBounds(650,350,450,450);
        userLayered.add(textField,JLayeredPane.DEFAULT_LAYER);
        userLayered.add(userTextLabel,JLayeredPane.PALETTE_LAYER);
        userLayered.add(userTextField,JLayeredPane.PALETTE_LAYER);
//        userLayered.add(user,JLayeredPane.DEFAULT_LAYER);

        passwordImageLabel = new Asset().generateImage("password_icon123.png");
        passwordImageLabel.setBounds(10,5,40,40);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(650, 430, 200, 25);
        passwordLabel.setFont(Asset.getNameFont("Bold"));

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Arial",Font.PLAIN,30));
        passwordText.setBounds(0, 0, 450, 60);
        passwordText.addKeyListener(this);

        userPasswordText = new JLabel("Enter your password here:");
        userPasswordText.setForeground(Color.GRAY);
        userPasswordText.setBounds(55, 9,400,45);
        userPasswordText.setVisible(true);
        userPasswordText.setFont(new Font("Arial",Font.PLAIN,20));



        passwordLayered = new JLayeredPane();
        passwordLayered.setBounds(650,470,450,450);
        passwordLayered.add(passwordText, JLayeredPane.DEFAULT_LAYER);
        passwordLayered.add(passwordImageLabel, JLayeredPane.PALETTE_LAYER);
        passwordLayered.add(userPasswordText, JLayeredPane.PALETTE_LAYER);


        otherS = new JLabel("<html>Not a personnel in AHHASC?<br> Click here to redirect to the home page<html>");
        otherS.setBounds(650,630,400,150);
        otherS.setFont(new Font("Arial",Font.PLAIN,20));

        button = new JButton("Login");
        button.setBounds(650, 600, 450, 70);
        button.setFont(Asset.getNameFont("Bold"));
        button.setBackground(Color.black);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);

        backgroundPanel.add(mainTitle);
        backgroundPanel.add(emailLabel);
        backgroundPanel.add(userLayered);
        backgroundPanel.add(passwordLabel);
        backgroundPanel.add(passwordLayered);
        backgroundPanel.add(button);
        backgroundPanel.add(success);
        backgroundPanel.add(otherS);
        backgroundPanel.add(technicianLabel);
        backgroundPanel.add(linePanel);

        frame.add(backgroundPanel);
        frame.add(backgroundImage);
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

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!textField.getText().isEmpty()){
            userTextField.setVisible(false);
        } else if (textField.getText().isEmpty()) {
            userTextField.setVisible(true);
        }

        if(!passwordText.getText().isEmpty()) {
            userPasswordText.setVisible(false);
        } else if (userPasswordText.getText().isEmpty()) {
            userPasswordText.setVisible(true);
        }

    }

    @Override
    public void componentResized(ComponentEvent e) {
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);
        backgroundImage.setBounds(0,0,frame.getWidth(),frame.getHeight());
        technicianLabel.setBounds(3,3,linePanel.getX() - 3,backgroundPanel.getHeight() - 6);
        mainTitle.setBounds(linePanel.getX() + 70,backgroundPanel.getHeight() / 20,400,200);
        emailLabel.setBounds(mainTitle.getX(), mainTitle.getY() + mainTitle.getHeight() + 20, mainTitle.getWidth(), 70);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
