import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager extends User {
    String managerID, maritalStatus, addressLine1, addressLine2, addressLine3, postcode, city, state, nationality, password, position;
    LocalDate dateJoined;
    private final static String[] managerPosition = {"CEO", "CFO", "Warden"};

    public static String[] getManagerPosition() {
        return managerPosition;
    }

    public final static ArrayList<Manager> overallManagerList = new ArrayList<>();

    public Manager(String name, String gender, String maritalStatus, String addressLine1, String addressLine2, String addressLine3, String postcode, String city, String state, String nationality, String contactNo, String dateJoined, String position, String email, String password) {
        super(name, gender, email, contactNo);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.managerID = "A" + String.format("%-3d", getOverallManagerList().size() + 1);
        this.maritalStatus = maritalStatus;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
        this.nationality = nationality;
        this.dateJoined = LocalDate.parse(dateJoined, dateFormat);
        this.position = position;
        this.password = password;
    }

    public static ArrayList<Manager> getOverallManagerList() {
        return overallManagerList;
    }

}
