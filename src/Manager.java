public class Manager extends User{

    private final static String[] managerPosition = {"CEO", "CFO", "Warden"};

    public static String[] getManagerPosition() {
        return managerPosition;
    }

    public Manager(String name, String gender, String email, String contactNo) {
        super(name, gender, email, contactNo);
    }

}
