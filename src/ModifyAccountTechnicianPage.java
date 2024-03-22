import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

public class ModifyAccountTechnicianPage implements ComponentListener, MouseListener {

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, title;
    JPanel backgroundPanel;
    JScrollPane editPanel;
    JLayeredPane cancelButton, saveButton;
    InformationPaneComponent infoPane1;
    Technician currentTechnician;

    public ModifyAccountTechnicianPage(Technician technician) {

        currentTechnician = technician;

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
        backArrow.addMouseListener(this);

        logoutButton = new Asset().generateImage("logout_icon.png");
        logoutButton.addMouseListener(this);

        title = new JLabel("Modify Account");
        title.setFont(Asset.getTitleFont());

        editPanel = new JScrollPane(infoPane1.modifyAccountTechnician(technician));
        editPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        saveButton = new Asset().generateButtonWithoutImage("Save details", 250, 50);
        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                int containUpperCaseCount = 0, containLowerCaseCount = 0, containDigitCount = 0;
                for (int i = 0; i < infoPane1.getPasswordMPI().length(); i++) {
                    if (Character.isUpperCase(infoPane1.getPasswordMPI().charAt(i))) {
                        containUpperCaseCount += 1;
                    } else if (Character.isLowerCase(infoPane1.getPasswordMPI().charAt(i))) {
                        containLowerCaseCount += 1;
                    } else if (Character.isDigit(infoPane1.getPasswordMPI().charAt(i))) {
                        containDigitCount += 1;
                    }
                }

                boolean contactNumberValidation = infoPane1.getContactNoMPI().length() >= 11 && infoPane1.getContactNoMPI().length() <= 12;
                for (int i = 0; i < infoPane1.getContactNoMPI().length(); i++) {
                    if (i != 3) {
                        if (!Character.isDigit(infoPane1.getContactNoMPI().charAt(i))) {
                            contactNumberValidation = false;
                        }
                    }
                }

                try {
                    if (infoPane1.getNameMPI().isEmpty() || infoPane1.getAddressLine1MPI().isEmpty() ||
                            infoPane1.getAddressLine2MPI().isEmpty() || infoPane1.getAddressLine3MPI().isEmpty() ||
                            infoPane1.getPostcodeMPI().isEmpty() || infoPane1.getCityMPI().isEmpty() ||
                            infoPane1.getStateMPI().isEmpty() || infoPane1.getNationalityMPI().isEmpty() ||
                            infoPane1.getEmailMPI().isEmpty() || infoPane1.getContactNoMPI().isEmpty()) {
                        throw new NullPointerException();
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician : Technician.getOverallTechnicianList()) {
                        if (!currentTechnician.email.equals(infoPane1.getEmailMPI())) {
                            if (technician.email.equals(infoPane1.getEmailMPI())) {
                                throw new NullPointerException();
                            }
                        }
                    }

                    TextFileOperationsComponent.readManagerFromFile();
                    for (Manager manager : Manager.getOverallManagerList()) {
                        if (manager.email.equals(infoPane1.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    if (!contactNumberValidation) {
                        throw new NullPointerException();
                    } else if (!infoPane1.getEmailMPI().contains("@") || !infoPane1.getEmailMPI().contains(".")) {
                        throw new NullPointerException();
                    } else if (!infoPane1.getPasswordMPI().isEmpty()) {
                        if (infoPane1.getPasswordMPI().length() < 4 || infoPane1.getPasswordMPI().length() > 20) {
                            throw new NullPointerException();
                        } else if (containUpperCaseCount == 0 || containLowerCaseCount == 0 || containDigitCount == 0) {
                            throw new NullPointerException();
                        } else if (!infoPane1.getPasswordMPI().equals(infoPane1.getReEnterPasswordMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    currentTechnician.name = infoPane1.getNameMPI();
                    currentTechnician.gender = infoPane1.getGenderMPI();
                    currentTechnician.maritalStatus = infoPane1.getMaritalStatusMPI();
                    currentTechnician.addressLine1 = infoPane1.getAddressLine1MPI();
                    currentTechnician.addressLine2 = infoPane1.getAddressLine2MPI();
                    currentTechnician.addressLine3 = infoPane1.getAddressLine3MPI();
                    currentTechnician.postcode = infoPane1.getPostcodeMPI();
                    currentTechnician.city = infoPane1.getCityMPI();
                    currentTechnician.state = infoPane1.getStateMPI();
                    currentTechnician.nationality = infoPane1.getNationalityMPI();
                    currentTechnician.email = infoPane1.getEmailMPI();
                    currentTechnician.contactNumber = infoPane1.getContactNoMPI();
                    if (!infoPane1.getPasswordMPI().isEmpty()) {
                        currentTechnician.password = infoPane1.getPasswordMPI();
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (int i = 0; i < Technician.getOverallTechnicianList().size(); i++) {
                        if (Technician.getOverallTechnicianList().get(i).technicianID.equals(currentTechnician.technicianID)) {
                            Technician.getOverallTechnicianList().set(i, currentTechnician);
                        }
                    }
                    TextFileOperationsComponent.writeTechnician();

                    JOptionPane.showMessageDialog(frame, "Account modified successful. You will be redirected to the main page.", "Success Modify Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician : Technician.getOverallTechnicianList()) {
                        if (technician.technicianID.equals(currentTechnician.technicianID)) {
                            currentTechnician = technician;
                        }
                    }
                    new TechnicianMainPage(currentTechnician);
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", saveButton.getWidth(), saveButton.getHeight());
        cancelButton.addMouseListener(this);

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == backArrow || e.getSource() == cancelButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Do you wish to return to the main page?<br>Any inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "return_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new TechnicianMainPage(currentTechnician);
                frame.dispose();
            }
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Are you sure that you would like to logout from the system?<br>Any inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
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
}
