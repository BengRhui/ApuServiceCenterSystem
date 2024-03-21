import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
* This class is used to initialize the main page of the system.
* Customers (students) will be able to enter their TP number to check for appointments.
* Managers and technicians will click on the text to be redirected to the login page.
* */
public class InitialMainPage implements KeyListener, ComponentListener, MouseListener, WindowListener {
    // All data declared
    static JFrame frame;
    JLabel backgroundImage, mainTitle, mainContent, subContent, placeholderText, repairImage, providerImage, providerText, callImage, callText, searchImage,
            personnelImage, personnelText, locationImage, locationText, subHeading, tpImage, predefinedText, closeButton;

    JPanel backgroundPanel, textFrame, buttonBackground, frontBackground;
    JTextField tpInputField;
    JLayeredPane buttonPane, inputPane;
    static Student currentStudent;

    // Constructor to initialize the GUI component of the system main page
    public InitialMainPage(){

        // Declaring the frame with its properties
        frame = new JFrame("AHHASC Main Page");
        frame.setSize(Asset.getFrameWidth(), Asset.getFrameHeight());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addComponentListener(this);
        frame.addWindowListener(this);

        // Generate a background image for the system
        backgroundImage = new Asset().generateImage("background_1.jpg");
        backgroundImage.setLocation(0,0);

        // Create a white blank space to accommodate all the components that will be displayed
        backgroundPanel = new JPanel(null);
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Main title for the page
        mainTitle = new JLabel("APU Hostel Home Appliances Service Center");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 40));

        // Text to indicate the information that the customer should fill in
        mainContent = new JLabel("Check your appointment here:");
        mainContent.setFont(Asset.getNameFont("Plain"));

        subContent = new JLabel("If you previously had one with us.");
        subContent.setFont(Asset.getBodyFont("Italic"));

        // Create a text box for customers to enter their TP number
        inputPane = new JLayeredPane();

        textFrame = new Asset().generateRoundedRectangle(400, 80, 10, 2);

        tpImage = new Asset().generateImage("tpCard_icon.png");
        tpImage.setLocation(35, 15);

        predefinedText = new JLabel("TP");
        predefinedText.setFont(Asset.getBodyFont("Plain"));

        placeholderText = new JLabel("Enter TP Number");
        placeholderText.setForeground(Color.GRAY);
        placeholderText.setFont(Asset.getBodyFont("Plain"));
        placeholderText.setLocation(130, 0);

        tpInputField = new JTextField();
        tpInputField.setFont(Asset.getBodyFont("Plain"));
        tpInputField.setBackground(Asset.getTransparentColour());
        tpInputField.setBorder(null);
        tpInputField.addKeyListener(this);

        inputPane.setSize(textFrame.getWidth(), textFrame.getHeight());
        inputPane.add(textFrame, JLayeredPane.DEFAULT_LAYER);
        inputPane.add(tpImage, JLayeredPane.PALETTE_LAYER);
        inputPane.add(placeholderText, JLayeredPane.PALETTE_LAYER);
        inputPane.add(predefinedText, JLayeredPane.MODAL_LAYER);
        inputPane.add(tpInputField, JLayeredPane.MODAL_LAYER);

        // Create a search button for customers to search for their appointments based on TP number
        buttonPane = new JLayeredPane();

        buttonBackground = new JPanel();
        buttonBackground.setBackground(Color.WHITE);
        buttonBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        frontBackground = new JPanel();
        frontBackground.setBackground(Color.BLACK);

        searchImage = new Asset().generateImage("search_icon.png");

        buttonPane.add(buttonBackground, JLayeredPane.DEFAULT_LAYER);
        buttonPane.add(frontBackground, JLayeredPane.PALETTE_LAYER);
        buttonPane.add(searchImage, JLayeredPane.MODAL_LAYER);
        buttonPane.addMouseListener(this);

        // Subheading for the main page.
        subHeading = new JLabel("<html>May it be booking appointments or answering inquires, do reach out to us if you require any assistance!</html>");
        subHeading.setFont(Asset.getNameFont("Bold"));

        // Detailed information of our system to the customer
        providerImage = new Asset().generateImage("providedService_icon.jpg");

        providerText = new JLabel("<html><u>We can fix:</u><br><ul><li>Electric kettle</li><li>Iron/ Steamer</li><li>Bedside lamp</li><li>Hair Dryer</li></ul>and many more!</html>");
        providerText.setFont(Asset.getBodyFont("Plain"));

        callImage = new Asset().generateImage("call_icon.png");

        callText = new JLabel("<html><u>Person-in-charge</u><br>Ms I Wee (012-345 6789)<br>Mr Teong (018-765 4321)</html>");
        callText.setFont(Asset.getBodyFont("Plain"));

        locationImage = new Asset ().generateImage("location_icon.png");

        locationText = new JLabel("<html><u>Location</u><br>Accomodation Office,<br>Level 3, APU</html>");
        locationText.setFont(Asset.getBodyFont("Plain"));

        personnelImage = new Asset().generateImage("personnel_icon.png");
        personnelImage.setSize(60, 40);
        personnelImage.addMouseListener(this);

        personnelText = new JLabel("<html>Personnel of AHHASC?<br>Click here to login to the system.</html>");
        personnelText.setFont(Asset.getBodyFont("Italic"));
        personnelText.addMouseListener(this);

        // A picture to demonstrate the main service provided
        repairImage = new Asset().generateImage("repair_vector.png");

        // A close button to exit from the system
        closeButton = new Asset().generateImage("close_icon.png");
        closeButton.addMouseListener(this);

        // Adding all the GUI components to the background panel
        backgroundPanel.add(closeButton);
        backgroundPanel.add(mainTitle);
        backgroundPanel.add(mainContent);
        backgroundPanel.add(inputPane);
        backgroundPanel.add(subContent);
        backgroundPanel.add(subHeading);
        backgroundPanel.add(repairImage);
        backgroundPanel.add(providerImage);
        backgroundPanel.add(providerText);
        backgroundPanel.add(callImage);
        backgroundPanel.add(callText);
        backgroundPanel.add(locationImage);
        backgroundPanel.add(locationText);
        backgroundPanel.add(personnelImage);
        backgroundPanel.add(personnelText);
        backgroundPanel.add(buttonPane);

        // Adding the panel and the background image to the frame
        frame.add(backgroundPanel);
        frame.add(backgroundImage);

        // Display the page to everyone using the system
        frame.setVisible(true);
    }

    // Used to set the visibility of the current frame
    public static void setFrameVisible(boolean status) {
        frame.setVisible(status);
    }

    // Used to adjust the position and size of the GUI elements when the frame is resized.
    @Override
    public void componentResized(ComponentEvent e) {
        backgroundImage.setSize(frame.getWidth(),frame.getHeight());
        backgroundPanel.setSize(frame.getWidth() * 9 / 10, frame.getHeight() * 9 / 10 - 30);
        backgroundPanel.setLocation((frame.getWidth() - backgroundPanel.getWidth()) /2, (frame.getHeight() - backgroundPanel.getHeight()) / 2 - 15);
        mainTitle.setLocation(backgroundPanel.getWidth() / 20,backgroundPanel.getHeight() / 20);
        mainTitle.setSize(backgroundPanel.getWidth() - mainTitle.getX(), 100);
        mainContent.setBounds(mainTitle.getX(),backgroundPanel.getHeight() * 4 / 20,backgroundPanel.getWidth() / 3,40);
        subContent.setBounds(mainTitle.getX(),backgroundPanel.getHeight() * 5 / 20,backgroundPanel.getWidth() / 3,40);
        inputPane.setLocation(backgroundPanel.getWidth() * 2 / 5, mainContent.getY());
        predefinedText.setBounds(tpImage.getX() + tpImage.getWidth() + 20, 0, 30, inputPane.getHeight());
        placeholderText.setSize(inputPane.getWidth() - placeholderText.getX(), inputPane.getHeight());
        tpInputField.setBounds(placeholderText.getX(), placeholderText.getY(), placeholderText.getWidth() - 20, placeholderText.getHeight());
        buttonPane.setBounds(inputPane.getX() + inputPane.getWidth() + 30,inputPane.getY(),inputPane.getHeight(),inputPane.getHeight());
        buttonPane.setBounds(buttonPane.getX(), buttonPane.getY(), buttonPane.getWidth(), buttonPane.getHeight());
        buttonBackground.setBounds(3, 3, inputPane.getHeight() - 6, inputPane.getHeight() - 6);
        frontBackground.setBounds(3, 3, buttonBackground.getWidth(), buttonBackground.getHeight());
        searchImage.setLocation((buttonBackground.getWidth() - searchImage.getWidth()) / 2, (buttonPane.getHeight() - searchImage.getHeight()) / 2);
        subHeading.setBounds(mainTitle.getX(),backgroundPanel.getHeight() * 3 / 10,backgroundPanel.getWidth() - mainTitle.getX() - 50,200);
        providerImage.setLocation(mainTitle.getX(),backgroundPanel.getHeight() / 2);
        providerText.setBounds(providerImage.getX() + providerImage.getWidth() + 20, providerImage.getY(),backgroundPanel.getWidth() / 4,175);
        callImage.setLocation(providerText.getX() + providerText.getWidth() + 10, providerImage.getY());
        callText.setBounds(callImage.getX() + callImage.getWidth() + 20, callImage.getY(), providerText.getWidth(), 75);
        locationImage.setLocation(callImage.getX(), callText.getY() + callText.getHeight() + 50);
        locationText.setBounds(callText.getX(), locationImage.getY(), callText.getWidth(), 75);
        personnelImage.setLocation(providerImage.getX(), backgroundPanel.getHeight() - 100);
        personnelText.setBounds(personnelImage.getX() + personnelImage.getWidth(), personnelImage.getY() - 5, 300, 50);
        repairImage.setLocation(backgroundPanel.getWidth() * 5 / 8, backgroundPanel.getHeight() * 22 / 50);
        closeButton.setLocation(backgroundPanel.getWidth() - closeButton.getWidth() - 50, 50);
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            MouseEvent clickEvent = new MouseEvent(buttonPane, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, buttonPane.getWidth() / 2, buttonPane.getHeight() / 2, 1, false);
            buttonPane.dispatchEvent(clickEvent);
        }
    }

    // Used to hide the text placeholder while typing password
    @Override
    public void keyReleased(KeyEvent e) {
        placeholderText.setVisible(tpInputField.getText().isEmpty());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // When the search button is clicked
        if (e.getSource() == buttonPane) {
            currentStudent = null;
            try {
                //Perform first validation: number of characters must be equals to 6
                int length = tpInputField.getText().length();
                if (length != 6) {
                    throw new NumberFormatException();
                }

                // Perform second validation: all characters are numbers
                String tpNumber = "TP" + String.format("%06d", Integer.parseInt(tpInputField.getText()));
                TextFileOperationsComponent.readStudent();

                // Retrieve the corresponding student based on the TP number
                for (Student student: Student.getOverallStudentList()) {
                    if (student.tpNumber.equals(tpNumber)) {
                        currentStudent = student;
                    }
                }

                // Proceed if the TP number of the student is in the system, else tell user that the customer is not registered
                if (currentStudent != null) {
                    Asset.setFramePosition(frame.getX(), frame.getY());
                    new CustomerAppointmentPage(currentStudent);
                    setFrameVisible(false);
                } else {
                    JOptionPane.showMessageDialog(frame, "<html>You have not been registered to the system.<br>Please contact the person-in-charge for appointment bookings.<html>", "Customer Unavailable", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "userNotFound_vector.png"));
                }

            } catch (NumberFormatException ex) {
                // Display error message if the TP number does not meet the format
                JOptionPane.showMessageDialog(frame, "Invalid TP number. Please try again.", "Invalid Input", JOptionPane.ERROR_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "warning_icon.png"));
            }
        } else if (e.getSource() == personnelImage || e.getSource() == personnelText) {

            // Link to the login page when the personnel image and text is clicked
            Asset.setFramePosition(frame.getX(), frame.getY());
            new LoginPage();
            setFrameVisible(false);

        } else if (e.getSource() == closeButton) {

            // Confirm that user wishes to exit, the end the overall system if the user intends to do so
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to exit the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "exitConfirm_vector.png"));
            if (choice == 0) {
                frame.dispose();
                System.exit(0);
            }
        }
    }

    // Set mouse cursor when we hover through the buttons
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == closeButton) {
            closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        } else if (e.getSource() == buttonPane) {
            buttonPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            frontBackground.setLocation(6, 0);
            searchImage.setLocation((frontBackground.getWidth() - searchImage.getWidth()) / 2 + 3, (frontBackground.getHeight() - searchImage.getHeight()) / 2 - 3);

        } else if (e.getSource() == personnelText || e.getSource() == personnelImage) {
            personnelText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            personnelImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == buttonPane) {
            buttonPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            frontBackground.setLocation(3, 3);
            searchImage.setLocation((frontBackground.getWidth() - searchImage.getWidth()) / 2, (frontBackground.getHeight() - searchImage.getHeight()) / 2);

        } else if (e.getSource() == personnelImage || e.getSource() == personnelText) {
            personnelText.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            personnelImage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        } else if (e.getSource() == closeButton) {
            closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == frame) {

            // Confirm that user wishes to exit, the end the overall system if the user intends to do so
            int choice = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to exit the system?", "System Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(TextFileOperationsComponent.getPictureFilePath() + "exitConfirm_vector.png"));
            if (choice == 0) {
                frame.dispose();
                System.exit(0);
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


