public class User {

    private static final String[] typesOfAccount = {"Manager", "Technician", "Customer"};
    private static final String[] genderTypes = {"Male", "Female", "Prefer not to say"};
    private static final String[] maritalStatus = {"Married", "Single"};

    public static String[] getTypesOfAccount() {
        return typesOfAccount;
    }

    public static String[] getGenderTypes() {
        return genderTypes;
    }

    public static String[] getMaritalStatus() {
        return genderTypes;
    }

}
