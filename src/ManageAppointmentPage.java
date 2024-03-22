import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageAppointmentPage implements ComponentListener, MouseListener, WindowListener {

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title;
    JPanel backgroundPanel, overallPanel, tablePanel;
    JLayeredPane switchButton, containerPane;
    CardLayout cardLayout = new CardLayout();
    ViewScheduleComponent todayPanel;

    public ManageAppointmentPage(Technician technician) {
        frame = new JFrame("Modify Technician Page");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        backgroundPicture = new Asset().generateImage("background.jpg");

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        backArrow = new Asset().generateImage("backArrow_icon.png");
        backArrow.addMouseListener(this);

        logoutButton = new Asset().generateImage("logout_icon.png");
        logoutButton.addMouseListener(this);

        title = new JLabel("Manage Appointment");
        title.setFont(Asset.getTitleFont());

        overallPanel = new JPanel(cardLayout);
        overallPanel.setBackground(Asset.getTransparentColour());

        containerPane = new JLayeredPane();
        containerPane.setSize(frame.getWidth() * 73 / 100, frame.getHeight() * 6 / 10);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        todayPanel = new ViewScheduleComponent(containerPane, technician.name, LocalDate.now().format(dateFormat));

        tablePanel = new AppointmentTableComponent(technician.technicianID);

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
        tablePanel.setSize(overallPanel.getWidth(), overallPanel.getHeight());

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
        } else if (e.getSource() == backArrow) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Do you wish to return to the main page?<br>Do not forget to save the data before exit.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "return_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                TechnicianMainPage.setVisibility(true);
                frame.dispose();
            }
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Are you sure that you would like to logout from the system?<br>Do not forget to save the data before exit.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new LoginPage();
                frame.dispose();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        backArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backArrow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == frame) {
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to logout from the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new LoginPage();
                frame.dispose();
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
