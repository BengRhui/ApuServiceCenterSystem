import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class TextFileOperations {
    private final static String filePath = "textfile/";
    public static void readElectronicsData() {
        ElectronicItems.getFullItemList().clear();
        try {
            String fileName = "electronicItems.txt";
            File file = new File(filePath + fileName);
            if (!file.exists()) {
                throw new IOException();
            } else {
                Scanner input = new Scanner(file);
                String[] line;
                while (input.hasNext()) {
                    line = input.nextLine().split(";");
                    for (int i = 0; i < line.length; i ++) {
                        line[i] = line[i].strip();
                    }
                    new ElectronicItems(line[0], line[1], Integer.parseInt(line[2]));
                }
            }
        } catch (IOException ex) {
            System.out.println("electronicItems.txt is not found. Please inspect readElectronicsData() method.");
        }
    }

    public static void readAppointment() {
        Appointment.getOverallAppointmentList().clear();
        try {
            String fileName = "appointmentList.txt";
            File file = new File(filePath + fileName);
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String[] line = read.nextLine().split(";");
                for (int i = 0; i < line.length; i ++) {
                    line[i] = line[i].strip();
                }
                Appointment newAppointment = new Appointment(line[0], line[1], line[2], line[3], line[4], line[5], line[6], Integer.parseInt(line[7]), line[8]);
                Appointment.setOverallAppointmentList(newAppointment);
            }
        } catch (IOException ex) {
            System.out.println("appointmentList.txt is not found. Please inspect readAppointmentData() method.");
        } catch (NumberFormatException ex) {
            System.out.println("Price of appointmentList.txt cannot be converted to integer. Please inspect appointmentList.txt file.");
        }
    }

    public static void readStudent() {
        Student.getOverallStudentList().clear();
        try {
            String fileName = "studentInformation.txt";
            File file = new File(filePath + fileName);
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String[] student = read.nextLine().split(";");
                int i = 0;
                for (String element: student) {
                    student[i] = element.strip();
                    i ++;
                }
                Student.getOverallStudentList().add(new Student(student[0], student[1], student[2], student[3], student[4]));
            }
        } catch (IOException ex) {
            System.out.println("Error with readStudent() method. Please inspect code.");
        }
    }

    public static void readTechnicianFromFile() {
        Technician.getOverallTechnicianList().clear();
        try {
            String fileName = "technicianDetails.txt";
            File file = new File(filePath + fileName);
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String[] info = read.nextLine().split(";");
                for (int i = 0; i < info.length; i ++) {
                    info[i] = info[i].strip();
                }
                Technician.getOverallTechnicianList().add(new Technician(
                        info[0],
                        info[1],
                        info[2],
                        info[3],
                        info[4],
                        info[5],
                        info[6],
                        info[7],
                        info[8],
                        info[9],
                        info[10],
                        info[11],
                        info[12],
                        info[13])
                );
            }
        } catch (IOException ex) {
            System.out.println("Error with readTechnicianFromFile() method. Please inspect code.");
        }
    }

    public static void writeAppointmentToFile() {
        String fileName = "appointmentList.txt";
        String[] writeList = new String[Appointment.getOverallAppointmentList().size()];
        int i = 0;
        for (Appointment appointment: Appointment.getOverallAppointmentList()) {

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateInString = appointment.date.format(format);

            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
            String startingTimeInString = appointment.startingTime.format(formatTime);
            String endingTimeInString = appointment.endingTime.format(formatTime);

            String line = appointment.appointmentID +
                    ";" +
                    String.format("%-5s", appointment.technicianID) +
                    ";" +
                    String.format("%-10s", appointment.studentTP) +
                    ";" +
                    String.format("%-30s", appointment.item) +
                    ";" +
                    String.format("%-15s", dateInString) +
                    ";" +
                    String.format("%-10s", startingTimeInString) +
                    ";" +
                    String.format("%-10s", endingTimeInString) +
                    ";" +
                    String.format("%-5s", appointment.price) +
                    ";" +
                    (appointment.paymentStatus ? "Paid" : "Unpaid");

            writeList[i] = line;
            i ++;
        }
        try {
            PrintWriter write = new PrintWriter(filePath + fileName);
            for (String line: writeList) {
                write.println(line);
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to appointment list file.");
        }
    }
}
