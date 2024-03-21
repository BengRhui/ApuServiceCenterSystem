import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ViewFeedbackTechnicianPage implements ComponentListener {
    public static void main(String[] args) {
        new ViewFeedbackTechnicianPage();
    }

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title, leftSubtitle, rightSubtitle;
    JPanel backgroundPanel, leftPanel, rightPanel;
    JScrollPane rightScrollPanel;

    public ViewFeedbackTechnicianPage() {

        frame = new JFrame("View Feedback Page");
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

        title = new JLabel("View Feedback");
        title.setFont(Asset.getTitleFont());

        leftSubtitle = new JLabel("Overall Review");
        leftSubtitle.setFont(Asset.getNameFont("Bold"));

        rightSubtitle = new JLabel("Individual Feedback");
        rightSubtitle.setFont(Asset.getNameFont("Bold"));

        leftPanel = new ViewFeedbackComponent().technicianOverallView("T001");
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(null);

        rightPanel = new ViewFeedbackComponent().individualReview("T001");
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
}
