import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BookAppointmentPage implements ComponentListener, MouseListener {
    public static void main(String[] args) {
        new BookAppointmentPage();
    }

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, promptTP, title, tpFront;
    JPanel backgroundPanel;
    JScrollPane fillInfoPane;
    JTextField tpField;
    JLayeredPane searchButton, cancelButton, saveButton;

    public BookAppointmentPage() {
        frame = new JFrame("Appointment Booking Page");
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

        title = new JLabel("Appointment Booking");
        title.setFont(Asset.getTitleFont());

        promptTP = new JLabel("Enter customer TP number:");
        promptTP.setFont(Asset.getNameFont("Plain"));
        promptTP.setSize(350, 50);

        tpFront = new JLabel("TP");
        tpFront.setFont(Asset.getBodyFont("Plain"));
        tpFront.setSize(30, 30);

        tpField = new JTextField();
        tpField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tpField.setFont(Asset.getBodyFont("Plain"));
        tpField.setHorizontalAlignment(JTextField.CENTER);

        searchButton = new Asset().generateButtonWithoutImage("Search", 150, promptTP.getHeight());
        searchButton.setFocusable(true);
        searchButton.addMouseListener(this);

        fillInfoPane = new InformationPane().bookAppointmentPane();
        fillInfoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        fillInfoPane.setVisible(false);

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", searchButton.getWidth(), searchButton.getHeight());

        saveButton = new Asset().generateButtonWithoutImage("Place appointment", cancelButton.getWidth() + 100, searchButton.getHeight());

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(promptTP);
        backgroundPanel.add(tpFront);
        backgroundPanel.add(tpField);
        backgroundPanel.add(searchButton);
        backgroundPanel.add(fillInfoPane);
        backgroundPanel.add(cancelButton);
        backgroundPanel.add(saveButton);

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
        promptTP.setLocation(title.getX(), title.getY() + title.getHeight() + 20);
        tpField.setBounds(promptTP.getX() + promptTP.getWidth(), promptTP.getY(), 200, promptTP.getHeight());
        tpFront.setLocation(tpField.getX() + 30, tpField.getY() + (tpField.getHeight() - tpFront.getHeight())/2);
        searchButton.setLocation(tpField.getX() + tpField.getWidth() + 20, tpField.getY() - 3);
        fillInfoPane.setBounds(title.getX(), promptTP.getY() + promptTP.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - promptTP.getY() - promptTP.getHeight()) * 9 / 10 - 100);
        saveButton.setLocation(fillInfoPane.getX() + fillInfoPane.getWidth() - saveButton.getWidth(), fillInfoPane.getY() + fillInfoPane.getHeight() + 20);
        cancelButton.setLocation(saveButton.getX() - 20 - cancelButton.getWidth(), saveButton.getY());
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
        if (e.getSource() == searchButton) {
            fillInfoPane.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
