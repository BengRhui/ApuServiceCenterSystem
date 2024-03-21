import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BookAppointmentPage implements ComponentListener, MouseListener, WindowListener, KeyListener {
    public static void main(String[] args) {
        new BookAppointmentPage();
    }

    static JFrame frame;
    JLabel backgroundPicture, backArrow, logoutButton, promptTP, title, tpFront;
    JPanel backgroundPanel;
    JScrollPane fillInfoPane;
    JTextField tpField;
    JLayeredPane searchButton, cancelButton, saveButton;
    static Student currentStudent;
    InformationPaneComponent infoPaneComponent;
    public BookAppointmentPage() {
        frame = new JFrame("Appointment Booking Page");
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

        title = new JLabel("Appointment Booking");
        title.setFont(Asset.getTitleFont());

        promptTP = new JLabel("Enter customer TP number:");
        promptTP.setFont(Asset.getNameFont("Plain"));
        promptTP.setSize(350, 50);

        tpFront = new JLabel("TP");
        tpFront.setFont(Asset.getBodyFont("Plain"));
        tpFront.setSize(30, 30);

        tpField = new JTextField();
        tpField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tpField.setFont(Asset.getBodyFont("Plain"));
        tpField.setHorizontalAlignment(JTextField.CENTER);
        tpField.addKeyListener(this);

        searchButton = new Asset().generateButtonWithoutImage("Search", 150, promptTP.getHeight());
        searchButton.setFocusable(true);

        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentStudent = null;
                try {
                    //Perform first validation: number of characters must be equals to 6
                    int length = tpField.getText().length();
                    if (length != 6) {
                        throw new NumberFormatException();
                    }

                    // Perform second validation: all characters are numbers
                    String tpNumber = "TP" + String.format("%06d", Integer.parseInt(tpField.getText()));
                    TextFileOperationsComponent.readStudent();

                    // Retrieve the corresponding student based on the TP number
                    for (Student student : Student.getOverallStudentList()) {
                        if (student.tpNumber.equals(tpNumber)) {
                            currentStudent = student;
                        }
                    }

                    if (currentStudent != null) {
                        if (fillInfoPane != null) {
                            backgroundPanel.remove(fillInfoPane);
                        }
                        infoPaneComponent = new InformationPaneComponent();
                        fillInfoPane = infoPaneComponent.bookAppointmentPane(currentStudent);
                        fillInfoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                        fillInfoPane.setBounds(title.getX(), promptTP.getY() + promptTP.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - promptTP.getY() - promptTP.getHeight()) * 9 / 10 - 100);
                        backgroundPanel.add(fillInfoPane);
                        backgroundPanel.revalidate();
                        backgroundPanel.repaint();
                        backgroundPanel.addComponentListener(new ComponentListener() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                fillInfoPane.setBounds(title.getX(), promptTP.getY() + promptTP.getHeight() + 30, title.getWidth() + logoutButton.getWidth() - 30, (backgroundPanel.getHeight() - promptTP.getY() - promptTP.getHeight()) * 9 / 10 - 100);
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
                        if (fillInfoPane != null) {
                            backgroundPanel.remove(fillInfoPane);
                            backgroundPanel.repaint();
                            backgroundPanel.revalidate();
                        }
                        JOptionPane.showMessageDialog(frame, "<html>The student has not been registered to the system.<br>Please register the student first before placing appointments.<html>", "Customer Unavailable", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "userNotFound_vector.png"));
                    }

                } catch (NumberFormatException ex) {
                    // Display error message if the TP number does not meet the format
                    JOptionPane.showMessageDialog(frame, "Invalid TP number. Please try again.", "Invalid Input", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        cancelButton = new Asset().generateButtonWithoutImage("Cancel", searchButton.getWidth(), searchButton.getHeight());
        cancelButton.addMouseListener(this);

        saveButton = new Asset().generateButtonWithoutImage("Place appointment", cancelButton.getWidth() + 100, searchButton.getHeight());
        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource() == saveButton) {
                    try {
                        String appointmentChoice = infoPaneComponent.getAppointmentChoiceAD();
                        String serviceItem = infoPaneComponent.getServiceItemListAD();
                        LocalTime appointmentStart = infoPaneComponent.getAppointmentStartAD();
                        LocalTime appointmentEnd = infoPaneComponent.getAppointmentEndAD();
                        LocalDate appointmentDate = infoPaneComponent.getDatePickerAD();

                        if (appointmentChoice.isEmpty() || serviceItem.isEmpty() || appointmentStart == null || appointmentEnd == null || appointmentDate == null) {
                            throw new NullPointerException();
                        } else if (appointmentStart.equals(appointmentEnd) || appointmentEnd.isBefore(appointmentStart)) {
                            throw new NullPointerException();
                        }

                        boolean availability = Appointment.checkAppointmentAvailability(appointmentChoice, appointmentDate, appointmentStart, appointmentEnd);
                        if (!availability) {
                            JOptionPane.showMessageDialog(frame, "<html>The technician is not available at this time.<br>Please try another time slot.</html>", "Slot Unavailable", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                        } else {
                            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
                            Appointment newAppointment = new Appointment(Technician.getIdFromName(appointmentChoice), currentStudent.tpNumber, serviceItem, appointmentDate.format(dateFormat), appointmentStart.format(timeFormat), appointmentEnd.format(timeFormat), ElectronicItems.getPriceFromItem(serviceItem), "Unpaid");
                            Appointment.addAppointmentToFile(newAppointment);
                            Feedback emptyFeedback = new Feedback(newAppointment.appointmentID, newAppointment.technicianID, currentStudent.tpNumber, 0, 0, "null");
                            Feedback.addFeedbackToFile(emptyFeedback);
                            JOptionPane.showMessageDialog(frame, "Register successful. You will be redirected to the main page.", "Success Booking", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "success_icon.png"));
                            Asset.setFramePosition(frame.getX(), frame.getY());
                            ManagerMainPage.setFrameVisibility(true);
                            frame.dispose();
                        }
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(frame, "<html>Please check all the fields before submitting the appointment.<br>Make sure all the fields are filled in.<br>Also, make sure that the time is valid.</html>", "Incomplete Input", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
                        System.out.println(ex.getMessage());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        backgroundPanel.add(backArrow);
        backgroundPanel.add(logoutButton);
        backgroundPanel.add(title);
        backgroundPanel.add(promptTP);
        backgroundPanel.add(tpFront);
        backgroundPanel.add(tpField);
        backgroundPanel.add(searchButton);
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
        promptTP.setLocation(title.getX(), title.getY() + title.getHeight() + 20);
        tpField.setBounds(promptTP.getX() + promptTP.getWidth(), promptTP.getY(), 200, promptTP.getHeight());
        tpFront.setLocation(tpField.getX() + 30, tpField.getY() + (tpField.getHeight() - tpFront.getHeight())/2);
        searchButton.setLocation(tpField.getX() + tpField.getWidth() + 20, tpField.getY() - 3);
        saveButton.setLocation(backgroundPanel.getWidth() - saveButton.getWidth() - 50, backgroundPanel.getHeight() - saveButton.getHeight() - 50);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == tpField && e.getKeyCode() == KeyEvent.VK_ENTER) {
            MouseEvent clickEvent = new MouseEvent(searchButton, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, searchButton.getWidth() / 2, searchButton.getHeight() / 2, 1, false);
            searchButton.dispatchEvent(clickEvent);
        }
    }
}
