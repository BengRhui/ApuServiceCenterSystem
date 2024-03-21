import java.util.ArrayList;

public class Technician extends User {
    String technicianID, maritalStatus, addressLine1, addressLine2, addressLine3, postcode, city, state, nationality, password;
    private final static String[] technicianPosition = {"Electrician", "Computer technician", "Maintenance technician"};
    private static ArrayList<Technician> overallTechnicianList = new ArrayList<>();

    public static String[] getTechnicianPosition() {
        return technicianPosition;
    }

    public Technician(String technicianID, String name, String gender, String maritalStatus, String addressLine1, String addressLine2, String addressLine3, String postcode, String city, String state, String nationality, String contactNo, String email, String password) {
        super(name, gender, contactNo, email);
        this.technicianID = technicianID;
        this.maritalStatus = maritalStatus;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
        this.nationality = nationality;
        this.password = password;
    }

    public static ArrayList<Technician> getOverallTechnicianList() {
        TextFileOperationsComponent.readTechnicianFromFile();
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

}
