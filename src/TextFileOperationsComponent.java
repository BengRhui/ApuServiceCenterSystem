import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TextFileOperationsComponent {
    private final static String filePath = "textfile/";
    private final static String picPath = "asset/";

    public static String getTextFilePath() {
        return filePath;
    }

    public static String getPictureFilePath() {
        return picPath;
    }

    public static void readManagerFromFile() {
        Manager.getOverallManagerList().clear();
        try {
            File file = new File(filePath + "managerDetails.txt");
            Scanner read = new Scanner(file);
            while (read.hasNext()) {
                String[] line = read.nextLine().split(";");
                for (int i = 0; i < line.length; i ++) {
                    line[i] = line[i].strip();
                }
                Manager manager = new Manager(line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13], line[14], line[15]);
                Manager.getOverallManagerList().add(manager);
            }
        } catch (IOException ex) {
            System.out.println("managerDetails.txt is not found. Please inspect readManagerFromFile() method.");
        }
    }

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
                    ElectronicItems item = new ElectronicItems(line[1], Integer.parseInt(line[2]));
                    ElectronicItems.getFullItemList().add(item);
                }
                input.close();
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
                Appointment newAppointment = new Appointment(line[1], line[2], line[3], line[4], line[5], line[6], Double.parseDouble(line[7]), line[8]);
                Appointment.setOverallAppointmentList(newAppointment);
            }
            read.close();
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
            read.close();
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
                        info[13],
                        info[14],
                        info[15])
                );
            }
            read.close();
        } catch (IOException ex) {
            System.out.println("Error with readTechnicianFromFile() method. Please inspect code.");
        }
    }

    public static void readFeedbackFromList() {
        Feedback.getOverallFeedbackList().clear();
        String fileName = "feedbackList.txt";
        try {
            File file = new File(filePath + fileName);
            Scanner read = new Scanner(file);
            readAppointment();
            while (read.hasNext()) {
                String[] line = read.nextLine().split(";");
                for (int i = 0; i < line.length; i ++) {
                    line[i] = line[i].strip();
                }
                String comment = line[5];
                if (line[5].equals("null")) {
                    comment = null;
                }
                Feedback feedback = new Feedback(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), comment);
                Feedback.getOverallFeedbackList().add(feedback);
            }
        } catch (IOException ex) {
            System.out.println("Error with reading from feedback list file. Please inspect code");
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
                    String.format("%-10.2f", appointment.price) +
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

    public static void writeFeedbackToFile() {
        try {
            String fileName = "feedbackList.txt";
            PrintWriter write = new PrintWriter(filePath + fileName);

            ArrayList<Feedback> list = Feedback.getOverallFeedbackList();

            for (Feedback feedback: list) {
                String lineToWrite = String.format("%-5s", feedback.appointmentID) + ";"
                        + String.format("%-5s", feedback.technicianID) + ";"
                        + String.format("%-10s", feedback.studentTP) + ";"
                        + String.format("%-5s", feedback.systemRating) + ";"
                        + String.format("%-5s", feedback.technicianRating) + ";"
                        + feedback.comment;
                write.println(lineToWrite);
            }

            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to feedback list file.");
        }
    }

    public static void writeManager() {
        try {
            String fileName = "managerDetails.txt";
            File file = new File(filePath + fileName);
            PrintWriter write = new PrintWriter(file);

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Manager manager: Manager.getOverallManagerList()) {
                String line = String.format("%-5s", manager.managerID) + ";"
                        + String.format("%-30s", manager.name) + ";"
                        + String.format("%-20s", manager.gender) + ";"
                        + String.format("%-15s", manager.maritalStatus) + ";"
                        + String.format("%-40s", manager.addressLine1) + ";"
                        + String.format("%-40s", manager.addressLine2) + ";"
                        + String.format("%-40s", manager.addressLine3) + ";"
                        + String.format("%-10s", manager.postcode) + ";"
                        + String.format("%-20s", manager.city) + ";"
                        + String.format("%-20s", manager.state) + ";"
                        + String.format("%-30s", manager.nationality) + ";"
                        + String.format("%-15s", manager.contactNumber) + ";"
                        + String.format("%-15s", manager.dateJoined.format(dateFormat)) + ";"
                        + String.format("%-30s", manager.position) + ";"
                        + String.format("%-40s", manager.email) + ";"
                        + manager.password;
                write.println(line);
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to manager list file.");
        }
    }


    public static void writeTechnician() {
        try {
            String fileName = "technicianDetails.txt";
            File file = new File(filePath + fileName);
            PrintWriter write = new PrintWriter(file);

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (Technician technician: Technician.getOverallTechnicianList()) {
                String line = String.format("%-5s", technician.technicianID) + ";"
                        + String.format("%-30s", technician.name) + ";"
                        + String.format("%-20s", technician.gender) + ";"
                        + String.format("%-15s", technician.maritalStatus) + ";"
                        + String.format("%-40s", technician.addressLine1) + ";"
                        + String.format("%-40s", technician.addressLine2) + ";"
                        + String.format("%-40s", technician.addressLine3) + ";"
                        + String.format("%-10s", technician.postcode) + ";"
                        + String.format("%-20s", technician.city) + ";"
                        + String.format("%-20s", technician.state) + ";"
                        + String.format("%-30s", technician.nationality) + ";"
                        + String.format("%-15s", technician.contactNumber) + ";"
                        + String.format("%-15s", technician.dateJoined.format(dateFormat)) + ";"
                        + String.format("%-30s", technician.position) + ";"
                        + String.format("%-40s", technician.email) + ";"
                        + technician.password;
                write.println(line);
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to technician list file.");
        }
    }

    public static void writeStudent() {
        try {
            String fileName = "studentInformation.txt";
            File file = new File(filePath + fileName);
            PrintWriter write = new PrintWriter(file);

            for (Student student: Student.getOverallStudentList()) {
                String line = String.format("%-10s", student.tpNumber) + ";"
                        + String.format("%-30s", student.name) + ";"
                        + String.format("%-20s", student.gender) + ";"
                        + String.format("%-40s", student.email) + ";"
                        + student.contactNumber;
                write.println(line);
            }
            write.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in writing to student list file.");
        }
    }
}
