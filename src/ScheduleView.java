import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleView extends JLayeredPane implements ComponentListener {

    JLabel title;
    JLayeredPane referencePane;


    public ScheduleView(JLayeredPane referencePane, String technician, String date) {

        this.referencePane = referencePane;
        this.referencePane.addComponentListener(this);

        setSize(referencePane.getWidth(), referencePane.getHeight());
        setLayout(null);

        title = new JLabel("Today's Appointment");
        title.setFont(Asset.getNameFont("Bold"));
        title.setBounds(0, 0, this.getWidth() / 2, 50);

        add(title, DEFAULT_LAYER);

        int counter = 0;
        for (String time: Appointment.getAppointmentTime()) {
            JLabel timeHolder = new JLabel(time);
            timeHolder.setFont(Asset.getBodyFont("Plain"));
            timeHolder.setBounds(0, title.getHeight() + counter, 100, 50);
            JPanel panel = new Asset().drawLine(70, title.getHeight() + timeHolder.getHeight() / 2 + counter, referencePane.getWidth(), title.getHeight() + timeHolder.getHeight() / 2 + counter, 1);
            counter += (this.getHeight() - title.getHeight()) / Appointment.getAppointmentTime().length;
            add(timeHolder, DEFAULT_LAYER);
            add(panel, DEFAULT_LAYER);
        }

        TextFileOperations.readAppointment();

        ArrayList<Appointment> filteredAppointment = new ArrayList<>();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate filterDate = LocalDate.parse(date, dateFormat);
        String technicianName = technician;
        String technicianID = "T001";

        for (Appointment appointment: Appointment.getOverallAppointmentList()) {
            if (appointment.date.isEqual(filterDate) && appointment.technicianID.equals(technicianID)) {
                filteredAppointment.add(appointment);
            }
        }

        int startingTime = 9;
        int scale = counter / Appointment.getAppointmentTime().length;
        int initialPoint = title.getHeight() + 25;
        int width = referencePane.getWidth() - 70;

        for (Appointment appointment: filteredAppointment) {

            int startY = (appointment.startingTime.getHour()  - startingTime) * scale + initialPoint + 1;
            int height = (appointment.endingTime.getHour() - appointment.startingTime.getHour()) * scale - 1;
            JPanel box = new Asset().generateRoundedRectangle(width, height, 3, 1);
            box.setLocation(70, startY);
            String status;
            if (appointment.paymentStatus) {
                status = "Paid";
            } else {
                status = "Unpaid";
            }
            JLabel info = new JLabel(appointment.startingTime + " - " + appointment.endingTime + ": " + appointment.item + " (" + appointment.studentTP + " - " + status + ")");
            info.setBounds(90, startY, width, height - 1);
            info.setFont(Asset.getBodyFont("Plain"));
            add(box, PALETTE_LAYER);
            add(info, MODAL_LAYER);
        }

        addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        title.setBounds(0, 0, this.getWidth() / 2, 50);
        setSize(referencePane.getWidth(), referencePane.getHeight());
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
}
