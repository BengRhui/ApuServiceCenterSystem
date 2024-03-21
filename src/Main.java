public class Main {
    public static void main(String[] args) {
        TextFileOperationsComponent.readAppointment();
        TextFileOperationsComponent.readStudent();
        TextFileOperationsComponent.readFeedbackFromList();
        TextFileOperationsComponent.readTechnicianFromFile();
        TextFileOperationsComponent.readManagerFromFile();
        TextFileOperationsComponent.readElectronicsData();
        new InitialMainPage();
    }
}
