import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TechnicianMainPage implements ComponentListener {

    static JFrame frame;
    JLayeredPane leftPanel, rightPanel, modifyAccountButton, manageAppointmentButton, viewFeedbackButton;
    JPanel profilePicture, line, informationPanel;
    JLabel backgroundLeft, name, accountType, position, gender, dateJoined, nationality, maritalStatus, address, email,
            contactNo, positionInfo, genderInfo, dateJoinedInfo, nationalityInfo, maritalStatusInfo, addressLine1,
            addressLine2, addressLine3, addressLine4, emailInfo, contactNoInfo, backgroundRight, mainTitle, exitButton;
    int profileRadius = 125, lineStroke = 5;

    public static void main(String[] args) {
        new TechnicianMainPage();
    }

    public TechnicianMainPage() {
        frame = new JFrame("Technician Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);

        leftPanel = new JLayeredPane();
        leftPanel.setLocation(0, 0);
        leftPanel.setSize(frame.getWidth() / 5 * 2, frame.getHeight());
        leftPanel.setLayout(null);

        backgroundLeft = new Asset().generateImage("background_3.jpg");

        profilePicture = new Asset().generateRoundProfile("default.png", profileRadius);

        line = new Asset().drawLine(leftPanel.getX() + leftPanel.getWidth(), leftPanel.getY(), leftPanel.getX() + leftPanel.getWidth(), leftPanel.getHeight() * 2, lineStroke);

        name = new JLabel("Joshua Wafiq A/L Selvakumar", JLabel.CENTER);
        name.setFont(Asset.getNameFont("Bold"));

        accountType = new JLabel("(Technician Account)", JLabel.CENTER);
        accountType.setFont(Asset.getNameFont("Italic"));

        informationPanel = new JPanel();
        informationPanel.setLayout(null);
        informationPanel.setBackground(Asset.getTransparentColour());

        position = new JLabel("Position");
        position.setFont(Asset.getBodyFont("Plain"));

        positionInfo = new JLabel(": Electrician");
        positionInfo.setFont(Asset.getBodyFont("Plain"));

        gender = new JLabel("Gender");
        gender.setFont(Asset.getBodyFont("Plain"));

        genderInfo = new JLabel(": Male");
        genderInfo.setFont(Asset.getBodyFont("Plain"));

        dateJoined = new JLabel("Date Joined");
        dateJoined.setFont(Asset.getBodyFont("Plain"));

        dateJoinedInfo = new JLabel(": 5 March 2024");
        dateJoinedInfo.setFont(Asset.getBodyFont("Plain"));

        nationality = new JLabel("Nationality");
        nationality.setFont(Asset.getBodyFont("Plain"));

        nationalityInfo = new JLabel(": Malaysian");
        nationalityInfo.setFont(Asset.getBodyFont("Plain"));

        maritalStatus = new JLabel("Marital Status");
        maritalStatus.setFont(Asset.getBodyFont("Plain"));

        maritalStatusInfo = new JLabel(": Single");
        maritalStatusInfo.setFont(Asset.getBodyFont("Plain"));

        address = new JLabel("Address");
        address.setFont(Asset.getBodyFont("Plain"));

        addressLine1 = new JLabel(": No 10");
        addressLine1.setFont(Asset.getBodyFont("Plain"));

        addressLine2 = new JLabel("  Jalan Merah 20");
        addressLine2.setFont(Asset.getBodyFont("Plain"));

        addressLine3 = new JLabel("  Taman Biru 30");
        addressLine3.setFont(Asset.getBodyFont("Plain"));

        addressLine4 = new JLabel("  31400 Ipoh, Perak");
        addressLine4.setFont(Asset.getBodyFont("Plain"));

        email = new JLabel("Email");
        email.setFont(Asset.getBodyFont("Plain"));

        emailInfo = new JLabel(": limbengrhui3@gmail.com");
        emailInfo.setFont(Asset.getBodyFont("Plain"));

        contactNo = new JLabel("Contact No");
        contactNo.setFont(Asset.getBodyFont("Plain"));

        contactNoInfo = new JLabel(": 011-10663136");
        contactNoInfo.setFont(Asset.getBodyFont("Plain"));

        informationPanel.add(position);
        informationPanel.add(positionInfo);
        informationPanel.add(gender);
        informationPanel.add(genderInfo);
        informationPanel.add(dateJoined);
        informationPanel.add(dateJoinedInfo);
        informationPanel.add(nationality);
        informationPanel.add(nationalityInfo);
        informationPanel.add(maritalStatus);
        informationPanel.add(maritalStatusInfo);
        informationPanel.add(address);
        informationPanel.add(addressLine1);
        informationPanel.add(addressLine2);
        informationPanel.add(addressLine3);
        informationPanel.add(addressLine4);
        informationPanel.add(email);
        informationPanel.add(emailInfo);
        informationPanel.add(contactNo);
        informationPanel.add(contactNoInfo);

        leftPanel.add(backgroundLeft, JLayeredPane.DEFAULT_LAYER);
        leftPanel.add(profilePicture, JLayeredPane.PALETTE_LAYER);
        leftPanel.add(name, JLayeredPane.PALETTE_LAYER);
        leftPanel.add(accountType, JLayeredPane.PALETTE_LAYER);
        leftPanel.add(informationPanel, JLayeredPane.PALETTE_LAYER);
        leftPanel.setPreferredSize(new Dimension(frame.getWidth() / 3, frame.getHeight()));

        rightPanel = new JLayeredPane();
        rightPanel.setBounds(leftPanel.getWidth(), 0, frame.getWidth() - leftPanel.getWidth(), frame.getHeight());

        backgroundRight = new JLabel();
        backgroundRight.setBackground(Color.WHITE);
        backgroundRight.setOpaque(true);

        mainTitle = new JLabel("Main Page");
        mainTitle.setFont(Asset.getTitleFont());

        exitButton = new Asset().generateImage("logout_icon.png");

        modifyAccountButton = new Asset().generateButtonWithImageLeft("<html>Modify Account <br>and Password</html>", "edit_vector_small.png", 600, 180);
        manageAppointmentButton = new Asset().generateButtonWithImageLeft("<html>Manage Appointment <br>and Payment</html>", "checkAppointment_vector_small.png", 600, 180);
        viewFeedbackButton = new Asset().generateButtonWithImageLeft("View Feedback", "feedback_vector_small.png", 600, 180);

        rightPanel.add(backgroundRight, JLayeredPane.DEFAULT_LAYER);
        rightPanel.add(mainTitle, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(exitButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(modifyAccountButton, JLayeredPane.MODAL_LAYER);
        rightPanel.add(manageAppointmentButton, JLayeredPane.MODAL_LAYER);
        rightPanel.add(viewFeedbackButton, JLayeredPane.MODAL_LAYER);

        frame.add(line);
        frame.add(rightPanel);
        frame.add(leftPanel);
        frame.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {

        leftPanel.setSize(frame.getWidth() / 5 * 2, frame.getHeight());

        backgroundLeft.setBounds(0, 0, leftPanel.getWidth(), leftPanel.getHeight());

        profilePicture.setLocation(leftPanel.getWidth() / 2 - profileRadius, leftPanel.getHeight() / 20);

        line.setLocation(leftPanel.getWidth() + leftPanel.getX(), leftPanel.getY());

        name.setSize(leftPanel.getWidth(), 50);
        name.setLocation((leftPanel.getWidth() - name.getWidth()) / 2, leftPanel.getHeight() / 20 + 280);

        accountType.setSize(leftPanel.getWidth(), 50);
        accountType.setLocation((leftPanel.getWidth() - accountType.getWidth()) / 2, leftPanel.getHeight() / 20 + 325);

        informationPanel.setBounds(leftPanel.getWidth() / 15, leftPanel.getHeight() / 20 + 375, leftPanel.getWidth() - leftPanel.getWidth() / 15 * 2, leftPanel.getHeight() / 2 - 25);

        position.setBounds(10, 10, informationPanel.getWidth() - 20, 40);
        gender.setBounds(10, 10 + position.getY() + 30, informationPanel.getWidth() - 20, 40);
        dateJoined.setBounds(10, 10 + gender.getY() + 30, informationPanel.getWidth() - 20, 40);
        nationality.setBounds(10, 10 + dateJoined.getY() + 30, informationPanel.getWidth() - 20, 40);
        maritalStatus.setBounds(10, 10 + nationality.getY() + 30, informationPanel.getWidth() - 20, 40);
        address.setBounds(10, 10 + maritalStatus.getY() + 30, informationPanel.getWidth() - 20, 40);
        email.setBounds(10, 10 + address.getY() + 120, informationPanel.getWidth() - 20, 40);
        contactNo.setBounds(10, 10 + email.getY() + 30, informationPanel.getWidth() - 20, 40);

        positionInfo.setBounds(150, position.getY(), informationPanel.getWidth() - positionInfo.getX(), 40);
        genderInfo.setBounds(150, gender.getY(), informationPanel.getWidth() - genderInfo.getX(), 40);
        dateJoinedInfo.setBounds(150, dateJoined.getY(), informationPanel.getWidth() - dateJoinedInfo.getX(), 40);
        nationalityInfo.setBounds(150, nationality.getY(), informationPanel.getWidth() - nationalityInfo.getX(), 40);
        maritalStatusInfo.setBounds(150, maritalStatus.getY(), informationPanel.getWidth() - maritalStatusInfo.getX(), 40);
        addressLine1.setBounds(150, address.getY(), informationPanel.getWidth() - addressLine1.getX(), 40);
        addressLine2.setBounds(150, addressLine1.getY() + 30, informationPanel.getWidth() - addressLine2.getX(), 40);
        addressLine3.setBounds(150, addressLine2.getY() + 30, informationPanel.getWidth() - addressLine3.getX(), 40);
        addressLine4.setBounds(150, addressLine3.getY() + 30, informationPanel.getWidth() - addressLine4.getX(), 40);
        emailInfo.setBounds(150, email.getY(), informationPanel.getWidth() - emailInfo.getX(), 40);
        contactNoInfo.setBounds(150, contactNo.getY(), informationPanel.getWidth() - contactNoInfo.getX(), 40);

        rightPanel.setBounds(leftPanel.getWidth(), 0, frame.getWidth() - leftPanel.getWidth(), frame.getHeight());

        backgroundRight.setBounds(0, 0, rightPanel.getWidth(), rightPanel.getHeight());

        mainTitle.setBounds(70, rightPanel.getHeight() / 20, rightPanel.getWidth() / 2, 100);

        exitButton.setBounds(rightPanel.getWidth() * 4 / 5, mainTitle.getY(), 100, 100);

        modifyAccountButton.setLocation(mainTitle.getX(), mainTitle.getY() + mainTitle.getHeight() + 50);
        manageAppointmentButton.setLocation(mainTitle.getX(), modifyAccountButton.getY() + modifyAccountButton.getHeight() + 20);
        viewFeedbackButton.setLocation(mainTitle.getX(), manageAppointmentButton.getY() + manageAppointmentButton.getHeight() + 20);

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
