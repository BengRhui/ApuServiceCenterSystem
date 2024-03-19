import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Appointment {

    String appointmentID, technicianID, studentTP, item;
    LocalDate date;
    LocalTime startingTime, endingTime;
    int price;
    boolean paymentStatus;

    private final static String[] appointmentTime = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
    private static final ArrayList<Appointment> overallAppointmentList = new ArrayList<>();

    public static String[] getAppointmentTime() {
        return appointmentTime;
    }

    public Appointment(String appointmentID, String technicianID, String studentTP, String item, String date, String startingTime, String endingTime, int price, String status) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        this.appointmentID = appointmentID;
        this.technicianID = technicianID;
        this.studentTP = studentTP;
        this.item = item;
        this.date = LocalDate.parse(date, dateFormatter);
        this.startingTime = LocalTime.parse(startingTime, timeFormatter);
        this.endingTime = LocalTime.parse(endingTime, timeFormatter);
        this.price = price;
        this.paymentStatus = status.equals("Paid");

    }

    public static void setOverallAppointmentList(Appointment appointment) {
        overallAppointmentList.add(appointment);
    }

    public static ArrayList<Appointment> getOverallAppointmentList() {
        return overallAppointmentList;
    }

    public static Object[][] createAppointmentTable() {

        Object[][] objectInTable = new Object[overallAppointmentList.size()][];
        int i = 0;
        String status;
        for (Appointment appointment : overallAppointmentList) {
            if (appointment.paymentStatus) {
                status = "Pass";
            } else {
                status = "Close";
            }
            String[] string = {appointment.appointmentID, appointment.technicianID, appointment.studentTP, appointment.item, appointment.date.toString(), appointment.startingTime.toString(), appointment.endingTime.toString(), status};
            objectInTable[i] = string;
        }
        return objectInTable;
    }

}
