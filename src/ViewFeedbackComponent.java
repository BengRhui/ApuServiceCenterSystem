import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewFeedbackComponent {

    public JPanel managerOverallReview() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel systemPanel = generateSystemPanel();
        systemPanel.setPreferredSize(new Dimension(440, 220));
        systemPanel.setBackground(new Color(255, 235, 190));
        systemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel technicianPanel = generateTechnicianPanel(null);
        technicianPanel.setPreferredSize(new Dimension(440, 220));
        technicianPanel.setBackground(new Color(255, 210, 190));
        technicianPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(440, 30));
        emptyPanel.setBackground(Color.WHITE);

        panel.add(systemPanel);
        panel.add(emptyPanel);
        panel.add(technicianPanel);

        return panel;

    }

    public JPanel technicianOverallView(String technicianID) {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel technicianPanel = generateTechnicianPanel(technicianID);
        technicianPanel.setPreferredSize(new Dimension(440, 480));
        technicianPanel.setBackground(new Color(255, 235, 190));
        technicianPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        panel.add(technicianPanel);

        return panel;

    }

    public JPanel generateSystemPanel() {

        JPanel panel = new JPanel(null);

        JLabel picture = new Asset().generateImage("feedbackSystem_icon.png");
        picture.setLocation(30, 20);

        TextFileOperationsComponent.readFeedbackFromList();
        ArrayList<Feedback> feedbackList = Feedback.getOverallFeedbackList();
        double systemSum = 0;
        double systemCount = 0;
        for (Feedback feedback: feedbackList) {
            systemSum += feedback.systemRating;
            systemCount += 1;
        }

        JLabel systemOverallScore = new JLabel(String.format("%.2f", systemSum / systemCount));
        systemOverallScore.setFont(Asset.getTitleFont());
        systemOverallScore.setBounds(picture.getX() + picture.getWidth() + 40, picture.getY() + 20, 150, 50);

        JLabel systemText = new JLabel("<html>is the overall<br>ratings for the system.</html>");
        systemText.setFont(Asset.getBodyFont("Plain"));
        systemText.setBounds(systemOverallScore.getX(), systemOverallScore.getY() + systemOverallScore.getHeight(), systemOverallScore.getWidth(), 100);

        panel.add(picture);
        panel.add(systemOverallScore);
        panel.add(systemText);

        return panel;

    }

    public JPanel generateTechnicianPanel(String technicianID) {

        JPanel panel = new JPanel(null);

        JLabel picture = new Asset().generateImage("feedbackSatisfaction_icon.png");

        if (technicianID == null) {
            picture.setLocation(30, 20);
        } else {
            picture.setLocation((440 - picture.getWidth()) / 2, 50);
        }

        TextFileOperationsComponent.readFeedbackFromList();

        ArrayList<Feedback> feedbackList;
        if (technicianID == null) {
            feedbackList = Feedback.getOverallFeedbackList();
        } else {
            feedbackList = Feedback.getTechnicianFilteredFeedbackLit(technicianID);
        }

        double technicianSum = 0, technicianCount = 0;
        for (Feedback feedback: feedbackList) {
            technicianSum += feedback.technicianRating;
            technicianCount += 1;
        }

        JLabel systemOverallScore = new JLabel();
        if (technicianCount == 0) {
            systemOverallScore.setText("Null");
        } else {
            systemOverallScore.setText(String.format("%.2f", technicianSum / technicianCount));
        }
        systemOverallScore.setFont(Asset.getTitleFont());
        systemOverallScore.setSize(150, 50);

        JLabel systemText = new JLabel();
        if (technicianID == null) {
            systemOverallScore.setLocation(picture.getX() + picture.getWidth() + 40, picture.getY() + 20);
            systemText.setText("<html>is the overall ratings for technicians.</html>");
            systemText.setBounds(systemOverallScore.getX(), systemOverallScore.getY() + systemOverallScore.getHeight(), systemOverallScore.getWidth(), 100);
        } else {
            systemOverallScore.setLocation((440 - systemOverallScore.getWidth())/2 + 15, picture.getY() + picture.getHeight() + 50);
            systemText.setText("<html><div style='text-align: center;'>is the overall ratings for your service.</div></html>");
            systemText.setSize(240, 100);
            systemText.setLocation(100, systemOverallScore.getY() + systemOverallScore.getHeight());
        }
        systemText.setFont(Asset.getBodyFont("Plain"));

        panel.add(picture);
        panel.add(systemOverallScore);
        panel.add(systemText);

        return panel;

    }


    public JPanel individualReview(String technicianID) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        TextFileOperationsComponent.readFeedbackFromList();
        TextFileOperationsComponent.readAppointment();

        ArrayList<Feedback> filteredList = new ArrayList<>();

        for (Feedback feedback : Feedback.getOverallFeedbackList()) {
            if (technicianID == null) {
                if (feedback.comment != null) {
                    filteredList.add(feedback);
                }
            } else {
                if (feedback.technicianID.equals(technicianID) && feedback.comment != null) {
                    filteredList.add(feedback);
                }
            }
        }


        filteredList.sort(Comparator.comparing(Feedback::getDateAndTime));

        ArrayList<Feedback> revertedList = new ArrayList<>();
        int listSize = filteredList.size();
        for (int i = 0; i < listSize; i ++) {
            revertedList.add(filteredList.getLast());
            filteredList.removeLast();
        }

        int panelHeight = revertedList.size() * (300 + 20);

        if (revertedList.isEmpty()) {
            JLabel layer = new JLabel("No comments provided.");
            layer.setFont(Asset.getNameFont("Plain"));
            panel.add(layer);
        }

        for (Feedback feedback: revertedList) {

            Font textFont = new Font("Arial", Font.PLAIN, 18);

            JPanel layer = new JPanel(null);
            layer.setBackground(Color.WHITE);
            layer.setPreferredSize(new Dimension(420, 300));
            layer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            if (technicianID == null) {
                JLabel systemRating = new JLabel("System ratings: " + feedback.systemRating + " out of 5");
                systemRating.setFont(textFont);
                systemRating.setBounds(30, 20, 250, 30);

                TextFileOperationsComponent.readTechnicianFromFile();
                JLabel technicianInCharge = new JLabel("Technician in charge: " + Technician.getNameFromID(feedback.technicianID));
                technicianInCharge.setFont(textFont);
                technicianInCharge.setBounds(30, 80, 400, 30);

                layer.add(systemRating);
                layer.add(technicianInCharge);
            }

            JLabel technicianRating = new JLabel();
            technicianRating.setFont(textFont);
            technicianRating.setSize(300, 30);
            if (technicianID == null) {
                technicianRating.setText("Technician ratings: " + feedback.technicianRating + " out of 5");
                technicianRating.setLocation(30, 50);
            } else {
                technicianRating.setText("Service ratings: " + feedback.technicianRating + " out of 5");
                technicianRating.setLocation(30, 20);
            }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            JLabel date = new JLabel("Service date: " + feedback.getDateAndTime().format(formatter));
            date.setFont(textFont);
            date.setSize(400, 30);
            if (technicianID == null) {
                date.setLocation(30, 110);
            } else {
                date.setLocation(30, 50);
            }

            if (technicianID != null) {
                JLabel item = new JLabel("Item: " + Appointment.getAppointmentFromID(feedback.appointmentID).item);
                item.setFont(textFont);
                item.setBounds(30, 80, 300, 30);
                layer.add(item);
            }

            JLabel feedbackTitle = new JLabel("<html><u>Feedback</u></html>");
            feedbackTitle.setFont(textFont);
            feedbackTitle.setSize(200, 30);

            JLabel feedbackContent = new JLabel("<html>"+ feedback.comment + "</html>");
            feedbackContent.setFont(textFont);

            JPanel feedbackLayer = new JPanel();
            feedbackLayer.setLayout(new BoxLayout(feedbackLayer, BoxLayout.PAGE_AXIS));
            feedbackLayer.setSize(360, 90);
            feedbackLayer.setBackground(Color.WHITE);
            feedbackLayer.add(feedbackContent);

            if (technicianID == null) {
                feedbackTitle.setLocation(30, 160);
                feedbackLayer.setLocation(30, 200);
            } else {
                feedbackTitle.setLocation(30, 150);
                feedbackLayer.setLocation(30, 190);
            }

            layer.add(technicianRating);
            layer.add(date);
            layer.add(feedbackTitle);
            layer.add(feedbackLayer);

            panel.add(layer);
            panel.add(Box.createVerticalStrut(20));
        }

        panel.setPreferredSize(new Dimension(420, panelHeight));
        return panel;
    }
}
