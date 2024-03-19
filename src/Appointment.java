import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static Object[][] createAppointmentTable(String name) {

        ArrayList<Appointment> filterList = new ArrayList<>();

        for (Appointment appointment: overallAppointmentList) {
            if (appointment.technicianID.equals(name)) {
                filterList.add(appointment);
            }
        }

        Object[][] objectInTable = new Object[filterList.size()][];
        int i = 0;
        for (Appointment appointment : filterList) {
            Object[] string = {appointment.appointmentID, appointment.technicianID, appointment.studentTP, appointment.item, appointment.date.toString(), appointment.startingTime.toString(), appointment.endingTime.toString(), appointment.price + "", appointment.paymentStatus};
            objectInTable[i] = string;
            i ++;
        }

        return objectInTable;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public LocalDateTime getAppointmentDateAndTime() {
        return LocalDateTime.of(date, startingTime);
    }

    public static ArrayList<Appointment> getFilteredCustomerList(String tpNumber) {
        TextFileOperations.readAppointment();
        ArrayList<Appointment> filter = new ArrayList<>();
        for (Appointment appointment: overallAppointmentList) {
            if (appointment.studentTP.equals(tpNumber)) {
                filter.add(appointment);
            }
        }
        return filter;
    }
}
