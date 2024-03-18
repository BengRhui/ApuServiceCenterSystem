import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextFileOperations {
    private final static String filePath = "textfile/";
    public static void readElectronicsData() {
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
        try {
            String fileName = "appointmentlist.txt";
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
            System.out.println("appointmentlist.txt is not found. Please inspect readAppointmentData() method.");
        } catch (NumberFormatException ex) {
            System.out.println("Price of appointmentlist.txt cannot be converted to integer. Please inspect appointmentlist.txt file.");
        }
    }
}
