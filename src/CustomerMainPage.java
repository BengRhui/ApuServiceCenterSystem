import javax.swing.*;
import java.awt.*;

public class CustomerMainPage {
    JFrame frame;
    JLabel label, mainTitle;
    JPanel panel, backgroundPanel;

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
        mainTitle.setBounds(80,5,5000,300);

        label = new Asset().generateImage("background_1.jpg");
        label.setBounds(0,0,frame.getWidth(),frame.getHeight());

        panel.add(mainTitle);
        panel.add(backgroundPanel);


        frame.add(panel);
        frame.add(label);
        frame.setVisible(true);



    }
}

