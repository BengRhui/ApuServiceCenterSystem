import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class ModifyAccountManagerPage implements ComponentListener, MouseListener, WindowListener {

    static Manager currentManager;
    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, accountText, title, checkInputText;
    JPanel backgroundPanel;
    JScrollPane displayManagerPanel, displayTechnicianPanel, displayCustomerPanel;
    JLayeredPane confirmButton, cancelButton, saveButton, searchButton;
    JComboBox<String> selectAccount;
    InformationPaneComponent infoPane1, infoPane2, infoPane3;
    JTextField checkPrompt;
    Manager searchedManagerAccount;
    Technician searchedTechnicianAccount;
    Student searchedStudentAccount;

    public ModifyAccountManagerPage(Manager manager) {

        currentManager = manager;

        frame = new JFrame("Modify Account Page");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

        title = new JLabel("Modify Account");
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

        cancelButton = new Asset().generateButtonWithoutImage("Cancel", confirmButton.getWidth(), confirmButton.getHeight());

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

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }

                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }

                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                checkInputText = new JLabel("Enter email for checking:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton);

                backgroundPanel.revalidate();
                backgroundPanel.repaint();

                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

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

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
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

                        try {
                            TextFileOperationsComponent.readManagerFromFile();
                            searchedManagerAccount = null;
                            for (Manager manager : Manager.getOverallManagerList()) {
                                if (manager.email.equals(checkPrompt.getText())) {
                                    searchedManagerAccount = manager;
                                    break;
                                }
                            }

                            if (searchedManagerAccount == null) {
                                throw new NullPointerException();
                            }

                            if (checkPrompt.getText().equals(currentManager.email)) {
                                displayManagerPanel = new JScrollPane(infoPane1.modifyManagerAndTechnicianAccountOwn(searchedManagerAccount));
                            } else {
                                displayManagerPanel = new JScrollPane(infoPane1.modifyManagerAndTechnicianAccountNotOwn(searchedManagerAccount, null));
                            }
                            displayManagerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                            displayManagerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                            backgroundPanel.add(displayManagerPanel);
                            displayManagerPanel.setVisible(true);
                            displayManagerPanel.revalidate();
                            displayManagerPanel.repaint();
                            backgroundPanel.addComponentListener(new ComponentListener() {
                                @Override
                                public void componentResized(ComponentEvent e) {
                                    displayManagerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
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

                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that the email is typed correctly.</html>", "Invalid Manager", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
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
                });

                checkPrompt.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            MouseEvent clickEvent = new MouseEvent(searchButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, searchButton.getWidth() / 2, searchButton.getHeight() / 2, 1, false);
                            searchButton.dispatchEvent(clickEvent);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });


            } else if (Objects.equals(selectAccount.getSelectedItem(), "Technician")) {

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }

                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }

                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }

                checkInputText = new JLabel("Enter email for checking:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                checkPrompt.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            MouseEvent clickEvent = new MouseEvent(searchButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, searchButton.getWidth() / 2, searchButton.getHeight() / 2, 1, false);
                            searchButton.dispatchEvent(clickEvent);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton);

                backgroundPanel.revalidate();
                backgroundPanel.repaint();

                searchButton.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);
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

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        InformationPaneComponent.positionChoice.clear();
                        InformationPaneComponent.positionChoice.addAll(Arrays.asList(Technician.getTechnicianPosition()));

                        if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                            backgroundPanel.remove(displayManagerPanel);
                        }

                        if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                            backgroundPanel.remove(displayTechnicianPanel);
                        }
                        if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                            backgroundPanel.remove(displayCustomerPanel);
                        }

                        try {

                            TextFileOperationsComponent.readTechnicianFromFile();

                            searchedTechnicianAccount = null;
                            for (Technician technician : Technician.getOverallTechnicianList()) {
                                if (technician.email.equals(checkPrompt.getText())) {
                                    searchedTechnicianAccount = technician;
                                    break;
                                }
                            }

                            if (searchedTechnicianAccount == null) {
                                throw new NullPointerException();
                            }

                            displayTechnicianPanel = new JScrollPane(infoPane2.modifyManagerAndTechnicianAccountNotOwn(null, searchedTechnicianAccount));

                            displayTechnicianPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                            backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                            backgroundPanel.add(displayTechnicianPanel);
                            displayTechnicianPanel.setVisible(true);
                            displayTechnicianPanel.revalidate();
                            backgroundPanel.addComponentListener(new ComponentListener() {
                                @Override
                                public void componentResized(ComponentEvent e) {
                                    displayTechnicianPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
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
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that the email is typed correctly.</html>", "Invalid Manager", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
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
                });


            } else if (Objects.equals(selectAccount.getSelectedItem(), "Customer")) {

                if (backgroundPanel.getComponentZOrder(checkInputText) > -1) {
                    backgroundPanel.remove(checkInputText);
                }
                if (backgroundPanel.getComponentZOrder(checkPrompt) > -1) {
                    backgroundPanel.remove(checkPrompt);
                }
                if (backgroundPanel.getComponentZOrder(searchButton) > -1) {
                    backgroundPanel.remove(searchButton);
                }

                if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                    backgroundPanel.remove(displayCustomerPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                    backgroundPanel.remove(displayTechnicianPanel);
                }
                if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                    backgroundPanel.remove(displayManagerPanel);
                }

                checkInputText = new JLabel("Enter TP number:");
                checkInputText.setFont(Asset.getNameFont("Plain"));
                checkInputText.setSize(accountText.getWidth(), accountText.getHeight());
                checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);

                checkPrompt = new Asset().generateTextField();
                checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());

                searchButton = new Asset().generateButtonWithoutImage("Search", confirmButton.getWidth() - 3, confirmButton.getHeight() - 3);
                searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);

                checkPrompt.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            MouseEvent clickEvent = new MouseEvent(searchButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, searchButton.getWidth() / 2, searchButton.getHeight() / 2, 1, false);
                            searchButton.dispatchEvent(clickEvent);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                backgroundPanel.add(checkInputText);
                backgroundPanel.add(checkPrompt);
                backgroundPanel.add(searchButton)
                ;
                backgroundPanel.repaint();
                backgroundPanel.revalidate();

                backgroundPanel.addComponentListener(new ComponentListener() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        checkInputText.setLocation(accountText.getX(), accountText.getY() + accountText.getHeight() + 20);
                        checkPrompt.setBounds(selectAccount.getX(), selectAccount.getY() + selectAccount.getHeight() + 20, selectAccount.getWidth(), selectAccount.getHeight());
                        searchButton.setLocation(confirmButton.getX(), checkPrompt.getY() - 3);
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

                searchButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (backgroundPanel.getComponentZOrder(displayManagerPanel) > -1) {
                            backgroundPanel.remove(displayManagerPanel);
                        }

                        if (backgroundPanel.getComponentZOrder(displayTechnicianPanel) > -1) {
                            backgroundPanel.remove(displayTechnicianPanel);
                        }
                        if (backgroundPanel.getComponentZOrder(displayCustomerPanel) > -1) {
                            backgroundPanel.remove(displayCustomerPanel);
                        }

                        try {
                            searchedStudentAccount = null;
                            TextFileOperationsComponent.readStudent();

                            for (Student view : Student.getOverallStudentList()) {
                                if (view.tpNumber.equals(checkPrompt.getText())) {
                                    searchedStudentAccount = view;
                                }
                            }

                            if (searchedStudentAccount == null) {
                                throw new NullPointerException();
                            }

                            displayCustomerPanel = new JScrollPane(infoPane3.customerModifyPersonalInformation(searchedStudentAccount));
                            displayCustomerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
                            backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

                            backgroundPanel.add(displayCustomerPanel);
                            displayCustomerPanel.setVisible(true);
                            displayCustomerPanel.revalidate();

                            backgroundPanel.addComponentListener(new ComponentListener() {
                                @Override
                                public void componentResized(ComponentEvent e) {
                                    displayCustomerPanel.setBounds(title.getX(), checkInputText.getY() + checkInputText.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - checkInputText.getY() - checkInputText.getHeight()) * 9 / 10 - 100);
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
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that the TP number is typed correctly.</html>", "Invalid Manager", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
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
                });
            } else {
                System.out.println("Error with selecting accounts.");
            }
        } else if (e.getSource() == saveButton) {
            if (searchedManagerAccount != null) {

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

                    TextFileOperationsComponent.readManagerFromFile();
                    for (Manager manager : Manager.getOverallManagerList()) {
                        if (!searchedManagerAccount.email.equals(infoPane1.getEmailMPI())) {
                            if (manager.email.equals(infoPane1.getEmailMPI())) {
                                throw new NullPointerException();
                            }
                        }
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician : Technician.getOverallTechnicianList()) {
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
                    } else if (infoPane1.getPasswordMPI() != null) {
                        if (infoPane1.getPasswordMPI().length() < 4 || infoPane1.getPasswordMPI().length() > 20) {
                            throw new NullPointerException();
                        } else if (containUpperCaseCount == 0 || containLowerCaseCount == 0 || containDigitCount == 0) {
                            throw new NullPointerException();
                        } else if (!infoPane1.getPasswordMPI().equals(infoPane1.getReEnterPasswordMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    if (infoPane1.getAccountType().equals("Manager")) {

                        searchedManagerAccount.name = infoPane1.getNameMPI();
                        searchedManagerAccount.gender = infoPane1.getGenderMPI();
                        searchedManagerAccount.maritalStatus = infoPane1.getMaritalStatusMPI();
                        searchedManagerAccount.addressLine1 = infoPane1.getAddressLine1MPI();
                        searchedManagerAccount.addressLine2 = infoPane1.getAddressLine2MPI();
                        searchedManagerAccount.addressLine3 = infoPane1.getAddressLine3MPI();
                        searchedManagerAccount.postcode = infoPane1.getPostcodeMPI();
                        searchedManagerAccount.city = infoPane1.getCityMPI();
                        searchedManagerAccount.state = infoPane1.getStateMPI();
                        searchedManagerAccount.nationality = infoPane1.getNationalityMPI();
                        searchedManagerAccount.email = infoPane1.getEmailMPI();
                        searchedManagerAccount.contactNumber = infoPane1.getContactNoMPI();
                        searchedManagerAccount.position = infoPane1.getPositionMPI();
                        searchedManagerAccount.dateJoined = infoPane1.getDateJoinedMPI();

                        if (infoPane1.getPasswordMPI() != null) {
                            searchedManagerAccount.password = infoPane1.getPasswordMPI();
                        }

                        TextFileOperationsComponent.readManagerFromFile();
                        for (int i = 0; i < Manager.getOverallManagerList().size(); i++) {
                            if (Manager.getOverallManagerList().get(i).managerID.equals(searchedManagerAccount.managerID)) {
                                Manager.getOverallManagerList().set(i, searchedManagerAccount);
                            }
                        }
                        TextFileOperationsComponent.writeManager();

                        JOptionPane.showMessageDialog(frame, "Account modified successful. You will be redirected to the main page.", "Success Modify Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                        Asset.setFramePosition(frame.getX(), frame.getY());
                        TextFileOperationsComponent.readManagerFromFile();
                        for (Manager manager : Manager.getOverallManagerList()) {
                            if (manager.managerID.equals(currentManager.managerID)) {
                                currentManager = manager;
                            }
                        }
                        new ManagerMainPage(currentManager);
                        frame.dispose();

                    } else if (infoPane1.getAccountType().equals("Technician")) {

                        TextFileOperationsComponent.readManagerFromFile();
                        for (Manager account : Manager.getOverallManagerList()) {
                            if (account.managerID.equals(searchedManagerAccount.managerID)) {
                                Manager.getOverallManagerList().remove(account);
                                break;
                            }
                        }
                        TextFileOperationsComponent.writeManager();

                        TextFileOperationsComponent.readTechnicianFromFile();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        Technician technician = new Technician(Technician.generateNewTechnicianID(), infoPane1.getNameMPI(), infoPane1.getGenderMPI(), infoPane1.getMaritalStatusMPI(), infoPane1.getAddressLine1MPI(), infoPane1.getAddressLine2MPI(), infoPane1.getAddressLine3MPI(), infoPane1.getPostcodeMPI(), infoPane1.getCityMPI(), infoPane1.getStateMPI(), infoPane1.getNationalityMPI(), infoPane1.getContactNoMPI(), infoPane1.getDateJoinedMPI().format(dateFormat), infoPane1.getPositionMPI(), infoPane1.getEmailMPI(), infoPane1.getPasswordMPI());
                        Technician.getOverallTechnicianList().add(technician);
                        TextFileOperationsComponent.writeTechnician();

                        JOptionPane.showMessageDialog(frame, "<html>Account modified successful.<br>You will be logged out from the system as you have changed your account to a technician account.</html>", "Success Modify Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                        Asset.setFramePosition(frame.getX(), frame.getY());
                        new LoginPage();
                        frame.dispose();
                    }

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }

            } else if (searchedTechnicianAccount != null) {

                boolean contactNumberValidation = infoPane2.getContactNoMPI().length() >= 11 && infoPane2.getContactNoMPI().length() <= 12;
                for (int i = 0; i < infoPane2.getContactNoMPI().length(); i++) {
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
                            infoPane2.getEmailMPI().isEmpty() || infoPane2.getContactNoMPI().isEmpty()) {
                        throw new NullPointerException();
                    }

                    TextFileOperationsComponent.readManagerFromFile();
                    for (Manager manager : Manager.getOverallManagerList()) {
                        if (manager.email.equals(infoPane2.getEmailMPI())) {
                            throw new NullPointerException();
                        }
                    }

                    TextFileOperationsComponent.readTechnicianFromFile();
                    for (Technician technician : Technician.getOverallTechnicianList()) {
                        if (!searchedTechnicianAccount.email.equals(infoPane2.getEmailMPI())) {
                            if (technician.email.equals(infoPane2.getEmailMPI())) {
                                throw new NullPointerException();
                            }
                        }
                    }

                    if (!contactNumberValidation) {
                        throw new NullPointerException();
                    } else if (infoPane2.getDateJoinedMPI().isAfter(LocalDate.now())) {
                        throw new NullPointerException();
                    } else if (!infoPane2.getEmailMPI().contains("@") || !infoPane2.getEmailMPI().contains(".")) {
                        throw new NullPointerException();
                    }

                    if (infoPane2.getAccountType().equals("Technician")) {

                        searchedTechnicianAccount.name = infoPane2.getNameMPI();
                        searchedTechnicianAccount.gender = infoPane2.getGenderMPI();
                        searchedTechnicianAccount.maritalStatus = infoPane2.getMaritalStatusMPI();
                        searchedTechnicianAccount.addressLine1 = infoPane2.getAddressLine1MPI();
                        searchedTechnicianAccount.addressLine2 = infoPane2.getAddressLine2MPI();
                        searchedTechnicianAccount.addressLine3 = infoPane2.getAddressLine3MPI();
                        searchedTechnicianAccount.postcode = infoPane2.getPostcodeMPI();
                        searchedTechnicianAccount.city = infoPane2.getCityMPI();
                        searchedTechnicianAccount.state = infoPane2.getStateMPI();
                        searchedTechnicianAccount.nationality = infoPane2.getNationalityMPI();
                        searchedTechnicianAccount.email = infoPane2.getEmailMPI();
                        searchedTechnicianAccount.contactNumber = infoPane2.getContactNoMPI();
                        searchedTechnicianAccount.position = infoPane2.getPositionMPI();
                        searchedTechnicianAccount.dateJoined = infoPane2.getDateJoinedMPI();

                        TextFileOperationsComponent.readTechnicianFromFile();
                        for (int i = 0; i < Technician.getOverallTechnicianList().size(); i ++) {
                            if (Technician.getOverallTechnicianList().get(i).technicianID.equals(searchedTechnicianAccount.technicianID)) {
                                Technician.getOverallTechnicianList().set(i, searchedTechnicianAccount);
                            }
                        }
                        TextFileOperationsComponent.writeTechnician();

                    } else if (infoPane2.getAccountType().equals("Manager")) {
                        TextFileOperationsComponent.readTechnicianFromFile();
                        Technician removeTechnician = null;
                        for (Technician technician: Technician.getOverallTechnicianList()) {
                            if (technician.technicianID.equals(searchedTechnicianAccount.technicianID)) {
                               removeTechnician = technician;
                            }
                        }
                        Technician.getOverallTechnicianList().remove(removeTechnician);
                        TextFileOperationsComponent.writeTechnician();

                        TextFileOperationsComponent.readManagerFromFile();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        Manager newManager = new Manager(Manager.generateNewManagerID(), infoPane2.getNameMPI(), infoPane2.getGenderMPI(), infoPane2.getMaritalStatusMPI(), infoPane2.getAddressLine1MPI(), infoPane2.getAddressLine2MPI(), infoPane2.getAddressLine3MPI(), infoPane2.getPostcodeMPI(), infoPane2.getCityMPI(), infoPane2.getStateMPI(), infoPane2.getNationalityMPI(), infoPane2.getContactNoMPI(), infoPane2.getDateJoinedMPI().format(dateFormat), infoPane2.getPositionMPI(), infoPane2.getEmailMPI(), searchedTechnicianAccount.password);
                        Manager.getOverallManagerList().add(newManager);
                        TextFileOperationsComponent.writeManager();
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.<br>Do check your email and password.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }

                JOptionPane.showMessageDialog(frame, "Account modified successful. You will be redirected to the main page.", "Success Modify Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                Asset.setFramePosition(frame.getX(), frame.getY());
                new ManagerMainPage(currentManager);
                frame.dispose();

            } else if (searchedStudentAccount != null) {

                boolean contactNumberValidation = infoPane3.getContactNoSPI().length() >= 11 && infoPane3.getContactNoSPI().length() <= 12;
                for (int i = 0; i < infoPane3.getContactNoSPI().length(); i++) {
                    if (i != 3) {
                        if (!Character.isDigit(infoPane3.getContactNoSPI().charAt(i))) {
                            contactNumberValidation = false;
                        }
                    }
                }

                try {
                    if (infoPane3.getNameSPI().isEmpty() || infoPane3.getGenderSPI().isEmpty() ||
                            infoPane3.getEmailSPI().isEmpty() || infoPane3.getContactNoSPI().isEmpty()) {
                        throw new NullPointerException();
                    }

                    TextFileOperationsComponent.readStudent();
                    for (Student student : Student.getOverallStudentList()) {
                        if (!student.email.equals(searchedStudentAccount.email)) {
                            if (student.email.equals(infoPane3.getEmailSPI())) {
                                throw new NullPointerException();
                            }
                        }
                    }


                    if (!contactNumberValidation) {
                        throw new NullPointerException();
                    } else if (!infoPane3.getEmailSPI().contains("@") || !infoPane3.getEmailSPI().contains(".")) {
                        throw new NullPointerException();
                    }

                    searchedStudentAccount.name = infoPane3.getNameSPI();
                    searchedStudentAccount.email = infoPane3.getEmailSPI();
                    searchedStudentAccount.gender = infoPane3.getGenderSPI();
                    searchedStudentAccount.contactNumber = infoPane3.getContactNoSPI();

                    TextFileOperationsComponent.readStudent();
                    for (int i = 0; i < Student.getOverallStudentList().size(); i ++) {
                        if (searchedStudentAccount.tpNumber.equals(Student.getOverallStudentList().get(i).tpNumber)) {
                            Student.getOverallStudentList().set(i, searchedStudentAccount);
                        }
                    }
                    TextFileOperationsComponent.writeStudent();

                    JOptionPane.showMessageDialog(frame, "Account modified successful. You will be redirected to the main page.", "Success Modify Account", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    new ManagerMainPage(currentManager);
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "<html>Invalid credentials.<br>Please make sure that all information are correctly filled in before you submit.</html>", "Invalid Credentials", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }
            }


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == backArrow || e.getSource() == cancelButton) {
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
