import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class PastAppointmentDetailsPage implements KeyListener, ComponentListener, WindowListener, MouseListener {
    JPanel panel, backgroundPanel;
    JFrame frame;
    JLabel mainTitle, itemLabel, dateLabel, technicianLabel, paymentLabel, startLabel, endLabel;
    JTextField itemText, dateText, technicianText, paymentText, startText, endText;
    JLayeredPane button;

    Color transparentColour = new Color(255,255,255,0);

    public PastAppointmentDetailsPage(Appointment appointment){
        panel = new JPanel();
        frame = new JFrame("Appointment Details");
        frame.setSize(1000, 700);
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

        itemLabel = new JLabel("Item to be Serviced");
        itemLabel.setFont(new Font("Arial",Font.PLAIN,25));

        dateLabel = new JLabel("Appointment Date");
        dateLabel.setFont(new Font("Arial",Font.PLAIN,25));

        technicianLabel = new JLabel("Technician-In-charge");
        technicianLabel.setFont(new Font("Arial",Font.PLAIN,25));

        paymentLabel = new JLabel("Payment");
        paymentLabel.setFont(new Font("Arial",Font.PLAIN,25));

        startLabel = new JLabel("Starting Time");
        startLabel.setFont(new Font("Arial", Font.PLAIN,25));

        endLabel = new JLabel("Ending Time");
        endLabel.setFont(new Font("Arial",Font.PLAIN,25));

        itemText = new Asset().generateTextField();
        itemText.setText(appointment.item);
        itemText.setEditable(false);
        itemText.addKeyListener(this);

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateText = new Asset().generateTextField();
        dateText.setText(appointment.date.format(formatDate));
        dateText.setEditable(false);
        dateText.addKeyListener(this);

        technicianText = new Asset().generateTextField();
        technicianText.setText(Technician.getNameFromID(appointment.technicianID));
        technicianText.setEditable(false);
        technicianText.addKeyListener(this);

        paymentText = new Asset().generateTextField();
        paymentText.setText(appointment.paymentStatus ? "Paid" : "Unpaid");
        paymentText.setEditable(false);
        paymentText.addKeyListener(this);

        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mm a");

        startText = new Asset().generateTextField();
        startText.setText(appointment.startingTime.format(formatTime));
        startText.setEditable(false);
        startText.addKeyListener(this);

        endText = new Asset().generateTextField();
        endText.setText(appointment.endingTime.format(formatTime));
        endText.setEditable(false);
        endText.addKeyListener(this);

        button = new Asset().generateButtonWithoutImage("OK", 250, 70);
        button.addMouseListener(this);

        panel.setLayout(null);

        panel.setBackground(transparentColour);

        panel.add(mainTitle);

        panel.add(itemLabel);
        panel.add(dateLabel);
        panel.add(technicianLabel);
        panel.add(paymentLabel);
        panel.add(startLabel);
        panel.add(endLabel);

        panel.add(itemText);
        panel.add(dateText);
        panel.add(technicianText);
        panel.add(paymentText);
        panel.add(startText);
        panel.add(endText);

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
        itemLabel.setBounds(width * 70 / 1100, height * 160 / 750, width * 400 / 1100, height * 50 / 750);
        dateLabel.setBounds(width * 70 / 1100, height * 280 / 750, width * 400 / 1100, height * 50 / 750);
        technicianLabel.setBounds(width * 70 / 1100, height * 400 / 750, width * 400 / 1100, height * 50 / 750);
        paymentLabel.setBounds(width * 700 / 1100, height * 160 / 750, width * 400 / 1100, height * 50 / 750);
        startLabel.setBounds(width * 700 / 1100, height * 280 / 750, width * 400 / 1100, height * 50 / 750);
        endLabel.setBounds(width * 700 / 1100, height * 400 / 750, width * 400 / 1100, height * 50 / 750);
        itemText.setBounds(width * 70 / 1100, height * 210 / 750, width * 600 / 1100, height * 50 / 750);
        dateText.setBounds(width * 70 / 1100, height * 330 / 750, width * 600 / 1100, height * 50 / 750);
        technicianText.setBounds(width * 70 / 1100, height * 450 / 750, width * 600 / 1100, height * 50 / 750);
        paymentText.setBounds(width * 700 / 1100, height * 210 / 750, width * 350 / 1100, height * 50 / 750);
        startText.setBounds(width * 700 / 1100, height * 330 / 750, width * 350 / 1100, height * 50 / 750);
        endText.setBounds(width * 700 / 1100, height * 450 / 750, width * 350 / 1100, height * 50 / 750);
        button.setLocation(endText.getX() + endText.getWidth() - button.getWidth(), height * 550 / 750);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == button) {
            CustomerAppointmentPage.setFrameEnable(true);
            frame.dispose();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
