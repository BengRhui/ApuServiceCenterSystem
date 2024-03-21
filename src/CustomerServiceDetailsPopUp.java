import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerServiceDetailsPopUp implements KeyListener, ComponentListener, WindowListener {
    JPanel panel, backgroundPanel;
    JFrame frame;
    JLabel mainTitle, mainContent1, mainContent2, mainContent3,mainContent4, mainContent5, mainContent6;
    JTextField textField1, textField2, textField3, textField4, textField5, textField6;
    JLayeredPane button;

    Color transparentColour = new Color(255,255,255,0);

    public static void main(String[] args){new CustomerServiceDetailsPopUp(null);}

    public CustomerServiceDetailsPopUp(Appointment appointment){
        panel = new JPanel();
        frame = new JFrame("CustomerServiceDetailsPopUp");
        frame.setSize(1100, 800);
        frame.setLocation(Asset.getFramePositionX() + (Asset.getFrameWidth() - frame.getWidth()) / 2, Asset.getFramePositionY() + (Asset.getFrameHeight() - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));


        mainTitle = new JLabel("Service Details");
        mainTitle.setFont(new Font("Arial",Font.BOLD,40));


        mainContent1 = new JLabel("Item to be Serviced");
        mainContent1.setFont(new Font("Arial",Font.PLAIN,25));


        mainContent2 = new JLabel("Appointment Date");
        mainContent2.setFont(new Font("Arial",Font.PLAIN,25));


        mainContent3 = new JLabel("Technician-In-charge");
        mainContent3.setFont(new Font("Arial",Font.PLAIN,25));


        mainContent4 = new JLabel("Payment");
        mainContent4.setFont(new Font("Arial",Font.PLAIN,25));


        mainContent5 = new JLabel("Starting Time");
        mainContent5.setFont(new Font("Arial", Font.PLAIN,25));


        mainContent6 = new JLabel("Ending Time");
        mainContent6.setFont(new Font("Arial",Font.PLAIN,25));


        textField1 = new Asset().generateTextField();

        textField1.addKeyListener(this);

        textField2 = new Asset().generateTextField();

        textField2.addKeyListener(this);

        textField3 = new Asset().generateTextField();

        textField3.addKeyListener(this);

        textField4 = new Asset().generateTextField();

        textField4.addKeyListener(this);

        textField5 = new Asset().generateTextField();

        textField5.addKeyListener(this);

        textField6 = new Asset().generateTextField();

        textField6.addKeyListener(this);

        button = new Asset().generateButtonWithoutImage("OK", 250, 50);


        panel.setLayout(null);

        panel.setBackground(transparentColour);

        panel.add(mainTitle);
        panel.add(mainContent1);
        panel.add(mainContent2);
        panel.add(mainContent3);
        panel.add(mainContent4);
        panel.add(mainContent4);
        panel.add(mainContent5);
        panel.add(mainContent6);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(textField6);
        panel.add(button);
        panel.add(backgroundPanel);

        frame.add(panel);
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
        int width = e.getComponent().getWidth();
        int height = e.getComponent().getHeight();

        backgroundPanel.setSize(frame.getWidth(), frame.getHeight());
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()), (frame.getHeight() - backgroundPanel.getHeight()));
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        mainTitle.setBounds(width * 70 / 1100, height * 50 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent1.setBounds(width * 70 / 1100, height * 130 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent2.setBounds(width * 70 / 1100, height * 290 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent3.setBounds(width * 70 / 1100, height * 450 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent4.setBounds(width * 700 / 1100, height * 130 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent5.setBounds(width * 700 / 1100, height * 290 / 750, width * 400 / 1100, height * 100 / 750);
        mainContent6.setBounds(width * 700 / 1100, height * 450 / 750, width * 400 / 1100, height * 100 / 750);
        textField1.setBounds(width * 70 / 1100, height * 210 / 750, width * 600 / 1100, height * 60 / 750);
        textField2.setBounds(width * 70 / 1100, height * 360 / 750, width * 600 / 1100, height * 60 / 750);
        textField3.setBounds(width * 70 / 1100, height * 530 / 750, width * 600 / 1100, height * 60 / 750);
        textField4.setBounds(width * 700 / 1100, height * 210 / 750, width * 350 / 1100, height * 60 / 750);
        textField5.setBounds(width * 700 / 1100, height * 360 / 750, width * 350 / 1100, height * 60 / 750);
        textField6.setBounds(width * 700 / 1100, height * 530 / 750, width * 350 / 1100, height * 60 / 750);
        button.setLocation(textField6.getX() + textField6.getWidth() - button.getWidth(), height * 620 / 750);
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
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        CustomerAppointmentPage.setFrameEnable(true);
        frame.dispose();
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
