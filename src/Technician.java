import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Technician extends User {
    String technicianID, maritalStatus, addressLine1, addressLine2, addressLine3, postcode, city, state, nationality, password, position;
    LocalDate dateJoined;
    private final static String[] technicianPosition = {"Electrician", "Computer technician", "Maintenance technician"};
    private final static ArrayList<Technician> overallTechnicianList = new ArrayList<>();

    public static String[] getTechnicianPosition() {
        return technicianPosition;
    }

    public Technician(String technicianID, String name, String gender, String maritalStatus, String addressLine1, String addressLine2, String addressLine3, String postcode, String city, String state, String nationality, String contactNo, String dateJoined, String position, String email, String password) {
        super(name, gender, email, contactNo);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.technicianID = technicianID;
        this.maritalStatus = maritalStatus;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
        this.nationality = nationality;
        this.dateJoined = LocalDate.parse(dateJoined, dateFormatter);
        this.position = position;
        this.password = password;
    }

    public static ArrayList<Technician> getOverallTechnicianList() {
        return overallTechnicianList;
    }

    public static String getNameFromID(String ID) {
        TextFileOperationsComponent.readTechnicianFromFile();
        String name = null;
        for (Technician people: overallTechnicianList) {
            if (people.technicianID.equals(ID)) {
                if (people.gender.equals("Male")) {
                    name = "Mr. " + people.name;
                    break;
                } else if (people.gender.equals("Female")) {
                    name = "Ms. " + people.name;
                    break;
                } else {
                    name = "Mr./Ms. " + people.name;
                    break;
                }
            }
        }
        return name;
    }

    public static String[] generateTechnicianNames() {
        TextFileOperationsComponent.readTechnicianFromFile();
        String[] names = new String[overallTechnicianList.size()];
        for (int i = 0; i < names.length; i ++) {
            names[i] = overallTechnicianList.get(i).name;
        }
        return names;
    }

    public static String getIdFromName(String technicianName) {
        String technicianID = null;
        TextFileOperationsComponent.readTechnicianFromFile();
        for (Technician technician: overallTechnicianList) {
            if (technician.name.equals(technicianName)) {
                technicianID = technician.technicianID;
            }
        }
        return technicianID;
    }

    public static String generateNewTechnicianID() {

        TextFileOperationsComponent.readTechnicianFromFile();
        StringBuilder lastID = new StringBuilder(Technician.getOverallTechnicianList().getLast().technicianID);
        lastID.delete(0, 1);
        int newID = Integer.parseInt(lastID.toString()) + 1;

        return "T" + String.format("%03d", newID);
    }

}
