import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomerAppointmentPage implements ComponentListener, MouseListener {

    static JFrame frame;
    JPanel titleTopPanel, contentPanel, marginPanel, leftEmptyPane, rightEmptyPane, topEmptyPane, bottomEmptyPane,
            leftContent, rightContent, leftContextPane, rightContextPane, wholeContextPane, leftInfo, rightInfo;
    JScrollPane leftScroll, rightScroll;
    JLabel title, logoutButton, titleBottomPanel, leftTitle, rightTitle, rightSubtitle;
    JLayeredPane titlePane;

    public CustomerAppointmentPage(Student currentStudent) {
        frame = new JFrame("View Customer Appointment");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);

        titleTopPanel = new JPanel(new BorderLayout());
        titleTopPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6));

        marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(85, frame.getHeight() / 6));
        marginPanel.setBackground(Asset.getTransparentColour());

        logoutButton = new Asset().generateImage("logout_icon.png");
        logoutButton.setPreferredSize(new Dimension(frame.getHeight() / 6, frame.getHeight() / 6));
        logoutButton.addMouseListener(this);

        title = new JLabel("Appointments");
        title.setFont(Asset.getTitleFont());
        title.setPreferredSize(new Dimension(frame.getWidth() - 250, frame.getHeight() / 6));

        titleTopPanel.add(marginPanel, BorderLayout.WEST);
        titleTopPanel.add(title, BorderLayout.CENTER);
        titleTopPanel.add(logoutButton, BorderLayout.EAST);
        titleTopPanel.setBackground(Asset.getTransparentColour());
        titleTopPanel.setOpaque(false);
        titleTopPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight() / 6);
        titleTopPanel.addComponentListener(this);

        titleBottomPanel = new Asset().generateImage("background_1.jpg");
        titleBottomPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight() / 6);

        titlePane = new JLayeredPane();
        titlePane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6));

        titlePane.add(titleTopPanel, JLayeredPane.PALETTE_LAYER);
        titlePane.add(titleBottomPanel, JLayeredPane.DEFAULT_LAYER);

        leftEmptyPane = new JPanel();
        leftEmptyPane.setPreferredSize(new Dimension(frame.getWidth() / 15, frame.getHeight()));
        leftEmptyPane.setBackground(Color.WHITE);

        rightEmptyPane = new JPanel();
        rightEmptyPane.setPreferredSize(new Dimension(frame.getWidth() / 15, frame.getHeight()));
        rightEmptyPane.setBackground(Color.WHITE);

        topEmptyPane = new JPanel();
        topEmptyPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 25));
        topEmptyPane.setBackground(Color.WHITE);

        bottomEmptyPane = new JPanel();
        bottomEmptyPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 20));
        bottomEmptyPane.setBackground(Color.WHITE);

        leftContextPane = new JPanel(null);
        leftContextPane.setBackground(Color.WHITE);

        leftTitle = new JLabel("<html>Upcoming<br>Appointments</html>");
        leftTitle.setFont(Asset.getNameFont("Bold"));
        leftTitle.setVerticalTextPosition(JLabel.TOP);

        leftInfo = new ViewAppointmentComponent(currentStudent.tpNumber).generateUpcomingAppointment();
        leftInfo.setBackground(Color.WHITE);

        leftScroll = new JScrollPane(leftInfo);
        leftScroll.setBorder(null);

        leftContent = new JPanel(null);
        leftContent.add(leftScroll);

        leftContextPane.add(leftTitle);
        leftContextPane.add(leftContent);

        rightContextPane = new JPanel(null);
        rightContextPane.setBackground(Color.WHITE);

        rightTitle = new JLabel("Previous Appointment");
        rightTitle.setFont(Asset.getNameFont("Bold"));

        rightSubtitle = new JLabel("<html>Please provide your feedback for your previous appointment.<br>" +
                "We value your opinion and strive to provide a better service to you in the future.</html>");
        rightSubtitle.setFont(Asset.getBodyFont("Plain"));

        rightInfo = new ViewAppointmentComponent(currentStudent.tpNumber).displayPreviousAppointment();
        rightInfo.setBackground(Color.WHITE);

        rightScroll = new JScrollPane(rightInfo);
        rightScroll.setBorder(null);

        rightContent = new JPanel(null);
        rightContent.add(rightScroll);

        rightContextPane.add(rightTitle);
        rightContextPane.add(rightSubtitle);
        rightContextPane.add(rightContent);

        wholeContextPane = new JPanel(null);
        wholeContextPane.setBackground(Color.WHITE);

        wholeContextPane.add(leftContextPane);
        wholeContextPane.add(rightContextPane);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 6 * 5));

        contentPanel.add(leftEmptyPane, BorderLayout.EAST);
        contentPanel.add(rightEmptyPane, BorderLayout.WEST);
        contentPanel.add(topEmptyPane, BorderLayout.NORTH);
        contentPanel.add(bottomEmptyPane, BorderLayout.SOUTH);
        contentPanel.add(wholeContextPane, BorderLayout.CENTER);

        frame.add(titlePane, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void setFrameEnable(boolean status) {
        frame.setEnabled(status);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        titleTopPanel.setBounds(0, 0, frame.getWidth(), Asset.getFrameHeight() / 6);
        titleBottomPanel.setBounds(0, 0, frame.getWidth(), Asset.getFrameHeight() / 6);
        leftContextPane.setBounds(0, 0, frame.getWidth() * 3 / 10, frame.getHeight() * 13 / 15);
        rightContextPane.setBounds(leftContextPane.getWidth() + 1, 0, contentPanel.getWidth() - leftContextPane.getWidth(), frame.getHeight() * 13 / 15);
        leftTitle.setBounds(0, 0, leftContextPane.getWidth(), leftContextPane.getHeight() / 9);
        rightTitle.setBounds(0, 0, rightContextPane.getWidth(), leftContextPane.getHeight() / 14);
        rightSubtitle.setBounds(0, rightTitle.getHeight(), rightContextPane.getWidth(), rightTitle.getHeight());
        leftContent.setBounds(0, leftTitle.getHeight() + 20, leftTitle.getWidth() - 40, wholeContextPane.getHeight() - leftTitle.getHeight() - 20);
        rightContent.setBounds(0, rightSubtitle.getY() + rightSubtitle.getHeight() + 20, wholeContextPane.getWidth() - leftTitle.getWidth(), wholeContextPane.getHeight() - rightSubtitle.getY() - rightSubtitle.getHeight() - 20);
        leftScroll.setBounds(0, 0, leftContent.getWidth(), leftContent.getHeight());
        rightScroll.setBounds(0, 0, rightContent.getWidth(), rightContent.getHeight());
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
        if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Do you wish to return to the main menu?", "Return to Main Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "return_icon.png"));
            if (choice == 0) {
                InitialMainPage.setFrameVisible(true);
                frame.dispose();
            }
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
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}
