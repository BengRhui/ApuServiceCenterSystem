import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManageAppointment implements ComponentListener, MouseListener {

    public static void main(String[] args) {
        new ManageAppointment();
    }

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title;
    JPanel backgroundPanel, overallPanel, tablePanel;
    JLayeredPane switchButton, containerPane;
    CardLayout cardLayout = new CardLayout();
    ScheduleView todayPanel;

    public ManageAppointment() {
        frame = new JFrame("Modify Technician Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);

        backgroundPicture = new Asset().generateImage("background.jpg");

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        backArrow = new Asset().generateImage("backArrow_icon.png");

        logoutButton = new Asset().generateImage("logout_icon.png");

        title = new JLabel("Manage Appointment");
        title.setFont(Asset.getTitleFont());

        overallPanel = new JPanel(cardLayout);
        overallPanel.setBackground(Asset.getTransparentColour());

        containerPane = new JLayeredPane();
        containerPane.setSize(frame.getWidth() * 73 / 100, frame.getHeight() * 6 / 10);


        todayPanel = new ScheduleView(containerPane, "Lim", "09/03/2024");

        tablePanel = new AppointmentTable("T001");
        tablePanel.setBackground(Color.BLUE);

        switchButton = new Asset().generateButtonWithoutImage("Switch View", 250, 50);
        switchButton.addMouseListener(this);

        backgroundPanel.addComponentListener(this);

        overallPanel.add(todayPanel, "Today");
        overallPanel.add(tablePanel, "Table");

        containerPane.add(overallPanel, JLayeredPane.DEFAULT_LAYER);
        containerPane.add(switchButton, JLayeredPane.MODAL_LAYER);

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(containerPane);

        frame.add(backgroundPanel);
        frame.add(backgroundPicture);

        frame.setVisible(true);

    }

    @Override
    public void componentResized(ComponentEvent e) {
        backgroundPicture.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) / 2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);
        backArrow.setLocation(backgroundPanel.getX(), backgroundPanel.getY() + 20);
        logoutButton.setLocation(backgroundPanel.getWidth() - logoutButton.getWidth() - 50, backgroundPanel.getY() - 5);
        title.setBounds(backArrow.getX() + backArrow.getWidth() + 20, logoutButton.getY(), logoutButton.getX() - backArrow.getWidth() - backArrow.getX(), logoutButton.getHeight());
        containerPane.setBounds(title.getX(), title.getY() + title.getHeight() + 20, frame.getWidth() * 73 / 100, frame.getHeight() * 6 / 10);
        overallPanel.setBounds(0, 0, containerPane.getWidth(), containerPane.getHeight());
        tablePanel.setBounds(0, 0, overallPanel.getWidth(), overallPanel.getHeight());

        todayPanel.revalidate();
        todayPanel.repaint();

        switchButton.setLocation(containerPane.getWidth() - switchButton.getWidth(), 0);
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
        if (e.getSource() == switchButton) {
            cardLayout.next(overallPanel);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
