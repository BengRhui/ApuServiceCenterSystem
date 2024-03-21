import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AppointmentTableComponent extends JPanel implements MouseListener {

    JLayeredPane saveButton;
    JTable table;
    String technicianID;
    public AppointmentTableComponent(String technicianID) {

        this.technicianID = technicianID;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Appointment List");
        title.setFont(Asset.getNameFont("Bold"));
        title.setPreferredSize(new Dimension(getWidth(), 90));
        title.setBackground(Color.WHITE);
        title.setOpaque(true);

        String[] header = {"Appointment ID", "Technician ID", "Student TP", "Item", "Date", "Starting time", "Ending Time", "Price (RM)", "Payment Status"};

        Object[][] appointmentContent = Appointment.createAppointmentTable(technicianID);

        DefaultTableModel model = new DefaultTableModel(appointmentContent, header) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == header.length - 1) {
                    return Boolean.class; // Specify the checkbox column
                }
                return super.getColumnClass(column);
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(40);

        JTableHeader headerTitle = table.getTableHeader();
        headerTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        headerTitle.setBackground(Asset.getLightBlue());
        headerTitle.setPreferredSize(new Dimension(100, 50));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 90));

        saveButton = new Asset().generateButtonWithoutImage("Save Details", 250, 50);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                saveButton.setLocation(getWidth() - saveButton.getWidth() - 3, (bottomPanel.getHeight() - saveButton.getHeight()) / 2);
            }
        });
        saveButton.addMouseListener(this);

        bottomPanel.add(saveButton);
        bottomPanel.setBackground(Color.WHITE);

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == saveButton) {

            Appointment.getOverallAppointmentList().removeIf(appointment -> appointment.technicianID.equals(technicianID));

// (String appointmentID, String technicianID, String studentTP, String item, String date, String startingTime, String endingTime, int price, String status)
// {"Appointment ID", "Technician ID", "Student TP", "Item", "Date", "Starting time", "Ending Time", "Price (RM)", "Payment Status"};
            for (int row = 0; row < table.getRowCount(); row ++) {

                String status;
                if (Boolean.parseBoolean(table.getValueAt(row, 8).toString())) {
                    status = "Paid";
                } else {
                    status = "Unpaid";
                }


                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateInString = LocalDate.parse(table.getValueAt(row, 4).toString()).format(formatDate);

                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                String startingTimeInString = LocalTime.parse(table.getValueAt(row, 5).toString()).format(formatTime);
                String endingTimeInString = LocalTime.parse(table.getValueAt(row, 6).toString()).format(formatTime);

                Appointment.getOverallAppointmentList().add(new Appointment(
                        table.getValueAt(row, 0).toString(),
                        table.getValueAt(row, 1).toString(),
                        table.getValueAt(row, 2).toString(),
                        table.getValueAt(row, 3).toString(),
                        dateInString,
                        startingTimeInString,
                        endingTimeInString,
                        Integer.parseInt(table.getValueAt(row, 7).toString()),
                        status
                ));

                Appointment.getOverallAppointmentList().sort(Comparator.comparing(Appointment::getAppointmentID));
            }

            TextFileOperationsComponent.writeAppointmentToFile();
            JOptionPane.showMessageDialog(null, "All appointment details have been updated successfully.");

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
