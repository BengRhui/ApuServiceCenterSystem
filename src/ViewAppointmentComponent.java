import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewAppointmentComponent {

    private static ArrayList<Appointment> appointmentList;
    private static ArrayList<Feedback> feedbackList;
    public ViewAppointmentComponent(String tpNumber) {
        appointmentList = Appointment.getFilteredCustomerList(tpNumber);
        feedbackList = Feedback.getFilteredFeedbackList(tpNumber);
    }

    public JPanel generateUpcomingAppointment() {

        JPanel overallPanel = new JPanel();
        overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));

        ArrayList<Appointment> futureAppointment = new ArrayList<>();

        for (Appointment list: appointmentList) {
            if (list.date.isAfter(LocalDate.now()) || (list.date.isEqual(LocalDate.now()) && list.startingTime.isAfter(LocalTime.now()))) {
                futureAppointment.add(list);
            }
        }

        futureAppointment.sort(Comparator.comparing(Appointment::getAppointmentDateAndTime));

        int panelHeight = futureAppointment.size() * (200 + 40);

        for (Appointment appointmentDetails: futureAppointment) {

            JPanel detail = new JPanel(new BorderLayout());
            detail.setPreferredSize(new Dimension(300, 200));
            detail.setBackground(Color.BLUE);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy");
            JLabel date = new JLabel(appointmentDetails.date.format(format));
            date.setFont(Asset.getBodyFont("Bold"));
            date.setBounds(30, 20, 250, 30);

            JLabel time = new JLabel(appointmentDetails.startingTime + " - " + appointmentDetails.endingTime);
            time.setFont(Asset.getBodyFont("Plain"));
            time.setBounds(30, 60, 250, 30);

            TextFileOperations.readTechnicianFromFile();
            JLabel technician = new JLabel("(" + Technician.getNameFromID(appointmentDetails.technicianID) + ")");
            technician.setFont(Asset.getBodyFont("Plain"));
            technician.setBounds(30, 100, 250, 30);

            JLabel item = new JLabel("Item: " + appointmentDetails.item);
            item.setFont(Asset.getBodyFont("Plain"));
            item.setBounds(30, 140, 250, 30);

            JPanel container = new JPanel(null);
            container.setPreferredSize(new Dimension(300, 200));
            container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            container.setBackground(Color.WHITE);
            container.add(date);
            container.add(time);
            container.add(technician);
            container.add(item);

            detail.add(container);


            overallPanel.add(detail, BorderLayout.CENTER);
            overallPanel.add(Box.createVerticalStrut(40));

        }

        overallPanel.setPreferredSize(new Dimension(300, panelHeight));

        return overallPanel;
    }

    public JPanel displayPreviousAppointment() {

        JPanel overallPane = new JPanel();

        ArrayList<Appointment> previousAppointment = new ArrayList<>();
        for (Appointment appointment: appointmentList) {
            if (appointment.date.isBefore(LocalDate.now()) || (appointment.date.isEqual(LocalDate.now()) && appointment.startingTime.isBefore(LocalTime.now()))) {
                previousAppointment.add(appointment);
            }
        }

        int rowNumber = (int) Math.round(previousAppointment.size() / 2.0);
        int remainingBox = 0;
        if (rowNumber < 5) {
            rowNumber = 5;
            remainingBox = 10 - previousAppointment.size();
        }

        int height = rowNumber * (80 + 20);

        GridLayout layout = new GridLayout((int) Math.round((previousAppointment.size() + remainingBox) / 2.0), 2);
        layout.setHgap(20);
        layout.setVgap(20);
        overallPane.setLayout(layout);

        for (Appointment appointmentDetails: previousAppointment) {
            JPanel basePanel = new JPanel(new BorderLayout());

            JPanel textPanel = new JPanel(null);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            JLabel date = new JLabel(appointmentDetails.date.format(dateFormatter));
            date.setBounds(20, 10, 250, 30);
            date.setFont(Asset.getBodyFont("Bold"));

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            JLabel startingAndEnding = new JLabel(appointmentDetails.startingTime.format(timeFormatter) + " - " + appointmentDetails.endingTime.format(timeFormatter));
            startingAndEnding.setFont(Asset.getBodyFont("Plain"));
            startingAndEnding.setBounds(20, 40, 250, 30);

            JLabel viewDetails = new Asset().generateImage("viewDetails_icon.png");
            viewDetails.setBounds(240, 25, 30, 30);

            boolean feedbackProvide = false;
            for (Feedback feedback: feedbackList) {
                if (feedback.appointmentID.equals(appointmentDetails.appointmentID)) {
                    feedbackProvide = true;
                    break;
                }
            }

            JLabel giveFeedback;
            if (feedbackProvide) {
                giveFeedback = new Asset().generateImage("provideFeedbackHalf_icon.jpg");
            } else {
                giveFeedback = new Asset().generateImage("provideFeedbackFull_icon.jpg");
            }

            giveFeedback.setBounds(290, 25, 30, 30);

            textPanel.add(date);
            textPanel.add(startingAndEnding);
            textPanel.add(viewDetails);
            textPanel.add(giveFeedback);
            textPanel.setBackground(Color.GRAY);

            basePanel.add(textPanel);
            overallPane.add(basePanel);

        }

        if (remainingBox != 0) {
            for (int i = 0; i < remainingBox; i ++) {
                JPanel empty = new JPanel(new BorderLayout());
                empty.setBackground(Color.BLUE);
                overallPane.add(empty);
            }
        }

        overallPane.setPreferredSize(new Dimension(690, height));
        return overallPane;
    }

}
