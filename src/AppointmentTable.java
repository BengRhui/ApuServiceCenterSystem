import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentTable extends JPanel {

    public AppointmentTable(String name) {

        String[] header = {"Appointment ID", "Student TP", "Student Name", "Item", "Date", "Starting time", "Ending Time", "Price", "Payment Status"};

        Object[][] appointmentContent = Appointment.createAppointmentTable();

        JTable table = new JTable(appointmentContent, header);
        table.setFont(Asset.getBodyFont("Plain"));
//        for (int i = 0; i < table.getRowCount(); i ++) {
//            if (i % 2 == 0) {
//
//            }
//        }

        JTableHeader headerTitle = table.getTableHeader();
        headerTitle.setFont(Asset.getBodyFont("Bold"));
        headerTitle.setBackground(Asset.getLightBlue());


        JScrollPane scrollPane = new JScrollPane(table);


        setLayout(null);
        add(scrollPane);

    }

}
