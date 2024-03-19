import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CustomerMainPage {
    JFrame frame;
    JLabel label, mainTitle, mainContent, subContent, subContent1, userTextField, repairImage, providerImage, callImage, personnelImage, personnelText;
    JPanel panel, backgroundPanel;
    JTextField textField;

    Color transparentColour = new Color(255,255,255,0);


    public static void main(String[] args){new CustomerMainPage();}

    public CustomerMainPage(){
        panel = new JPanel();
        frame = new JFrame("CustomerMainPage");
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);

        panel.setLayout(null);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        panel.setBackground(transparentColour);

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

        repairImage = new Asset().generateImage("repair_vector123.png");
        repairImage.setBounds(720,430,400,400);

        providerImage = new Asset().generateImage("providedService_icon123.jpg");
        providerImage.setBounds(135,525,55,55);

        callImage = new Asset().generateImage("call_icon.png");
        callImage.setBounds(390,520,60,60);

        personnelText = new JLabel("Personnel of AHHASC? Click here to login to the system.");
        personnelText.setFont(new Font("Arial", Font.ITALIC,20));
        personnelText.setBounds(135,750,400,400);

        personnelImage = new Asset().generateImage("personnel_icon.png");
        personnelImage.setBounds(115,750,40,40);



//        textField.addKeyListener(this);




        label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());



        panel.add(mainTitle);
        panel.add(mainContent);
        panel.add(subContent);
        panel.add(subContent1);
        panel.add(personnelText);
        panel.add(userTextField);
        panel.add(textField);
        panel.add(repairImage);
        panel.add(providerImage);
        panel.add(callImage);
        panel.add(personnelImage);
        panel.add(backgroundPanel);


        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);



    }
}


