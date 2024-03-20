import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InitialMainPage implements KeyListener, ComponentListener {
    JFrame frame;
    JLabel label, mainTitle, mainContent, subContent, subContent1, userTextField, repairImage, providerImage, providerText, callImage, callText, searchImage,
            personnelImage, personnelText, locationImage, locationText, locationText1;

    JPanel panel, backgroundPanel;
    JTextField textField;
    JButton button;
    JLayeredPane LayeredPane;

    public InitialMainPage(){

        frame = new JFrame("AHHASC Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        panel.setBackground(Asset.getTransparentColour());

        mainTitle = new JLabel("APU Hostel Home Appliances Service Center");
        mainTitle.setFont(new Font("Arial",Font.BOLD,40));
        mainTitle.setBounds(135,5,5000,300);

        mainContent = new JLabel("Check your appointment here:");
        mainContent.setFont(new Font("Arial",Font.PLAIN, 25));
        mainContent.setBounds(135,55,500,450);

        subContent = new JLabel("If you previously had one with us.");
        subContent.setFont(new Font("Arial",Font.ITALIC,20));
        subContent.setBounds(135,90,500,485);

        subContent1 = new JLabel("<html>May it be booking appointments or answering inquires, do reach out to <br>us if you require any assistance!<html>");
        subContent1.setFont(new Font("Arial",Font.BOLD,25));
        subContent1.setBounds(135,180,5000,570);

        userTextField = new JLabel("Enter TP Number");
        userTextField.setForeground(Color.GRAY);
        userTextField.setBounds(680, 280, 200, 60);
        userTextField.setVisible(true);
        userTextField.setFont(new Font("Arial",Font.PLAIN,20));

        textField = new Asset().generateTextField();
        textField.setBounds(600,280,350,60);
        textField.addKeyListener(this);


        repairImage = new Asset().generateImage("repair_vector123.png");
        repairImage.setBounds(720,430,400,400);

        providerImage = new Asset().generateImage("providedService_icon123.jpg");
        providerImage.setBounds(135,525,55,55);

        providerText = new JLabel("<html><u>We can fix:</u><br><ul><li>Electric kettle</li><li>Iron/ Steamer</li><li>Bedside lamp</li><li>Hair Dryer</li></ul>and many more!<html>");
        providerText.setFont(new Font("Arial", Font.PLAIN,18));
        providerText.setBounds(200,480,200,200);


        callImage = new Asset().generateImage("call_icon.png");
        callImage.setBounds(390,520,60,60);

        callText = new JLabel("<html><u>Person-in-charge</u><br>Ms I Wee (012-345 6789)<br>Mr Teong (018-765 4321)</html>");
        callText.setFont(new Font("Arial", Font.PLAIN, 18));
        callText.setBounds(460, 340, 500, 400);

        locationImage = new Asset ().generateImage("location_icon.png");
        locationImage.setBounds(390,630,55,55);

        locationText = new JLabel("<html><u>Location</u><html>");
        locationText.setFont(new Font("Arial",Font.PLAIN,18));
        locationText.setBounds(460,400,500,500);

        locationText1 = new JLabel("Accommodation Office,");
        locationText1.setFont(new Font("Arial",Font.PLAIN,18));
        locationText1.setBounds(460,430,500,500);

        personnelText = new JLabel("Personnel of AHHASC? Click here to login to the system.");
        personnelText.setFont(new Font("Arial", Font.ITALIC,12));
        personnelText.setBounds(170,580,500,400);

        personnelImage = new Asset().generateImage("personnel_icon.png");
        personnelImage.setBounds(115,750,40,40);

        button = new JButton("");
        button.setBounds(0,0,70,70);
        button.setBackground(Color.black);
        searchImage = new Asset().generateImage("search_icon.png");
        searchImage.setBounds(0,0,60,60);

        LayeredPane = new JLayeredPane();
        LayeredPane.setBounds(970,275,70,70);
        LayeredPane.add(button, JLayeredPane.DEFAULT_LAYER);
        LayeredPane.add(searchImage, JLayeredPane.PALETTE_LAYER);
        LayeredPane.addKeyListener(this);

        label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());

        panel.add(mainTitle);
        panel.add(mainContent);
        panel.add(subContent);
        panel.add(subContent1);
        panel.add(userTextField);
        panel.add(textField);
        panel.add(repairImage);
        panel.add(providerImage);
        panel.add(providerText);
        panel.add(callImage);
        panel.add(callText);
        panel.add(locationImage);
        panel.add(locationText);
        panel.add(locationText1);
        panel.add(personnelImage);
        panel.add(personnelText);
        panel.add(backgroundPanel);
        panel.add(LayeredPane);


        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        
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

