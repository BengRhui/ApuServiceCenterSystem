import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class TechnicianMainPage implements ComponentListener, WindowListener, MouseListener {

    static JFrame frame;
    JLayeredPane leftPanel, rightPanel, modifyAccountButton, manageAppointmentButton, viewFeedbackButton;
    JPanel profilePicture, line, informationPanel;
    JLabel backgroundLeft, name, accountType, position, gender, dateJoined, nationality, maritalStatus, address, email,
            contactNo, positionInfo, genderInfo, dateJoinedInfo, nationalityInfo, maritalStatusInfo, addressLine1,
            addressLine2, addressLine3, addressLine4, emailInfo, contactNoInfo, backgroundRight, mainTitle, exitButton;
    int profileRadius = 125, lineStroke = 5;
    Technician currentTechnician;

    public TechnicianMainPage(Technician technician) {

        currentTechnician = technician;

        frame = new JFrame("Technician Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        leftPanel = new JLayeredPane();
        leftPanel.setLocation(0, 0);
        leftPanel.setSize(frame.getWidth() / 5 * 2, frame.getHeight());
        leftPanel.setLayout(null);

        backgroundLeft = new Asset().generateImage("background_3.jpg");

        profilePicture = new Asset().generateRoundProfile("default.png", profileRadius);

        line = new Asset().drawLine(leftPanel.getX() + leftPanel.getWidth(), leftPanel.getY(), leftPanel.getX() + leftPanel.getWidth(), leftPanel.getHeight() * 2, lineStroke);

        name = new JLabel();
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setFont(Asset.getNameFont("Bold"));

        accountType = new JLabel("(Technician Account)", JLabel.CENTER);
        accountType.setFont(Asset.getNameFont("Italic"));

        informationPanel = new JPanel();
        informationPanel.setLayout(null);
        informationPanel.setBackground(Asset.getTransparentColour());

        position = new JLabel("Position");
        position.setFont(Asset.getBodyFont("Plain"));

        positionInfo = new JLabel();
        positionInfo.setFont(Asset.getBodyFont("Plain"));

        gender = new JLabel("Gender");
        gender.setFont(Asset.getBodyFont("Plain"));

        genderInfo = new JLabel();
        genderInfo.setFont(Asset.getBodyFont("Plain"));

        dateJoined = new JLabel("Date Joined");
        dateJoined.setFont(Asset.getBodyFont("Plain"));

        dateJoinedInfo = new JLabel();
        dateJoinedInfo.setFont(Asset.getBodyFont("Plain"));

        nationality = new JLabel("Nationality");
        nationality.setFont(Asset.getBodyFont("Plain"));

        nationalityInfo = new JLabel();
        nationalityInfo.setFont(Asset.getBodyFont("Plain"));

        maritalStatus = new JLabel("Marital Status");
        maritalStatus.setFont(Asset.getBodyFont("Plain"));

        maritalStatusInfo = new JLabel();
        maritalStatusInfo.setFont(Asset.getBodyFont("Plain"));

        address = new JLabel("Address");
        address.setFont(Asset.getBodyFont("Plain"));

        addressLine1 = new JLabel();
        addressLine1.setFont(Asset.getBodyFont("Plain"));

        addressLine2 = new JLabel();
        addressLine2.setFont(Asset.getBodyFont("Plain"));

        addressLine3 = new JLabel();
        addressLine3.setFont(Asset.getBodyFont("Plain"));

        addressLine4 = new JLabel();
        addressLine4.setFont(Asset.getBodyFont("Plain"));

        email = new JLabel("Email");
        email.setFont(Asset.getBodyFont("Plain"));

        emailInfo = new JLabel();
        emailInfo.setFont(Asset.getBodyFont("Plain"));

        contactNo = new JLabel("Contact No");
        contactNo.setFont(Asset.getBodyFont("Plain"));

        contactNoInfo = new JLabel();
        contactNoInfo.setFont(Asset.getBodyFont("Plain"));

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        name.setText(technician.name);
        positionInfo.setText(": " + technician.position);
        genderInfo.setText(": " + technician.gender);
        dateJoinedInfo.setText(": " + technician.dateJoined.format(dateFormat));
        nationalityInfo.setText(": " + technician.nationality);
        maritalStatusInfo.setText(": " + technician.maritalStatus);
        addressLine1.setText(": " + technician.addressLine1);
        addressLine2.setText("  " + technician.addressLine2);
        addressLine3.setText("  " + technician.addressLine3);
        addressLine4.setText("  " + technician.postcode + " " + technician.city + ", " + technician.state);
        emailInfo.setText(": " + technician.email);
        contactNoInfo.setText(": " + technician.contactNumber);

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
        exitButton.addMouseListener(this);

        modifyAccountButton = new Asset().generateButtonWithImageLeft("<html>Modify Account <br>and Password</html>", "edit_vector_small.png", 600, 180);
        manageAppointmentButton = new Asset().generateButtonWithImageLeft("<html>Manage Appointment <br>and Payment</html>", "checkAppointment_vector_small.png", 600, 180);
        viewFeedbackButton = new Asset().generateButtonWithImageLeft("View Feedback", "feedback_vector_small.png", 600, 180);

        modifyAccountButton.addMouseListener(this);
        manageAppointmentButton.addMouseListener(this);
        viewFeedbackButton.addMouseListener(this);

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

    public static void setVisibility(boolean status) {
        frame.setVisible(status);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to logout from the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new LoginPage();
                frame.dispose();
            }
        } else if (e.getSource() == modifyAccountButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new ModifyAccountTechnicianPage(currentTechnician);
            frame.dispose();

        } else if (e.getSource() == manageAppointmentButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new ManageAppointmentPage(currentTechnician);
            frame.setVisible(false);
            frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());

        } else if (e.getSource() == viewFeedbackButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new ViewFeedbackTechnicianPage(currentTechnician);
            frame.setVisible(false);
            frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }
}
