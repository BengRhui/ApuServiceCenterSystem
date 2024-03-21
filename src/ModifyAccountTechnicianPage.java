import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ModifyAccountTechnicianPage implements ComponentListener {
    public static void main(String[] args) {
        currentPage = new ModifyAccountTechnicianPage();
    }

    static ModifyAccountTechnicianPage currentPage;
    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title;
    JPanel backgroundPanel;
    JScrollPane editPanel;
    JLayeredPane cancelButton, saveButton;
    InformationPaneComponent infoPane1;

    public ModifyAccountTechnicianPage() {

        frame = new JFrame("Modify Technician Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);

        infoPane1 = new InformationPaneComponent();

        backgroundPicture = new Asset().generateImage("background.jpg");

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        backArrow = new Asset().generateImage("backArrow_icon.png");

        logoutButton = new Asset().generateImage("logout_icon.png");

        title = new JLabel("Modify Account");
        title.setFont(Asset.getTitleFont());

        editPanel = new JScrollPane(infoPane1.modifyAccountTechnician());
        editPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        saveButton = new Asset().generateButtonWithoutImage("Save details", 250, 50);
        cancelButton = new Asset().generateButtonWithoutImage("Cancel", saveButton.getWidth(), saveButton.getHeight());


        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(editPanel);
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
        saveButton.setLocation(backgroundPanel.getWidth() - saveButton.getWidth() - 40, backgroundPanel.getHeight() - saveButton.getHeight() - 40);
        cancelButton.setLocation(saveButton.getX() - 20 - cancelButton.getWidth(), saveButton.getY());
        editPanel.setBounds(title.getX(), title.getY() + title.getHeight() + 20, saveButton.getX() + saveButton.getWidth() - title.getX(), cancelButton.getY() - title.getHeight() - title.getY() - 40);
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
