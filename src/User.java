public class User {

    String name, gender, email, contactNumber;

    private static final String[] typesOfAccount = {"Manager", "Technician", "Customer"};
    private static final String[] genderTypes = {"Male", "Female", "Prefer not to say"};
    private static final String[] maritalStatus = {"Married", "Single"};

    public User(String name, String gender, String email, String contactNumber) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.contactNumber = contactNumber;
    }
    public static String[] getTypesOfAccount() {
        return typesOfAccount;
    }

    public static String[] getGenderTypes() {
        return genderTypes;
    }

    public static String[] getMaritalStatus() {
        return maritalStatus;
    }

}
