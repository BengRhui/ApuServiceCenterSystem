import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class LoginPage implements ActionListener, KeyListener, ComponentListener {

    JPanel backgroundPanel, linePanel, emailTextFrame, passwordTextFrame;
    JFrame frame;
    JLabel backgroundImage, technicianLabel,mainTitle, otherS, passwordImageLabel;
    JLabel emailLabel, emailPromptText, passwordLabel, passwordPrompt, emailIcon, returnTextTop, returnTextBottom;
    JTextField emailTextField;
    JPasswordField passwordText;
    JButton loginButton;
    JLayeredPane passwordLayer, emailLayer;

    public LoginPage(){
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

        emailIcon = new Asset().generateImage("email_icon.png");

        emailTextFrame = new Asset().generateRoundedRectangle(550, 80, 10, 2);
        emailTextFrame.setLocation(0, 0);

        emailPromptText = new JLabel("Input email here");
        emailPromptText.setForeground(Color.GRAY);
        emailPromptText.setSize(450, emailTextFrame.getHeight());
        emailPromptText.setVisible(true);
        emailPromptText.setFont(new Font("Arial",Font.PLAIN,20));

        emailTextField = new JTextField();
        emailTextField.setBorder(null);
        emailTextField.setBackground(Asset.getTransparentColour());
        emailTextField.setSize(450, emailTextFrame.getHeight());
        emailTextField.setFont(Asset.getBodyFont("Plain"));
        emailTextField.addKeyListener(this);

        emailLayer = new JLayeredPane();
        emailLayer.setSize(emailTextFrame.getWidth(),emailTextFrame.getHeight());
        emailLayer.add(emailTextField,JLayeredPane.MODAL_LAYER);
        emailLayer.add(emailIcon,JLayeredPane.PALETTE_LAYER);
        emailLayer.add(emailPromptText,JLayeredPane.PALETTE_LAYER);
        emailLayer.add(emailTextFrame, JLayeredPane.DEFAULT_LAYER);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(Asset.getNameFont("Bold"));

        passwordTextFrame = new Asset().generateRoundedRectangle(550, 80, 10, 2);
        passwordTextFrame.setLocation(0, 0);

        passwordImageLabel = new Asset().generateImage("password_icon.png");

        passwordPrompt = new JLabel("Enter password here");
        passwordPrompt.setForeground(Color.GRAY);
        passwordPrompt.setSize(450, passwordTextFrame.getHeight());
        passwordPrompt.setVisible(true);
        passwordPrompt.setFont(new Font("Arial",Font.PLAIN,20));

        passwordText = new JPasswordField();
        passwordText.setFont(Asset.getBodyFont("Plain"));
        passwordText.setSize(450, passwordTextFrame.getHeight());
        passwordText.setBackground(Asset.getTransparentColour());
        passwordText.setBorder(null);
        passwordText.addKeyListener(this);

        passwordLayer = new JLayeredPane();
        passwordLayer.setSize(passwordTextFrame.getWidth(),passwordTextFrame.getHeight());
        passwordLayer.add(passwordText, JLayeredPane.MODAL_LAYER);
        passwordLayer.add(passwordImageLabel, JLayeredPane.PALETTE_LAYER);
        passwordLayer.add(passwordPrompt, JLayeredPane.PALETTE_LAYER);
        passwordLayer.add(passwordTextFrame, JLayeredPane.DEFAULT_LAYER);

        loginButton = new JButton("Login");
        loginButton.setFont(Asset.getNameFont("Bold"));
        loginButton.setBorderPainted(false);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);


        loginButton.addActionListener(this);

        returnTextTop = new JLabel("Not a personnel of AHHASC?");
        returnTextTop.setFont(Asset.getBodyFont("Plain"));

        returnTextBottom = new JLabel("Click here to redirect to the home page.");
        returnTextBottom.setFont(Asset.getBodyFont("Plain"));

        backgroundPanel.add(mainTitle);
        backgroundPanel.add(emailLabel);
        backgroundPanel.add(emailLayer);
        backgroundPanel.add(passwordLabel);
        backgroundPanel.add(passwordLayer);
        backgroundPanel.add(loginButton);
        backgroundPanel.add(returnTextTop);
        backgroundPanel.add(returnTextBottom);
        backgroundPanel.add(technicianLabel);
        backgroundPanel.add(linePanel);

        frame.add(backgroundPanel);
        frame.add(backgroundImage);
        frame.setVisible(true);

    }





    public static void main(String[] args){
        new LoginPage();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String inputEmail = emailTextField.getText();

            StringBuilder password = new StringBuilder();
            for (char character: passwordText.getPassword()) {
                password.append(character);
            }
            String userPassword = password.toString();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!emailTextField.getText().isEmpty()){
            emailPromptText.setVisible(false);
        } else if (emailTextField.getText().isEmpty()) {
            emailPromptText.setVisible(true);
        }

        StringBuilder password = new StringBuilder();
        for (char character: passwordText.getPassword()) {
            password.append(character);
        }
        String userPassword = password.toString();

        passwordPrompt.setVisible(userPassword.isEmpty());

    }

    @Override
    public void componentResized(ComponentEvent e) {
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);
        backgroundImage.setBounds(0,0,frame.getWidth(),frame.getHeight());
        technicianLabel.setBounds(3,3,linePanel.getX() - 3,backgroundPanel.getHeight() - 6);
        mainTitle.setBounds(linePanel.getX() + 70,frame.getHeight() / 25,400,180);
        emailLabel.setBounds(mainTitle.getX(), frame.getHeight() * 2 / 9, mainTitle.getWidth(), 70);
        emailLayer.setLocation(emailLabel.getX(),emailLabel.getY() + emailLabel.getHeight());
        emailIcon.setLocation(30, (emailLayer.getHeight() - emailIcon.getHeight()) / 2);
        emailTextField.setLocation(emailIcon.getWidth() + emailIcon.getX() + 20, 0);
        emailPromptText.setLocation(emailTextField.getX(), emailTextField.getY());
        passwordLabel.setBounds(emailLabel.getX(), emailLayer.getY() + emailLayer.getHeight() + 10, emailLabel.getWidth(), emailLabel.getHeight());
        passwordImageLabel.setLocation(35,(passwordLayer.getHeight() - passwordImageLabel.getHeight()) / 2);
        passwordLayer.setLocation(passwordLabel.getX(),passwordLabel.getY() + passwordLabel.getHeight());
        passwordPrompt.setLocation(emailPromptText.getX(), 0);
        passwordText.setLocation(passwordPrompt.getX(), 0);
        loginButton.setBounds(passwordLayer.getX(), passwordLayer.getY() + passwordLabel.getHeight() + 50, passwordLayer.getWidth(), passwordLayer.getHeight());
        returnTextTop.setBounds(loginButton.getX(),loginButton.getY() + loginButton.getHeight() + 30,loginButton.getWidth(),30);
        returnTextBottom.setBounds(returnTextTop.getX(),returnTextTop.getY() + returnTextTop.getHeight(),returnTextTop.getWidth(),30);
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
