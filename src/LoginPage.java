import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.security.Key;

public class LoginPage implements ActionListener, KeyListener, ComponentListener, MouseListener, WindowListener {

    JPanel backgroundPanel, linePanel, emailTextFrame, passwordTextFrame;
    JFrame frame;
    JLabel backgroundImage, technicianLabel,mainTitle, passwordImageLabel, emailLabel, emailPromptText, passwordLabel,
            passwordPrompt, emailIcon, returnTextTop, returnTextBottom, exitButton;
    JTextField emailTextField;
    JPasswordField passwordText;
    JButton loginButton;
    JLayeredPane passwordLayer, emailLayer;

    public LoginPage(){
        frame = new JFrame("Login Page");
        frame.setSize(1250, 900);
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);

        backgroundImage = new Asset().generateImage("background_1.jpg");

        linePanel = new Asset().drawLine(backgroundPanel.getWidth() / 5 * 2,0,backgroundPanel.getWidth() / 5 * 2, 2000, 3);

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
        loginButton.addMouseListener(this);

        returnTextTop = new JLabel("Not a personnel of AHHASC?");
        returnTextTop.setFont(Asset.getBodyFont("Plain"));
        returnTextTop.addMouseListener(this);

        returnTextBottom = new JLabel("Click here to redirect to the home page.");
        returnTextBottom.setFont(Asset.getBodyFont("Plain"));
        returnTextBottom.addMouseListener(this);

        exitButton = new Asset().generateImage("close_icon.png");
        exitButton.addMouseListener(this);

        backgroundPanel.add(exitButton);
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
            for (char character : passwordText.getPassword()) {
                password.append(character);
            }
            String userPassword = password.toString();

            Manager currentManager = null;
            Technician currentTechnician = null;



            for (Manager manager : Manager.getOverallManagerList()) {
                if (manager.email.equals(inputEmail) && manager.password.equals(userPassword)) {
                    currentManager = manager;
                }
            }

            for (Technician technician : Technician.getOverallTechnicianList()) {
                if (technician.email.equals(inputEmail) && technician.password.equals(userPassword)) {
                    currentTechnician = technician;
                }
            }


            if (currentManager == null && currentTechnician == null) {
                JOptionPane.showMessageDialog(frame, "Invalid email and password. Please insert the correct credentials.", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "userNotFound_vector.png"));
            } else if (currentManager != null && currentTechnician != null) {
                JOptionPane.showMessageDialog(frame, "Error in registration. Please contact admin / manager to report this issue.", "Credential Error", JOptionPane.ERROR_MESSAGE);
            } else if (currentManager != null) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new ManagerMainPage(currentManager);
                frame.dispose();
            } else {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new TechnicianMainPage(currentTechnician);
                frame.dispose();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == emailTextField && e.getKeyCode() == KeyEvent.VK_ENTER) {
            passwordText.requestFocus();
            passwordText.setCaretPosition(0);
        } else if (e.getSource() == passwordText && e.getKeyCode() == KeyEvent.VK_ENTER) {
             loginButton.doClick();
        }
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
        emailLabel.setBounds(mainTitle.getX(), frame.getHeight() * 2 / 9 + 10, mainTitle.getWidth(), 70);
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
        exitButton.setLocation(backgroundPanel.getWidth() - exitButton.getWidth() - 50, 50);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == returnTextTop || e.getSource() == returnTextBottom) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            InitialMainPage.setFrameVisible(true);
            frame.dispose();
        } else if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to exit the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "exitConfirm_vector.png"));
            if (choice == 0) {
                frame.dispose();
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        returnTextTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnTextBottom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        returnTextTop.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        returnTextBottom.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == frame) {

            // Confirm that user wishes to exit, the end the overall system if the user intends to do so
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to exit the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "exitConfirm_vector.png"));
            if (choice == 0) {
                frame.dispose();
                System.exit(0);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
