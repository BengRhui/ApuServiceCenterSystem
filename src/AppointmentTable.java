import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentTable extends JPanel {

    public AppointmentTable(String name) {

        String[] header = {"Appointment ID", "Student TP", "Student Name", "Item", "Date", "Starting time", "Ending Time", "Price", "Payment Status"};

        JTable table = new JTable();


        JScrollPane scrollPane = new JScrollPane(table);


        setLayout(null);
        add(scrollPane);

    }

}
