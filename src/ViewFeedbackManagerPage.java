import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewFeedbackManagerPage implements ComponentListener, MouseListener, WindowListener {

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title, leftSubtitle, rightSubtitle;
    JPanel backgroundPanel, leftPanel, rightPanel;
    JScrollPane rightScrollPanel;

    public ViewFeedbackManagerPage() {

        frame = new JFrame("View Feedback Page");
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

        title = new JLabel("View Feedback");
        title.setFont(Asset.getTitleFont());

        leftSubtitle = new JLabel("Overall Review");
        leftSubtitle.setFont(Asset.getNameFont("Bold"));

        rightSubtitle = new JLabel("Individual Feedback");
        rightSubtitle.setFont(Asset.getNameFont("Bold"));

        leftPanel = new ViewFeedbackComponent().managerOverallReview();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(null);

        rightPanel = new ViewFeedbackComponent().individualReview(null);
        rightPanel.setBackground(Color.WHITE);

        rightScrollPanel = new JScrollPane(rightPanel);
        rightScrollPanel.setBounds(628, 240, 446, 490);
        rightScrollPanel.setBorder(null);

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(leftSubtitle);
        backgroundPanel.add(rightSubtitle);
        backgroundPanel.add(leftPanel);
        backgroundPanel.add(rightScrollPanel);

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
        leftSubtitle.setBounds(title.getX(), title.getY() + title.getHeight() + 30, (backgroundPanel.getWidth() - title.getX()) / 2 - 50, 50);
        rightSubtitle.setBounds(leftSubtitle.getX() + leftSubtitle.getWidth() + 50, leftSubtitle.getY(), leftSubtitle.getWidth(), leftSubtitle.getHeight());
        leftPanel.setLocation(leftSubtitle.getX(), leftSubtitle.getY() + leftSubtitle.getHeight() + 20);
        leftPanel.setSize(leftSubtitle.getWidth(), backgroundPanel.getHeight() - leftPanel.getY() - 50);
        rightScrollPanel.setBounds(rightSubtitle.getX(), leftPanel.getY(), leftPanel.getWidth(), leftPanel.getHeight());
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
        if (e.getSource() == backArrow) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            ManagerMainPage.setFrameVisibility(true);
            frame.dispose();
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to logout from the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
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
