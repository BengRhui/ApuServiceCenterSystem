import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewAppointmentComponent {

    private static ArrayList<Appointment> appointmentList;

    public ViewAppointmentComponent(String tpNumber) {
        appointmentList = Appointment.getFilteredCustomerList(tpNumber);
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

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
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

}
