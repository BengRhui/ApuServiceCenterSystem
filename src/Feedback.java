import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Feedback {

    int systemRating, technicianRating;
    String comment, appointmentID, technicianID, studentTP;

    private final static ArrayList<Feedback> overallFeedbackList = new ArrayList<>();
    public Feedback(String appointmentID, String technicianID, String studentTP, int systemRating, int technicianRating, String comment) {
        this.appointmentID = appointmentID;
        this.technicianID = technicianID;
        this.studentTP = studentTP;
        this.systemRating = systemRating;
        this.technicianRating = technicianRating;
        this.comment = comment;
    }

    public static ArrayList<Feedback> getOverallFeedbackList() {
        return overallFeedbackList;
    }

    public static ArrayList<Feedback> getStudentFilteredFeedbackList(String tpNumber) {

        TextFileOperationsComponent.readFeedbackFromList();
        ArrayList<Feedback> filter = new ArrayList<>();
        for (Feedback feedback: overallFeedbackList) {
            if (feedback.studentTP.equals(tpNumber)) {
                filter.add(feedback);
            }
        }
        return filter;
    }

    public static ArrayList<Feedback> getTechnicianFilteredFeedbackLit(String technicianID) {
        TextFileOperationsComponent.readTechnicianFromFile();
        ArrayList<Feedback> filter = new ArrayList<>();
        for (Feedback list: overallFeedbackList) {
            if (list.technicianID.equals(technicianID)) {
                filter.add(list);
            }
        }
        return filter;
    }
    public LocalDateTime getDateAndTime() {
        return LocalDateTime.of(Appointment.getAppointmentFromID(appointmentID).date, Appointment.getAppointmentFromID(appointmentID).startingTime);
    }

    public static JFrame provideFeedback(Appointment appointmentDetails, Feedback currentFeedback) {
        JFrame frame = new JFrame("Provide feedback");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1000, 700);

        JLabel title = new JLabel("Provide Feedback");
        title.setFont(Asset.getNameFont("Bold"));

        JLabel topPrompt = new JLabel("How would you rate our system:");
        topPrompt.setFont(Asset.getNameFont("Plain"));

        JRadioButton button1 = new JRadioButton("1");
        button1.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button2 = new JRadioButton("2");
        button2.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button3 = new JRadioButton("3");
        button3.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button4 = new JRadioButton("4");
        button4.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button5 = new JRadioButton("5");
        button5.setFont(Asset.getBodyFont("Plain"));


        ButtonGroup group1 = new ButtonGroup();
        group1.add(button1);
        group1.add(button2);
        group1.add(button3);
        group1.add(button4);
        group1.add(button5);

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel1.add(button1);
        buttonPanel1.add(button2);
        buttonPanel1.add(button3);
        buttonPanel1.add(button4);
        buttonPanel1.add(button5);
        buttonPanel1.setBackground(Color.WHITE);
        buttonPanel1.setBounds(500, 125, 400, 45);

        JLabel bottomPrompt = new JLabel("How would you rate our technician:");
        bottomPrompt.setFont(Asset.getNameFont("Plain"));

        JRadioButton button6 = new JRadioButton("1");
        button6.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button7 = new JRadioButton("2");
        button7.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button8 = new JRadioButton("3");
        button8.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button9 = new JRadioButton("4");
        button9.setFont(Asset.getBodyFont("Plain"));
        JRadioButton button10 = new JRadioButton("5");
        button10.setFont(Asset.getBodyFont("Plain"));

        ButtonGroup group2 = new ButtonGroup();
        group2.add(button6);
        group2.add(button7);
        group2.add(button8);
        group2.add(button9);
        group2.add(button10);

        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel2.add(button6);
        buttonPanel2.add(button7);
        buttonPanel2.add(button8);
        buttonPanel2.add(button9);
        buttonPanel2.add(button10);
        buttonPanel2.setBackground(Color.WHITE);
        buttonPanel2.setBounds(500, 200, 400, 45);

        JLabel additional = new JLabel("Additional Feedback");
        additional.setFont(Asset.getNameFont("Plain"));

        JTextArea comment = new JTextArea(5, 20);
        comment.setFont(Asset.getBodyFont("Plain"));
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLayeredPane saveButton = new Asset().generateButtonWithoutImage("Save", 250, 50);

        JLayeredPane cancelButton = new Asset().generateButtonWithoutImage("Cancel", 250, 50);

        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);

        comment.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (comment.getText().length() > 100 || e.getKeyChar() == ';') {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                title.setBounds(frame.getWidth() / 10, frame.getHeight() / 10, frame.getWidth() * 4 / 5, 50);
                topPrompt.setBounds(title.getX(), title.getY() + title.getHeight() + 20, title.getWidth() / 2, title.getHeight());
                buttonPanel1.setBounds(topPrompt.getX() + topPrompt.getWidth(), topPrompt.getY() + 5, topPrompt.getWidth(), topPrompt.getHeight() - 5);
                bottomPrompt.setBounds(topPrompt.getX(), topPrompt.getY() + topPrompt.getHeight() + 20, topPrompt.getWidth(), topPrompt.getHeight());
                buttonPanel2.setBounds(bottomPrompt.getX() + bottomPrompt.getWidth(), bottomPrompt.getY() + 5, buttonPanel1.getWidth(), buttonPanel1.getHeight());
                additional.setBounds(topPrompt.getX(), buttonPanel2.getY() + buttonPanel2.getHeight() + 30, topPrompt.getWidth(), buttonPanel1.getHeight());
                comment.setBounds(additional.getX(), additional.getY() + additional.getHeight() + 20, frame.getWidth() - additional.getX() * 2, frame.getHeight() / 5);
                saveButton.setLocation(comment.getX() + comment.getWidth() - saveButton.getWidth() + 3, comment.getY() + comment.getHeight() + 20);
                cancelButton.setLocation(saveButton.getX() - cancelButton.getWidth() - 20, saveButton.getY());
                background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
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

        frame.add(title);
        frame.add(topPrompt);
        frame.add(buttonPanel1);
        frame.add(bottomPrompt);
        frame.add(buttonPanel2);
        frame.add(additional);
        frame.add(comment);
        frame.add(saveButton);
        frame.add(cancelButton);
        frame.add(background);

        saveButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    int systemRating;
                    if (button1.isSelected()) {
                        systemRating = 1;
                    } else if (button2.isSelected()) {
                        systemRating = 2;
                    } else if (button3.isSelected()) {
                        systemRating = 3;
                    } else if (button4.isSelected()) {
                        systemRating = 4;
                    } else if (button5.isSelected()) {
                        systemRating = 5;
                    } else {
                        throw new NullPointerException();
                    }

                    int technicianRating;
                    if (button6.isSelected()) {
                        technicianRating = 1;
                    } else if (button7.isSelected()) {
                        technicianRating = 2;
                    } else if (button8.isSelected()) {
                        technicianRating = 3;
                    } else if (button9.isSelected()) {
                        technicianRating = 4;
                    } else if (button10.isSelected()) {
                        technicianRating = 5;
                    } else {
                        throw new NullPointerException();
                    }

                    String commentText = comment.getText().strip();
                    if (commentText.isEmpty()) {
                        throw new NullPointerException();
                    }

                    currentFeedback.systemRating = systemRating;
                    currentFeedback.technicianRating = technicianRating;
                    currentFeedback.comment = commentText;
                    TextFileOperationsComponent.writeFeedbackToFile();
                    JOptionPane.showMessageDialog(frame, "Record has been successfully saved.");
                    frame.dispose();

                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all the fields before submitting.");
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, "Your feedback will not be saved. Do you wish to exit now?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    frame.dispose();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.setVisible(true);


        return frame;
    }

}
