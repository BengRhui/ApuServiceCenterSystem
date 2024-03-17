import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class CreateAccountPage implements ComponentListener, MouseListener {
    public static void main(String[] args) {
        new CreateAccountPage();
    }

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, accountText, title;
    JPanel backgroundPanel, fillInfoPane;
    JScrollPane displayManagerPanel, displayTechnicianPanel, displayCustomerPanel;
    JLayeredPane confirmButton, cancelButton, saveButton;
    JComboBox<String> selectAccount;
    CardLayout layout;

    public CreateAccountPage() {
        frame = new JFrame("Create Account Page");
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

        title = new JLabel("Create Account");
        title.setFont(Asset.getTitleFont());

        accountText = new JLabel("Choose type of account:");
        accountText.setFont(Asset.getNameFont("Plain"));
        accountText.setSize(350, 50);

        String[] accountChoice = User.getTypesOfAccount();
        selectAccount = new JComboBox<>(accountChoice);
        selectAccount.setFont(Asset.getBodyFont("Plain"));
        selectAccount.setSize(accountText.getWidth(), accountText.getHeight());
        selectAccount.setBackground(Color.WHITE);

        confirmButton = new Asset().generateButtonWithoutImage("Confirm", 150, accountText.getHeight());
        confirmButton.setFocusable(true);
        confirmButton.addMouseListener(this);

        layout = new CardLayout();
        fillInfoPane = new JPanel(layout);
        fillInfoPane.setVisible(false);

        displayManagerPanel = new JScrollPane(new InformationPane().createManagerAndTechnicianAccount());
        displayTechnicianPanel = new JScrollPane(new InformationPane().bookAppointmentPane());
        displayCustomerPanel = new JScrollPane(new InformationPane().customerInformation());

        fillInfoPane.add(displayManagerPanel);
        fillInfoPane.add(displayTechnicianPanel);
        fillInfoPane.add(displayCustomerPanel);

        layout.addLayoutComponent(displayManagerPanel, "Manager");
        layout.addLayoutComponent(displayTechnicianPanel, "Technician");
        layout.addLayoutComponent(displayCustomerPanel, "Customer");

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", confirmButton.getWidth(), confirmButton.getHeight());

        saveButton = new Asset().generateButtonWithoutImage("Place appointment", cancelButton.getWidth() + 100, confirmButton.getHeight());

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(accountText);
        backgroundPanel.add(selectAccount);
        backgroundPanel.add(confirmButton);
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
        accountText.setLocation(title.getX(), title.getY() + title.getHeight() + 20);
        selectAccount.setLocation(accountText.getX() + accountText.getWidth(), accountText.getY());
        confirmButton.setLocation(selectAccount.getX() + selectAccount.getWidth() + 20, selectAccount.getY() - 3);
        fillInfoPane.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
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
        if (e.getSource() == confirmButton) {
            if (Objects.equals(selectAccount.getSelectedItem(), "Manager")) {
                layout.show(fillInfoPane, "Manager");
            } else if (Objects.equals(selectAccount.getSelectedItem(), "Technician")) {
                layout.show(fillInfoPane, "Technician");
            } else if (Objects.equals(selectAccount.getSelectedItem(), "Customer")) {
                layout.show(fillInfoPane, "Customer");
            } else {
                System.out.println("Error with selecting accounts.");
            }
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
