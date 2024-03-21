import java.util.ArrayList;

public class Student extends User {
    String tpNumber;

    private static final ArrayList<Student> overallStudentList = new ArrayList<>();

    public Student(String tpNumber, String name, String gender, String email, String contactNumber) {
        super(name, gender, email, contactNumber);
        this.tpNumber = tpNumber;
    }

    public static ArrayList<Student> getOverallStudentList() {
        return overallStudentList;
    }

    public Student searchByTP(String tpNumber) {
        Student found = null;
        for (Student student: overallStudentList) {
            if (student.tpNumber.equals(tpNumber)) {
                found = student;
            }
        }
        return found;
    }

}
