import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class ManagerMainPage implements ComponentListener, MouseListener, WindowListener {

    static ManagerMainPage page;
    static JFrame frame;
    JLayeredPane leftPanel, rightPanel, createAccountButton, modifyAccountButton, createAppointmentButton, viewFeedbackButton;
    JPanel profilePicture, line, informationPanel;
    JLabel backgroundLeft, name, accountType, position, gender, dateJoined, nationality, maritalStatus, address, email,
            contactNo, positionInfo, genderInfo, dateJoinedInfo, nationalityInfo, maritalStatusInfo, addressLine1,
            addressLine2, addressLine3, addressLine4, emailInfo, contactNoInfo, backgroundRight, mainTitle, exitButton, settingsButton;
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    int profileRadius = 125, lineStroke = 5;

    public ManagerMainPage(Manager manager) {

        frame = new JFrame("Manager Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

        name = new JLabel(manager.name, JLabel.CENTER);
        name.setFont(Asset.getNameFont("Bold"));

        accountType = new JLabel("(Manager Account)", JLabel.CENTER);
        accountType.setFont(Asset.getNameFont("Italic"));

        informationPanel = new JPanel();
        informationPanel.setLayout(null);
        informationPanel.setBackground(Asset.getTransparentColour());

        position = new JLabel("Position");
        position.setFont(Asset.getBodyFont("Plain"));

        positionInfo = new JLabel(": " + manager.position);
        positionInfo.setFont(Asset.getBodyFont("Plain"));

        gender = new JLabel("Gender");
        gender.setFont(Asset.getBodyFont("Plain"));

        genderInfo = new JLabel(": " + manager.gender);
        genderInfo.setFont(Asset.getBodyFont("Plain"));

        dateJoined = new JLabel("Date Joined");
        dateJoined.setFont(Asset.getBodyFont("Plain"));

        dateJoinedInfo = new JLabel(": " + manager.dateJoined.format(dateFormat));
        dateJoinedInfo.setFont(Asset.getBodyFont("Plain"));

        nationality = new JLabel("Nationality");
        nationality.setFont(Asset.getBodyFont("Plain"));

        nationalityInfo = new JLabel(": " + manager.nationality);
        nationalityInfo.setFont(Asset.getBodyFont("Plain"));

        maritalStatus = new JLabel("Marital Status");
        maritalStatus.setFont(Asset.getBodyFont("Plain"));

        maritalStatusInfo = new JLabel(": " + manager.maritalStatus);
        maritalStatusInfo.setFont(Asset.getBodyFont("Plain"));

        address = new JLabel("Address");
        address.setFont(Asset.getBodyFont("Plain"));

        addressLine1 = new JLabel(": " + manager.addressLine1);
        addressLine1.setFont(Asset.getBodyFont("Plain"));

        addressLine2 = new JLabel("  " + manager.addressLine2);
        addressLine2.setFont(Asset.getBodyFont("Plain"));

        addressLine3 = new JLabel("  " + manager.addressLine3);
        addressLine3.setFont(Asset.getBodyFont("Plain"));

        addressLine4 = new JLabel("  " + manager.postcode + " " + manager.city + ", " + manager.state);
        addressLine4.setFont(Asset.getBodyFont("Plain"));

        email = new JLabel("Email");
        email.setFont(Asset.getBodyFont("Plain"));

        emailInfo = new JLabel(": " + manager.email);
        emailInfo.setFont(Asset.getBodyFont("Plain"));

        contactNo = new JLabel("Contact No");
        contactNo.setFont(Asset.getBodyFont("Plain"));

        contactNoInfo = new JLabel(": " + manager.contactNumber);
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

        backgroundRight = new JLabel();
        backgroundRight.setBackground(Color.WHITE);
        backgroundRight.setOpaque(true);

        mainTitle = new JLabel("Main Page");
        mainTitle.setFont(Asset.getTitleFont());

        settingsButton = new Asset().generateImage("settings_icon.png");
        settingsButton.addMouseListener(this);

        exitButton = new Asset().generateImage("logout_icon.png");
        exitButton.addMouseListener(this);

        createAccountButton = new Asset().generateButtonWithImageTop("Create Account", "registerAccount_vector.png", 300, 300);
        createAccountButton.addMouseListener(this);

        modifyAccountButton = new Asset().generateButtonWithImageTop("Modify Account", "edit_vector.png", 300, 300);
        modifyAccountButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new ModifyAccountManagerPage(manager);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        createAppointmentButton = new Asset().generateButtonWithImageTop("Create Appointment", "checkAppointment_vector.png", 300, 300);
        createAppointmentButton.addMouseListener(this);

        viewFeedbackButton = new Asset().generateButtonWithImageTop("View Feedback", "feedback_vector.png", 300, 300);
        viewFeedbackButton.addMouseListener(this);

        rightPanel.add(settingsButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(backgroundRight, JLayeredPane.DEFAULT_LAYER);
        rightPanel.add(mainTitle, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(exitButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(createAccountButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(modifyAccountButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(createAppointmentButton, JLayeredPane.PALETTE_LAYER);
        rightPanel.add(viewFeedbackButton, JLayeredPane.PALETTE_LAYER);


        frame.add(line);
        frame.add(rightPanel);
        frame.add(leftPanel);
        frame.setVisible(true);
    }

    public static void setFrameVisibility(boolean status) {
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
        settingsButton.setBounds(exitButton.getX() - settingsButton.getWidth() - 20, exitButton.getY(), 100, 100);

        createAccountButton.setLocation(mainTitle.getX(), mainTitle.getY() + mainTitle.getHeight() + 25);
        modifyAccountButton.setLocation(mainTitle.getX() + createAccountButton.getWidth() + 20, createAccountButton.getY());
        createAppointmentButton.setLocation(mainTitle.getX(), createAccountButton.getY() + createAccountButton.getHeight() + 20);
        viewFeedbackButton.setLocation(modifyAccountButton.getX(), modifyAccountButton.getY() + modifyAccountButton.getWidth() + 20);

        System.out.println(settingsButton.getX() + "\t" + settingsButton.getY());
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
        if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to logout from the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new LoginPage();
                frame.dispose();
            }
        } else if (e.getSource() == createAccountButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new CreateAccountPage();
            frame.setVisible(false);
            frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());

        } else if (e.getSource() == createAppointmentButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new BookAppointmentPage();
            frame.setVisible(false);
            frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());

        } else if (e.getSource() == viewFeedbackButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new ViewFeedbackManagerPage();
            frame.setVisible(false);
            frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());

        } else if (e.getSource() == settingsButton) {
            Asset.setFramePosition(frame.getX(), frame.getY());
            new AddItemPage();
            frame.setEnabled(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modifyAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createAppointmentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewFeedbackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        createAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        modifyAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        createAppointmentButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        viewFeedbackButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
