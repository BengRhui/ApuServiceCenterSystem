import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class CreateAccountPage implements ComponentListener, MouseListener, WindowListener {
    public static void main(String[] args) {
        currentPage = new CreateAccountPage();
    }

    static CreateAccountPage currentPage;
    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, accountText, title;
    JPanel backgroundPanel;
    JScrollPane displayManagerPanel, displayTechnicianPanel, displayCustomerPanel;
    JLayeredPane confirmButton, cancelButton, saveButton;
    JComboBox<String> selectAccount;
    InformationPaneComponent infoPane1, infoPane2, infoPane3;

    public CreateAccountPage() {

        frame = new JFrame("Create Account Page");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.setLayout(null);
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocation(Asset.getFramePositionX(), Asset.getFramePositionY());
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        infoPane1 = new InformationPaneComponent();
        infoPane2 = new InformationPaneComponent();
        infoPane3 = new InformationPaneComponent();

        backgroundPicture = new Asset().generateImage("background.jpg");

        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        backArrow = new Asset().generateImage("backArrow_icon.png");
        backArrow.addMouseListener(this);

        logoutButton = new Asset().generateImage("logout_icon.png");
        logoutButton.addMouseListener(this);

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

        displayTechnicianPanel = new JScrollPane(new InformationPaneComponent().createManagerAndTechnicianAccount());
        displayCustomerPanel = new JScrollPane(new InformationPaneComponent().customerCreatePersonalInformation());

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", confirmButton.getWidth(), confirmButton.getHeight());
        cancelButton.addMouseListener(this);

        saveButton = new Asset().generateButtonWithoutImage("Save details", cancelButton.getWidth() + 100, confirmButton.getHeight());
        saveButton.addMouseListener(this);

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(accountText);
        backgroundPanel.add(selectAccount);
        backgroundPanel.add(confirmButton);
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
        saveButton.setLocation(backgroundPanel.getWidth() - saveButton.getWidth() - 40, backgroundPanel.getHeight() - saveButton.getHeight() - 40);
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
                InformationPaneComponent.positionChoice.clear();
                InformationPaneComponent.positionChoice.addAll(Arrays.asList(Manager.getManagerPosition()));


                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                displayManagerPanel = new JScrollPane(infoPane1.createManagerAndTechnicianAccount());
                displayManagerPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                backgroundPanel.add(displayManagerPanel);
                displayManagerPanel.setVisible(true);
                displayManagerPanel.revalidate();

                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        displayManagerPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                        displayManagerPanel.revalidate();
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
                });




            } else if (Objects.equals(selectAccount.getSelectedItem(), "Technician")) {
                InformationPaneComponent.positionChoice.clear();
                InformationPaneComponent.positionChoice.addAll(Arrays.asList(Technician.getTechnicianPosition()));

                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                displayTechnicianPanel = new JScrollPane(infoPane2.createManagerAndTechnicianAccount());
                displayTechnicianPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                backgroundPanel.add(displayTechnicianPanel);
                displayTechnicianPanel.setVisible(true);
                displayTechnicianPanel.revalidate();
                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        displayTechnicianPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                        displayTechnicianPanel.revalidate();
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
                });


            } else if (Objects.equals(selectAccount.getSelectedItem(), "Customer")) {

                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }

                displayCustomerPanel = new JScrollPane(infoPane3.customerCreatePersonalInformation());
                displayCustomerPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                backgroundPanel.add(displayCustomerPanel);
                displayCustomerPanel.setVisible(true);
                displayCustomerPanel.revalidate();
                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        displayCustomerPanel.setBounds(title.getX(), accountText.getY() + accountText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - accountText.getY() - accountText.getHeight()) * 9 / 10 - 100);
                        displayCustomerPanel.revalidate();
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
                });

            } else {
                System.out.println("Error with selecting accounts.");
            }
        } else if (e.getSource() == saveButton) {
            if (Objects.equals(selectAccount.getSelectedItem(), "Manager")) {

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

                boolean contactNumberValidation = true;
                if (infoPane1.getContactNoMPI().length() < 11 || infoPane1.getContactNoMPI().length() > 12) {
                    contactNumberValidation = false;
                }
                for (int i = 0; i < infoPane1.getContactNoMPI().length(); i ++) {
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
                            infoPane1.getEmailMPI().isEmpty() || infoPane1.getContactNoMPI().isEmpty() ||
                            infoPane1.getPasswordMPI().isEmpty()) {
                        throw new NullPointerException();
                    }

                    TextFileOperationsComponent.readManagerFromFile();
                    for (Manager manager : Manager.getOverallManagerList()) {
                        if (manager.email.equals(infoPane1.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician: Technician.getOverallTechnicianList()) {
                        if (technician.email.equals(infoPane1.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    if (!contactNumberValidation) {
                        throw new NullPointerException();
                    } else if (infoPane1.getDateJoinedMPI().isAfter(LocalDate.now())) {
                        throw new NullPointerException();
                    } else if (!infoPane1.getEmailMPI().contains("@") || !infoPane1.getEmailMPI().contains(".")) {
                        throw new NullPointerException();
                    } else if (infoPane1.getPasswordMPI().length() < 4 || infoPane1.getPasswordMPI().length() > 20) {
                        throw new NullPointerException();
                    } else if (containUpperCaseCount == 0 || containLowerCaseCount == 0 || containDigitCount == 0) {
                        throw new NullPointerException();
                    } else if (!infoPane1.getPasswordMPI().equals(infoPane1.getReEnterPasswordMPI())) {
                        throw new NullPointerException();
                    }

                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    Manager manager = new Manager(Manager.generateNewManagerID(), infoPane1.getNameMPI(), infoPane1.getGenderMPI(), infoPane1.getMaritalStatusMPI(), infoPane1.getAddressLine1MPI(), infoPane1.getAddressLine2MPI(), infoPane1.getAddressLine3MPI(), infoPane1.getPostcodeMPI(), infoPane1.getCityMPI(), infoPane1.getStateMPI(), infoPane1.getNationalityMPI(), infoPane1.getContactNoMPI(), infoPane1.getDateJoinedMPI().format(dateFormat), infoPane1.getPositionMPI(), infoPane1.getEmailMPI(), infoPane1.getPasswordMPI());
                    Manager.getOverallManagerList().add(manager);

                    TextFileOperationsComponent.writeManager();

                    JOptionPane.showMessageDialog(frame, "Account created successful. You will be redirected to the main page.", "Success Create Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    ManagerMainPage.setFrameVisibility(true);
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.<br>Do check your email and password. The account might have been registered before.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));

                }
            } else if (Objects.equals(selectAccount.getSelectedItem(), "Technician")) {

                int containUpperCaseCount = 0, containLowerCaseCount = 0, containDigitCount = 0;
                for (int i = 0; i < infoPane2.getPasswordMPI().length(); i++) {
                    if (Character.isUpperCase(infoPane2.getPasswordMPI().charAt(i))) {
                        containUpperCaseCount += 1;
                    } else if (Character.isLowerCase(infoPane2.getPasswordMPI().charAt(i))) {
                        containLowerCaseCount += 1;
                    } else if (Character.isDigit(infoPane2.getPasswordMPI().charAt(i))) {
                        containDigitCount += 1;
                    }
                }

                boolean contactNumberValidation = true;
                if (infoPane2.getContactNoMPI().length() < 11 || infoPane2.getContactNoMPI().length() > 12) {
                    contactNumberValidation = false;
                }
                for (int i = 0; i < infoPane2.getContactNoMPI().length(); i ++) {
                    if (i != 3) {
                        if (!Character.isDigit(infoPane2.getContactNoMPI().charAt(i))) {
                            contactNumberValidation = false;
                        }
                    }
                }

                try {
                    if (infoPane2.getNameMPI().isEmpty() || infoPane2.getAddressLine1MPI().isEmpty() ||
                            infoPane2.getAddressLine2MPI().isEmpty() || infoPane2.getAddressLine3MPI().isEmpty() ||
                            infoPane2.getPostcodeMPI().isEmpty() || infoPane2.getCityMPI().isEmpty() ||
                            infoPane2.getStateMPI().isEmpty() || infoPane2.getNationalityMPI().isEmpty() ||
                            infoPane2.getEmailMPI().isEmpty() || infoPane2.getContactNoMPI().isEmpty() ||
                            infoPane2.getPasswordMPI().isEmpty()) {
                        throw new NullPointerException();
                    }


                    TextFileOperationsComponent.readManagerFromFile();
                    for (Technician technician: Technician.getOverallTechnicianList()) {
                        if (technician.email.equals(infoPane2.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician: Technician.getOverallTechnicianList()) {
                        if (technician.email.equals(infoPane2.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    if (!contactNumberValidation) {
                        throw new NullPointerException();
                    } else if (infoPane2.getDateJoinedMPI().isAfter(LocalDate.now())) {
                        throw new NullPointerException();
                    } else if (!infoPane2.getEmailMPI().contains("@") || !infoPane2.getEmailMPI().contains(".")) {
                        throw new NullPointerException();
                    } else if (infoPane2.getPasswordMPI().length() < 4 || infoPane2.getPasswordMPI().length() > 20) {
                        throw new NullPointerException();
                    } else if (containUpperCaseCount == 0 || containLowerCaseCount == 0 || containDigitCount == 0) {
                        throw new NullPointerException();
                    } else if (!infoPane2.getPasswordMPI().equals(infoPane2.getReEnterPasswordMPI())) {
                        throw new NullPointerException();
                    }

                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    Technician technician = new Technician(Technician.generateNewTechnicianID(), infoPane2.getNameMPI(), infoPane2.getGenderMPI(), infoPane2.getMaritalStatusMPI(), infoPane2.getAddressLine1MPI(), infoPane2.getAddressLine2MPI(), infoPane2.getAddressLine3MPI(), infoPane2.getPostcodeMPI(), infoPane2.getCityMPI(), infoPane2.getStateMPI(), infoPane2.getNationalityMPI(), infoPane2.getContactNoMPI(), infoPane2.getDateJoinedMPI().format(dateFormat), infoPane2.getPositionMPI(), infoPane2.getEmailMPI(), infoPane2.getPasswordMPI());
                    Technician.getOverallTechnicianList().add(technician);

                    TextFileOperationsComponent.writeTechnician();

                    JOptionPane.showMessageDialog(frame, "Technician account created successful. You will be redirected to the main page.", "Success Create Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    ManagerMainPage.setFrameVisibility(true);
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.<br>Do check your email and password. The account might have been registered before.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }

            } else if (Objects.equals(selectAccount.getSelectedItem(), "Customer")) {

                boolean tpNumberValidate = true;
                if (!infoPane3.getTpNumberCPI().startsWith("TP")) {
                    tpNumberValidate = false;
                } else if (infoPane3.getTpNumberCPI().length() != 8) {
                    tpNumberValidate = false;
                } else {
                    for (int i = 2; i < infoPane3.getTpNumberCPI().length(); i ++) {
                        if (!Character.isDigit(infoPane3.getTpNumberCPI().charAt(i))) {
                            tpNumberValidate = false;
                        }
                    }
                }

                boolean contactNumberValidation = true;
                if (infoPane3.getContactNoCPI().length() < 11 || infoPane3.getContactNoCPI().length() > 12) {
                    contactNumberValidation = false;
                }
                for (int i = 0; i < infoPane3.getContactNoCPI().length(); i ++) {
                    if (i != 3) {
                        if (!Character.isDigit(infoPane3.getContactNoCPI().charAt(i))) {
                            contactNumberValidation = false;
                        }
                    }
                }

                try {
                    if (infoPane3.getNameCPI().isEmpty() || infoPane3.getTpNumberCPI().isEmpty() ||
                            infoPane3.getContactNoCPI().isEmpty() || infoPane3.getGenderCPI().isEmpty()) {
                        throw new NullPointerException();
                    }

                    TextFileOperationsComponent.readStudent();
                    for (Student student : Student.getOverallStudentList()) {
                        if (student.tpNumber.equals(infoPane3.getTpNumberCPI())) {
                            throw new NullPointerException();
                        }
                    }

                    if (!infoPane3.getEmailCPI().contains("@") || !infoPane3.getEmailCPI().contains(".")) {
                        throw new NullPointerException();
                    } else if (!tpNumberValidate || !contactNumberValidation) {
                        throw new NullPointerException();
                    }

                    Student student = new Student(infoPane3.getTpNumberCPI(), infoPane3.getNameCPI(), infoPane3.getGenderCPI(), infoPane3.getEmailCPI(), infoPane3.getContactNoCPI());
                    Student.getOverallStudentList().add(student);

                    TextFileOperationsComponent.writeStudent();

                    JOptionPane.showMessageDialog(frame, "Account created successful. You will be redirected to the main page.", "Success Create Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    ManagerMainPage.setFrameVisibility(true);
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.<br>Do check the TP number, email and contact number.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }
            }

        } if (e.getSource() == backArrow || e.getSource() == cancelButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Do you wish to return to the main page?<br>The inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "return_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                ManagerMainPage.setFrameVisibility(true);
                frame.dispose();
            }
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Are you sure that you would like to logout from the system?<br>The inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
            if (choice == 0) {
                Asset.setFramePosition(frame.getX(), frame.getY());
                new LoginPage();
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
        backArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backArrow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == frame) {
            int choice = JOptionPane.showConfirmDialog(frame, "<html>Are you sure that you would like to logout from the system?<br>The inputted data will not be saved.</html>", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "logout_icon.png"));
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
